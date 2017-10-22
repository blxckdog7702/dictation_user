package com.cbnu.sweng.randombox.dictation_user.dictation_user.model;

import android.util.ArrayMap;

/**
 * Created by user on 2017-10-22.
 */

public class LocalQuestions {
    String questionType;
    private String[] one_one = new String[]{"우리", "나", "너", "아버지", "가족", "어머니", "아기", "우리 아기", "우리 가족", "우리는 하나"};
    private String[] one_two = new String[]{"친구", "내 친구", "정다운", "선생님", "고마우신", "학교", "우리 학교", "즐거운 학교", "모두 모여", "우리는 하나"};
    private String[] one_three = new String[]{"나무", "노루", "너구리", "거미", "소", "파", "고추", "오리", "하마", "고구마"};
    private String[] one_four = new String[]{"나의 꿈", "김슬기 입니다", "그림을", "잘 그립니다.", "화가가", "되고 싶습니다", "모자", "나비", "아침입니다", "학교에"};
    private String[] one_five = new String[]{"다녀오겠습니다", "어머니께", "인사를 합니다", "길에서", "친구를", "만납니다", "수진아 안녕", "진수야 안녕", "함께", "갑니다"};
    private String[] one_six = new String[]{"학교 가는 길", "아기나무", "어서어서", "자라서", "무엇이 될까", "자라면", "큰 나무되지", "우리는", "자라라", "꾀꼬리"};
    private String[] one_seven = new String[]{"목소리", "개나리", "울타리", "오리", "한 마리", "시냇물이 졸졸졸", "바람개비가 뱅글뱅글", "엄마돼지", "아기돼지", "토실토실"};
    private String[] one_eight = new String[]{"밥 달라고", "꿀꿀꿀", "오냐오냐", "알았다고", "춤을 추어요", "고개를 끄덕끄덕", "어깨를 으쓱으쓱", "엉덩이를 흔들흔들", "덩실덩실 신나게", "방글방글"};
    private String[] one_nine = new String[]{"풍덩 엄마오리", "연못 속에", "퐁당 아기오리", "엄마 따라", "둥둥", "연못 위에", "동동 아기오리", "곰과 여우", "골짜기에서", "가재를 잡고"};
    private String[] one_ten = new String[]{"잡고 있습니다", "꾀 많은 여우가", "슬금슬금", "다가갑니다", "나무에 있는", "꿀을 따서", "나눠 먹지 않을래", "뒤를", "성큼성큼", "따라갑니다"};
    private String[] one_eleven = new String[]{"맛있겠다", "나 혼자 먹어야지", "꾀를 냅니다", "나무 위로 올라가", "벌집을 따서", "내가 받을게", "살금살금", "올라갑니다", "가득 들어 있는", "아래로 던집니다"};
    private String[] one_twelve = new String[]{"받아 들고는", "나", "너", "아버지", "가족", "어머니", "아기", "우리 아기", "우리 가족", "우리는 하나"};
    String[] one_thirteen = new String[]{"집을 봅니다", "심심해", "토끼장 문을 엽니다", "토끼들이 신나서", "깡충깡충 뛰어", "나옵니다", "외양간 ", "빗장을 풀자", "소들도", "신이 나서"
    };
    String[] one_fourteen = new String[]{"겅중겅중", "뛰어나옵니다", "배추밭으로", "안 돼", "오물오물", "맛있게 먹습니다", "빨리 돌아와", "배추밭으로", "달려갑니다", "보리밭으로"
    };
    String[] one_fifteen = new String[]{"우물우물 먹습니다", "어떻게 해", "동동 구릅니다", "울상이 됩니다", "심심해서 그랬어요", "흉내놀이", "참새 소리 내보자", "짹짹짹짹", "꽉꽉꽉꽉", "재미있다"
    };
    String[] two_one = new String[]{"물방울이 햇빛을 받아 생깁니다.", "반원 모양의 띠처럼", "추석 무렵이 되면", "햇과일로 차례를 지냅니다.", "밝은 보름달 아래에서", "묵은해를 보내고 ", "웃어른께 세배를 드립니다.", "떡국도 끓여 먹고", "창포를 삶은 물에 머리를 감으면", "머릿결이 좋아진다고"
    };
    String[] two_two = new String[]{"일기를 읽으면서", "키는 50센티미터이며", "몸무게는 3킬로그램", "아랫니 두 개가 ", "마치 새싹처럼 뾰족하게", "세 발짝을 걸었다.", "뒤뚱거리며 걷기가", "밤늦도록", "중요한 일을 모두 일기에", "어머니께서 쓰신 육아 일기"
    };
    String[] two_three = new String[]{"어디에서나 흔히 볼 수 있는", "꽃밭에 심기로", "풀 이름을 적은 푯말을 꽂았습니다.", "틈만 나면 풀에 대하여", "노란즙이 나옵니다.", "열매를 짓이겨", "이삭이 강아지 꼬리를 닮아서", "새의 모이가 되기도 합니다.", "쓴 맛이 나서 씀바귀라고 부릅니다.", "우리에게 이름이 있듯이"
    };
    String[] two_four = new String[]{"어떻게 변하여 왔을까요?", "알갱이가 굵어서", "달콤한 아이스 크림", "는개, 이슬비, 가랑비", "굵고 억세게 내리는 비", "세찬 바람과 함께", "좍좍 내리는 비는 채찍비", "뺨이 얼얼하다고", "월드 컵 축구 대회", "세계인의 관심 속에서"
    };
    String[] two_five = new String[]{"오랫동안 날 수 있는 ", "글라이더를 타다가", "목숨을 잃기도", "얼마 못 가 모래밭에", "곤두박질쳤습니다.", "엔진과 프로펠러", "말도 안 돼", "한참 동안 하늘을", "여섯 명이 손뼉을 치며", "어떻게 끓일까?"
    };
    String[] two_six = new String[]{"내가 왕이 될 거야", "동물들이 왕을 뽑고 있었습니다.", "걱정이 많았습니다.", "내가 오래 못 살 것 같구나.", "보물이 묻혀 있다는 포도밭으로 갔습니다.", "어느 한 곳도 빠뜨리지 않고", "열심히 파헤쳤습니다. ", "돌멩이도 보이지 않게 되었습니다.", "훨씬 더 탐스러운 포도가", "무엇인지 깨닫게 되었습니다."
    };
    String[] two_seven = new String[]{"뒷부분에 이어질 내용", "신문 좀 갖다 주겠니?", "아이, 귀찮아.", "나는 텔레비전을 더 볼 테야.", "중얼거렸습니다.", "조약돌들이 튀어나와", "사방으로 흩어졌다.", "책상 앞에 앉아서도", "뜻밖이라는 듯이", "승환이가 건네주는"
    };
    String[] two_eight = new String[]{"통째로 삼켜 버렸습니다.", "등잔불을 켜고", "엎어졌습니다.", "펄쩍펄쩍 뛰었습니다.", "사냥꾼이 쫓아와요.", "나뭇더미 속에", "내가 따돌릴 테니", "은혜를 갚고 싶어요.", "당연한 일을", "산골짜기를"
    };
    String[] two_nine = new String[]{"주름살이 많으니까", "정확한 발음으로", "팔걸이, 해돋이, 깨끗이", "붉게, 한 잎 두 잎", "미닫이문 사이로", "띄어 읽어야 할 곳", "옛날 이야기를 아주 좋아할 거예요.", "젊은이는 고개를 끄덕였습니다.", "안 되지, 안 돼", "큰기침을 하고 점잖게"
    };
    String[] two_ten = new String[]{"가방을 메고", "부쩍 어른스러워진 네 모습", "요즈음처럼", "옷을 얇게 입고", "부탁하는 말로 들어 주렴.", "길을 넓히는 일", "나무를 많이 베어야겠지.", "물건을 실어 나르는 데", "고개를 설레설레 저으며", "좋은 점도 있겠죠"
    };
    String[] two_eleven = new String[]{"쓰레기통을 없애면서", "훨씬 더 깨끗해질 것이라고", "제 생각은 다릅니다.", "제때에 비우지 않으면", "가장 나이가 많은", "옆에 앉아 있던", "아름답고 살기 좋은 곳인지", "친구들이 많잖아요?", "대표가 되어야 합니다.", "어떤 까닭을 들어가며"
    };
    String[] two_twelve = new String[]{"이야기 제목 알아맞히기", "신발 한 짝을 잃어버렸는데", "열람실에 들어갔습니다.", "책꽂이에서 책을 고르는", "마음이 뿌듯하였습니다.", "수염을 건드렸다.", "날카로운 이빨로 그물을 쏠아", "은혜를 갚을 줄 아는 ", "소가 된 게으름뱅이", "나는 게으름을 부릴 때가 많다."
    };
    String[] two_thirteen = new String[]{"토끼 한 쌍 그렸네.", "두 귀는 쫑긋, 앞발은 짤막", "된장을 담그거나", "어깨만 빠질 것처럼", "고갯길을 힘겹게 올라갔습니다.", "그만 주저앉고 싶었습니다.", "지겟작대기로 받쳐 놓았습니다.", "얼굴의 땀을 닦고", "빚을 갚는데 쓰고", "여덟 개를 살 수 있구나."
    };
    String[] two_fourteen = new String[]{"소복소복 쌓였습니다. ", "겨우 지푸라기를 붙잡았습니다.", "호롱불이 켜져 있었습니다.", "화롯불에 고구마를 구워", "모자를 벗는 것은 뭘까?", "수수께기를 척척 알아맞혔습니다.", "발을 엮어 주면 ", "야단맞고 쫓겨났군.", "살이 빠져 홀쭉해진 여우", "사흘을 굶기로"
    };
    String[] two_fifteen = new String[]{"굵은 빗방울이 쏟아졌습니다.", "그렇게 며칠이 지났습니다.", "나뭇가지에 매달린", "무슨 비가 그렇게 많이 온담?", "입을 벙긋거리며", "험상궂게 생긴데다가", "우리 함께 헤엄치면서 놀지 않을래?", "커다란 입을 넙죽거리며", "몸에 달라붙어서", "아무리 소리쳐도"
    };
    String[] three_one = new String[]{"방귀쟁이 며느리", "초콜릿을 많이 먹는다고", "내가 게을러서 학교에 안 가고", "기분이 우쭐해지시는 모양이다.", "숫자도 제대로 안 세어진다.", "배가 더 많이 아픈 것 같다.", "다른 생각을 할 수가 없다.", "고개를 들지 말아야 한다.", "운동장에 엉켜서 뒹구는 아이들", "이제 가야 할 시간이 되어서"
    };
    String[] three_two = new String[]{"숨을 크게 들이쉬셨다.", "입을 다물지 못하였다.", "나는 기분이 아주 으쓱해졌다.", "배 아픈 거 어떻게 되었니?", "냉큼 쥐 한 마리를 잡아다가", "으리으리한 기와집에서 ", "마구간에 있던 당나귀가", "사람들로 북적대는 저잣거리도", "하룻밤을 묵어가게 되었어.", "두둑한 배짱이 마음에 들었어."
    };
    String[] three_three = new String[]{"요즈음 들어 고민이 생겼어요.", "칠판이 훨씬 잘 보이고", "다닥다닥 붙어 있는 각종 광고지를", "떼어 낸 자리가 지저분할 때가 ", "더 많은 사람이 불편 없이", "모두 없어져 버렸으면 좋겠어요!", "글자를 띄어 써야 하나요?", "한 글자씩 써 내려갔어요.", "읽는 사람이 곤란해진다고.", "내 방문 앞까지 쫓아왔어요."
    };
    String[] three_four = new String[]{"우리가 가끔 사용하고 버리는", "종이컵 인형을 만들 준비물", "연필로 대강 선을 긋습니다.", "나무젓가락을 붙입니다.", "가위로 자르지 않았던 부분", "아래위로 당겼다 놓았다", "인형이 말하는 것처럼 보입니다.", "구체적으로 설명하자면,", "마술을 시작할 준비는 끝났습니다.", "끊어질 수 있으므로 적당한 힘으로"
    };
    String[] three_five = new String[]{"네 손가락 위에 살짝 올려놓습니다.", "눈 깜짝할 사이에", "무서운 용 한 마리가 나타나", "용을 찾아 나섰습니다.", "말 뼈들만 뒹굴고 있었습니다.", "문을 쾅쾅 두드렸습니다.", "난 지금 몹시 바쁘니 내일 다시 와.", "문에 코를 찧을 뻔하였습니다.", "성 한 채를 통째로 삼켰다니까.", "꼼짝도 하지 않았습니다."
    };
    String[] three_six = new String[]{"손가락으로 더듬어 읽는", "우리말을 온전히 나타낼 수 있는", "한글 점자 연구에 빠져들었습니다.", "마음대로 쓸 수 없던", "습관은 무심코 같은 행동을", "반복되면 쇠사슬처럼 강해져요.", "매일 아침 일어나 몸단장을 하고", "큰일이 날 수 있기 때문이었어요.", "별의 반대편까지 뿌리를 뻗쳐", "허겁지겁 일을 하게 되지요."
    };
    String[] three_seven = new String[]{"나무나 돌을 깎아 만든 장승", "마을 어귀나 길가에", "마을을 지켜 주는 구실", "길을 알려 주는 역할도 하였단다.", "경계를 표시하는 역할", "재미있고 우스꽝스러운 장난꾸러기", "친구인 듯 반갑게 인사하여", "재료는 깨끗한 것으로 ", "음식이 다 익을 때까지", "훨씬 더 맛있답니다."
    };
    String[] three_eight = new String[]{"물감을 겹겹이 칠해서", "살아 있는 것 같은 생생한 느낌", "단 한 점의 그림만을 팔았을 만큼", "엄청난 값이 나갑니다.", "소용돌이와 굽이치는 풍경", "물결 모양으로 긁습니다.", "관심과 시선을 잡아 끄는", "그것이 잘못 걸려 있다는 것을", "무척 흥미로운 사실", "시선을 집중시켰습니다."
    };
    String[] three_nine = new String[]{"사방이 캄캄한데", "혼잣말을 하였습니다.", "점심때가 되자,", "콩밭에서 한 움큼 따 왔습니다.", "밥을 한 술도 뜨지 않았습니다.", "슬프게 울고 있었습니다.", "놓고 온 것이 있어", "방에 불도 때지 못하고", "얼마나 춥고 배가 고프겠느냐?", "코끝이 찡해졌어요."
    };
    String[] three_ten = new String[]{"마음씨를 본받아야겠다고", "내가 길을 잃고 쩔쩔매던 생각이", "이제 집에 혼자 갈 수 있지?", "마음이 뿌듯하고 흐뭇하였다.", "못마땅하다는 듯이 흘겨보며", "쓸데없이 참견 말고", "옥신각신 말다툼을 하자", "또 왜들 이러시오?", "말없이 고개를 갸웃거린다.", "보기 드문 충신이로구나."
    };
    String[] three_eleven = new String[]{"똑같은 흙이 담긴 화분", "다 같이 예상해 보자.", "싹이 트지 않을 거예요.", "오늘 바쁜 일이 있어서", "책상 옆에서 머뭇머뭇합니다.", "너, 나쁜 짓 했지?", "화가 났지만 간신히 참습니다.", "쏘아붙이듯 말합니다.", "어저께도, 그저께도 내가 물 줬어.", "조바심이 났던 것입니다."
    };
    String[] three_twelve = new String[]{"그렇게 귀여울 수 없습니다.", "생긋 웃음을 보내 줍니다.", "꽃처럼 환하게 보조개가", "단잠을 자기도 하였습니다.", "홀로 있을 때가 많아졌습니다.", "나뭇잎과 사과밖에 없어.", "멀리 떠나고 싶거든.", "나도 필요한 게 별로 없어.", "바닷가에 가 보고 싶은 마음", "눈물이 그칠 새가 없었습니다."
    };
    String[] three_thirteen = new String[]{"꼬리를 하늘을 향해 쭉 뻗으면", "꼼짝 않고 있을게.", "나무 사이를 건널 때", "꼬리로 땅을 힘껏 누르면서", "위험하다는 신호를 보낸답니다.", "해충이나 적을 쫓아 버리는 데도", "어리광을 부리기도", "자꾸자꾸 몸을 들썩이자,", "달빛이 흘러들었어요.", "껍데기를 더욱더 밀어 댔어요."
    };
    String[] three_fourteen = new String[]{"눈이 딱 마주쳤어요.", "공룡의 몸을 닦아 주었어요.", "태어난 지 어느덧 일 년", "용감하고 멋진 사냥꾼", "자꾸만 엄마를 졸랐어요.", "드넓은 풀밭이 펼쳐진 그곳에는", "나뭇잎을 뜯어 먹고", "그냥 두고 볼 리가 없지요.", "엄마 곁으로 달려갔어요.", "바위를 힘주어 긁었어요."
    };
    String[] three_fifteen = new String[]{"비가 안 와 팔리지 않았구나.", "먹을 것이 잔뜩 들어 있어", "즐겁게 점심을 먹었습니다.", "비가 쏟아지기 시작하였습니다.", "우산에 대하여 캐묻지 않았습니다.", "학용품 사 써라.", "너는 천사 같은 아이이다.", "마침 돈을 주운 자가 나타났구나.", "처음 보는 낯선 풍경이었어.", "속이 안 좋아서 그만 먹을래요."
    };
    String[] four_one = new String[]{"닭은 닭은 홰에 자고", "나무 붙은 솔방울아", "꺼부꺼부 잠을 자나 ", "엄마 품에 잠을 자지.", "물오른 살구나무", "꽃가지도 소곤소곤", "내 귀가 가려워", "나뭇잎 위에 떨어졌네.", "빨랫줄에 걸렸네!", "힘 빠졌다."
    };
    String[] four_two = new String[]{"캄캄한 밤이었어.", "우리는 깜짝 놀랐어요.", "나는 왠지 가슴이 두근거렸어요.", "찾아내고야 말겠다.", "두 주먹을 불끈 쥐셨어요.", "형사처럼 예리한 눈초리로", "가장 위에 붙어 있었다.", "앞뒤가 척척 맞게", "마침 잘 됐다는 듯이 ", "일부러 짓궂게 굴었다."
    };
    String[] four_three = new String[]{"어떻게 만들어졌는지 알려면 ", "길들이는 일은 무척 힘들었다.", "비싼 값에 거래되었기 때문에", "도둑은 사라지지 않았다. ", "시치미를 떼어 버리면 ", "다른 것으로 대체되어야 할 것이다.", "지구에 묻혀 있는 양은", "수백만 년에 걸쳐 ", "쉽게 닿을 수 있는 곳이 아니어서 ", "절약하여야 할 뿐만 아니라"
    };
    String[] four_four = new String[]{"한해살이풀이에요.", "끝이 뾰족하고 기다란 달걀모양 ", "가장자리 꽃잎은 혀같이 생겼고,", "웬만한 가뭄이나 더위에도 끄떡없이", "꽃꽂이용으로도 많이 기르지요", "다친 다리를 싸매 준 제비가 ", "있는 힘껏 짹짹거리면서 ", "지저귀는 법을 배웁니다.", "사방을 훑어보고는 ", "논밭의 진흙을 으깨어 "
    };
    String[] four_five = new String[]{"값이 오르면 팔았다.", "목화를 어지럽히기도 하고", "아궁이 곁에서 불을 쬐던", "몽땅 타 버리고 말았다.", "고양이의 아픈 다리를 맡고 있던", "손을 들고 의견을 내놓았다.", "과자를 간단히 차려 놓고", "떡볶이를 만들자는", "운동을 곁들인 놀이", "의견이 잔치의 목적에 어울리는지"
    };
    String[] four_six = new String[]{"도깨비 나라에 과거 시험이 ", "자기주장만 내세우기 일쑤입니다. ", "아이들이 던진 돌멩이에 맞아", "대나무 회초리로 종아리를", "부모님의 말씀을 잘 듣는", "우리 마을은 위기에 빠졌어요.", "빗물을 잘 보관하였다면", "많은 시간과 노력이 필요해요. ", "물이 많이 나오지 않아 ", "물을 어떻게 빌려 오지요?"
    };
    String[] four_seven = new String[]{"누나 역할을 톡톡히 하더구나. ", "예의가 참 바른 어린이라고", "주로 웃어른께 하는 말로서", "할아버지, 진지 잡수세요.", "높임말과 예사말을 바르게 쓸게요.", "우리 교실이 갑자기 술렁거렸다.", "금세 친구들에게 둘러싸였다.", "선생님께서 제니를 감싸 주셨다.", "외국인들이 잘 헷갈린다고", "모든 것이 낯설었다."
    };
    String[] four_eight = new String[]{"젊었을 때부터", "깍듯하게 좋은 말투로", "가장 좋은 부위의 고기를", "뭉텅 잘라 주었다.", "비로소 안심하는 표정을", "밑줄 그은 자료를 ", "말끝을 흐렸습니다.", "왠지 다른 사람이 한 것을 베끼면", "내가 겪은 일에 대한 느낌을", "어엿한 내 저작물이 되는 것이지요."
    };
    String[] four_nine = new String[]{"무령왕과 그 왕비의 무덤이다.", "오랜 침묵을 지켜 오다가", "벽돌로 쌓아 올렸는데,", "밋밋하고 단조로운 느낌을", "두 장을 맞대면", "등잔을 올려놓았던", "비석처럼 세워 둔", "업적을 자랑하려고 강제로 ", "상대편의 말을 넘어뜨려야 합니다.", "두 번 뛰어 밟습니다."
    };
    String[] four_ten = new String[]{"한 단계 격이 낮은", "연회를 베풀던 장소이다.", "건물과 후원이 잘 어우러진", "산자락에 자연스럽게 배치된", "전통적 사상을 반영하여", "효와 인연이 깊다.", "많은 건물을 헐고 ", "나랏일을 논의하거나", "궁궐이 모두 불타 버려서 ", "손님을 맞이하던 곳이다."
    };
    String[] four_eleven = new String[]{"시골길을 걷고 있었습니다.", "햇볕이 매우 뜨거웠습니다.", "별안간 벼락 치는 듯한", "나무 밑에서 노인들이 앉아 ", "당나귀가 가엾지도 않소? ", "자앧를 어깨에 짊어지고", "담 너머 옆집까지 뻗어", "저의 무례함을 용서하십시오", "방문을 뚫고 들어온 ", "잘 익은 감을 맛있게"
    };
    String[] four_twelve = new String[]{"문구도 심심찮게 볼 수 있다.", "게시물을 붙이는 사람들이", "어렵고 낯선 외국 말보다", "살려 쓸 만한 아름다운 우리말이다.", "쉽게 알 수 있어서 ", "실제로는 불가능하대요.", "계산도 하고 기억도 하잖아.  ", "비판하고 창의적으로 생각하는", "자기 잘못을 깨달아서", "무엇인지 밝혀낼 거라고 믿는다."
    };
    String[] four_thirteen = new String[]{"바다 냄새가 차창으로 들어와", "우뚝 솟은 한라산이 보였다.", "푸른 바다가 한눈에 펼쳐졌다.", "가슴이 탁 트이는 것 같았다.", "해안을 따라 세워져 깎아지른 듯한", "정교하게 겹겹이 쌓여", "경주의 옛 이름이 서라벌인데", "천문 관측을 하기 위하여 ", "석가탑에 얽힌", "인자한 미소를 띠고 있는"
    };
    String[] four_fourteen = new String[]{"문화재가 훼손될 수 있기", "아저씨 댁으로 갔다.", "새삼스레 한국인의 긍지가", "밑이 뻥 뚫려 엉덩이가 ", "제 빛깔을 잃은 단청이", "여덟 살짜리 여자아이", "좀 버겁다 싶은 배낭인데도 ", "꼬치꼬치 캐묻는다.", "짜증을 내기는 커녕", "영락없는 꼬마들이지만 "
    };
    String[] four_fifteen = new String[]{"직접 따 먹기도 했었지", "나이를 많이 먹었구나.", "손에 든 장바구니를", "내 손에 옮겨 들었다.", "따뜻한 손길로 내 머리를 ", "안녕? 얘들아. ", "끌려갈까 봐 물래 숨어", "호기심을 꾹 참고 기다렸지.", "더 이상 참을 수가 없어서", "아침 인사를 나누고"
    };

