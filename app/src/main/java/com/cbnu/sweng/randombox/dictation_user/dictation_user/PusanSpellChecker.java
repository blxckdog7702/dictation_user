package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.util.ArrayMap;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by user on 2017-08-20.
 */

public class PusanSpellChecker {

    private final OkHttpClient client = new OkHttpClient();
    private ArrayList<ArrayMap<String, String>> result = new ArrayList<>();

    public ArrayList<ArrayMap<String, String>> execute(String text1) throws Exception{
        RequestBody formBody = new FormBody.Builder()
                .add("id", "text1")
                .add("name", "text1")
                .add("text1", text1)
                .build();

        final Request request = new Request.Builder()
                .url("http://speller.cs.pusan.ac.kr/PnuWebSpeller/lib/getXMLResult.asp")
                .post(formBody)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("HttpService", "onFailure() Request was: " + request);
                e.printStackTrace();
            }

            /*
                  <PnuNlpSpeller> : 최상위 노드, Root의 역할만 함
                  <PnuErrorWordList> : 오류어 리스트 노드,
                      Attribute { [repeat: 반복교정 여부, 값은 yes/no] }
                  <PnuErrorWord> : 오류어 노드,
                      Attribute { [nErrorIdx: 오류어 번호, 값은 0부터 1씩 증가],
                                  [m_nStart: 원문에서 오류어가 시작하는 위치. 문서의 시작을 0으로 본다.],
                                  [m_nEnd: 원문에서의 오류어가 끝나는 위치] }
                  <OrgStr> : 입력된 문자열에 대한 노드, 오류어의 원래 문자열을 가짐.
                  <CandWordList> : 대치어 리스트 노드,
                      Attribute { [m_nCount: 대치어 갯수, 0인 경우 대치어가 없음] }
                  <CandWord> : 대치어 노드, 대치어의 문자열을 가짐.
                  <Help> : 도움말 노드, 도움말 정보가 그대로 표현되어 있음. 개행을 html형식으로 <br/>로 나타낸 것이 특이사항.
                      Attribute { [nCorrectMethod: 교정 방법에 대한 정보. 해당 정보를 이용해 밑줄 색을 다르게 표기할 수도 있음.
                                      0-에러가 없을 때,
                                      1-형태소 분석이 안 될 때,
                                      2-오용어로 분석될 때,
                                      3-다수어절 오류,
                                      4-의미 문체 오류,
                                      5-문장 부호 오류,
                                      6-통계정보를 이용한 붙여쓰기,
                                      7-영어 오용어로 분석될 때,
                                      8-태깅 오류,
                                      9-복합명사 언더바 오류,
                                      10-오류 형태에 따라 붙여쓰기] }
                  <Error> : 검사 도중 오류가 발생했을 때 나타나는 노드. 오류 내용을 msg Attribute를 통해 표현함. 오류가 없으면 나타나지 않음.
			*/
            @Override
            public void onResponse(Call call, Response r) throws IOException {
                String response = r.body().string();

                Log.e("response ", "onResponse(): " + response );

                XmlParser xmlParser = new XmlParser();
                Document doc = xmlParser.loadXmlString(response);

                NodeList PnuErrorWordList = doc.getElementsByTagName("PnuErrorWordList");
                for(int i = 0; i< PnuErrorWordList.getLength()-1; i++){
                    ArrayMap<String, String> map = new ArrayMap<String, String>();
                    map.put("repeat", xmlParser.getAttribute(PnuErrorWordList, "repeat"));

                    NodeList PnuErrorWord = doc.getElementsByTagName("PnuErrorWord");
                    for(int j = 0; j< PnuErrorWord.getLength(); j++){
                        Node node = PnuErrorWord.item(j);
                        Element fstElmnt = (Element) node;
                        NodeList orgStrList  = fstElmnt.getElementsByTagName("OrgStr");
                        NodeList CandWordList  = fstElmnt.getElementsByTagName("CandWordList");
                        NodeList helpList  = fstElmnt.getElementsByTagName("Help");
                        NodeList ErrorList  = fstElmnt.getElementsByTagName("Error");

                        map.put("nErrorIdx", xmlParser.getAttribute(node, "nErrorIdx"));
                        map.put("m_nStart", xmlParser.getAttribute(node, "m_nStart"));
                        map.put("m_nEnd", xmlParser.getAttribute(node, "m_nEnd"));
                        map.put("m_nCount", xmlParser.getAttribute(CandWordList, "m_nCount"));

                        for(int k = 0; k< CandWordList.getLength(); k++){
                            Node node1 = CandWordList.item(k);
                            Element fstElmnt1 = (Element) node1;
                            NodeList CandWord  = fstElmnt1.getElementsByTagName("CandWord");

                            map.put("CandWord", CandWord.item(0).getChildNodes().item(0).getNodeValue());
                        }

                        map.put("orgStr", orgStrList.item(0).getChildNodes().item(0).getNodeValue());
                        map.put("Help", helpList.item(0).getChildNodes().item(0).getNodeValue());
                        map.put("nCorrectMethod", xmlParser.getAttribute(helpList, "nCorrectMethod"));
                        if(ErrorList.item(0) != null){
                            map.put("Error", ErrorList.item(0).getChildNodes().item(0).getNodeValue());
                        }
                    }
                    result.add(map);
                }
            }
        });
        return result;
    }
}
