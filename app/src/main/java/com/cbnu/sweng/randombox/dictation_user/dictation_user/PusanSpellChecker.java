package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.util.ArrayMap;
import android.util.Log;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.CandWordList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.Error;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.Help;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWord;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWordList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuNlpSpeller;

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
    PnuNlpSpeller pnuNlpSpeller;

    public PnuNlpSpeller execute(String text1) throws Exception{
        RequestBody formBody = new FormBody.Builder()
                .add("id", "text1")
                .add("name", "text1")
                .add("text1", text1)
                .build();

        final Request request = new Request.Builder()
                .url("http://speller.cs.pusan.ac.kr/PnuWebSpeller/lib/getXMLResult.asp")
                .post(formBody)
                .build();

        Response r = client.newCall(request).execute();
        if (r.isSuccessful()){
            String response = r.body().string();
            Log.e("response ", "CheckOnResponse(): " + response );

            pnuNlpSpeller = convertX2J(response);
        }

        return pnuNlpSpeller;
    }

    public PnuNlpSpeller convertX2J(String xml){
        PnuNlpSpeller pnuNlpSpeller = new PnuNlpSpeller();
        PnuErrorWordList pnuErrorWordList = new PnuErrorWordList();
        PnuErrorWord pnuErrorWord = new PnuErrorWord();
        CandWordList candWordList = new CandWordList();
        String[] candWord = null;
        Help help = new Help();
        Error error = new Error();

        XmlParser xmlParser = new XmlParser();
        Document doc = xmlParser.loadXmlString(xml);

        NodeList nodePnuErrorWordList = doc.getElementsByTagName("PnuErrorWordList");
        PnuErrorWordList[] pnuErrorWordLists = new PnuErrorWordList[nodePnuErrorWordList.getLength()];
        for(int i = 0; i< nodePnuErrorWordList.getLength(); i++){
            pnuErrorWordList.setRepeat(xmlParser.getAttribute(nodePnuErrorWordList, "repeat"));
            System.out.println("repat" + pnuErrorWordList.getRepeat());

            NodeList nodeError = doc.getElementsByTagName("Error");

            if(nodeError.item(0) != null){
                error.setMsg(xmlParser.getAttribute(nodeError, "msg"));
                System.out.println("error.msg" + error.getMsg());
            }
            else{
                //@NULL
                error.setMsg("PASS");
                System.out.println("error.msg" + "PASS");
            }
            pnuErrorWordList.setError(error);

            NodeList nodePnuErrorWord = doc.getElementsByTagName("PnuErrorWord");
            PnuErrorWord[] pnuErrorWords = new PnuErrorWord[nodePnuErrorWord.getLength()];
            System.out.println("PnuErrorWord.length : " + nodePnuErrorWord.getLength());

            for(int j = 0; j< nodePnuErrorWord.getLength(); j++){
                Node node = nodePnuErrorWord.item(j);
                Element fstElmnt = (Element) node;
                NodeList nodeOrgStrList = fstElmnt.getElementsByTagName("OrgStr");
                NodeList nodeCandWordList = fstElmnt.getElementsByTagName("CandWordList");
                NodeList nodeHelpList = fstElmnt.getElementsByTagName("Help");

                candWordList.setM_nCount(Integer.parseInt(xmlParser.getAttribute(nodeCandWordList, "m_nCount")));
                for(int k = 0; k< candWordList.getM_nCount(); k++){
                    Node node1 = nodeCandWordList.item(k);
                    Element fstElmnt1 = (Element) node1;
                    NodeList nodeCandWord = fstElmnt1.getElementsByTagName("CandWord");
                    candWord = new String[candWordList.getM_nCount()];
                    candWord[k] = nodeCandWord.item(0).getChildNodes().item(0).getNodeValue();
                    System.out.println("candword" + candWord[k]);
                }
                candWordList.setCandWord(candWord);

                help.setNCorrectMethod(Integer.parseInt(xmlParser.getAttribute(nodeHelpList, "nCorrectMethod")));
                help.setText(nodeHelpList.item(0).getChildNodes().item(0).getNodeValue());


                pnuErrorWord.setM_nStart(Integer.parseInt(xmlParser.getAttribute(node, "m_nStart")));
                pnuErrorWord.setM_nEnd(Integer.parseInt(xmlParser.getAttribute(node, "m_nEnd")));
                pnuErrorWord.setNErrorIdx(Integer.parseInt(xmlParser.getAttribute(node, "nErrorIdx")));
                pnuErrorWord.setOrgStr(nodeOrgStrList.item(0).getChildNodes().item(0).getNodeValue());
                pnuErrorWord.setCandWordList(candWordList);
                pnuErrorWord.setHelp(help);

                pnuErrorWords[j] = pnuErrorWord;
                System.out.println("help.getNCorrectMethod" + pnuErrorWords[j].getHelp().getNCorrectMethod());
                System.out.println("help.getText" + pnuErrorWords[j].getHelp().getText());
                System.out.println("m_nEnd" + pnuErrorWords[j].getM_nEnd());
                System.out.println("m_nStart" + pnuErrorWords[j].getM_nStart());
                System.out.println("nErrorIdx" + pnuErrorWords[j].getNErrorIdx());
                System.out.println("setOrgStr" + pnuErrorWords[j].getOrgStr());


            }
            pnuErrorWordList.setPnuErrorWord(pnuErrorWords);
            pnuErrorWordLists[i] = pnuErrorWordList;
        }
        pnuNlpSpeller.setPnuErrorWordList(pnuErrorWordLists);

        return pnuNlpSpeller;
    }
}