    public ArrayMap<Integer, String> getQuestion(String questionType){
        ArrayMap<Integer, String> qs = new ArrayMap<>();
        if(questionType.equals("1-1")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_one[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-2")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_two[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-3")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_three[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-4")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_four[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-5")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_five[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-6")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_six[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-7")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_seven[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-8")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_eight[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-9")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_nine[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-10")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_ten[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-11")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_eleven[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-12")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_twelve[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-13")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_thirteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-14")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_fourteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("1-15")){
            for(int i = 0; i < 10; i++){
                qs.put(i, one_fifteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-1")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_one[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-2")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_two[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-3")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_three[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-4")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_four[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-5")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_five[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-6")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_six[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-7")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_seven[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-8")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_eight[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-9")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_nine[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-10")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_ten[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-11")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_eleven[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-12")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_twelve[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-13")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_thirteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-14")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_fourteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("2-15")){
            for(int i = 0; i < 10; i++){
                qs.put(i, two_fifteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-1")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_one[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-2")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_two[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-3")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_three[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-4")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_four[i]);
            }
            return qs;
        }else if(questionType.equals("3-5")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_five[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-6")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_six[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-7")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_seven[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-8")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_eight[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-9")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_nine[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-10")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_ten[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-11")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_eleven[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-12")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_twelve[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-13")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_thirteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-14")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_fourteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("3-15")){
            for(int i = 0; i < 10; i++){
                qs.put(i, three_fifteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-1")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_one[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-2")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_two[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-3")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_three[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-4")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_four[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-5")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_five[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-6")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_six[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-7")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_seven[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-8")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_eight[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-9")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_nine[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-10")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_ten[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-11")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_eleven[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-12")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_twelve[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-13")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_thirteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-14")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_fourteen[i]);
            }
            return qs;
        }
        else if(questionType.equals("4-15")){
            for(int i = 0; i < 10; i++){
                qs.put(i, four_fifteen[i]);
            }
            return qs;
        }
        else{
            return null;
        }
    }

}
