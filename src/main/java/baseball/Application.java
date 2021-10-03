package baseball;

public class Application {

    private static final int COUNT = 3;

    public static void main(String[] args) {

        int[] comNumArr = new int[COUNT]; //시작시 상대방(컴퓨터)의 임의의 숫자 3개
        int[] playerNumArr = new int[COUNT]; //사용자에게 입력받을 숫자
        boolean state = true;  //반복문 while
        boolean gameResult = false; //게임결과 맞았는지 틀렸는지
        boolean isFirst = true; //게임이 새로 시작하는지
        boolean isCheckNum;
        boolean isValidation;
        BaseballGame bbg = null;

        while (state) {
            if(isFirst) {
                bbg = new BaseballGame(comNumArr, playerNumArr);
                bbg.setComNumArr(comNumArr);  //상대방(컴퓨터)의 임의의 숫자 셋팅
                isFirst = false;
            }
            String inputNum = bbg.setPlayerNum(); //player 숫자 입력
            isValidation = bbg.verification(inputNum);

            if(isValidation) {
                bbg.changeType(playerNumArr,inputNum.split(""));
                gameResult = bbg.startBaseballGame(comNumArr, playerNumArr);
            }
            if(gameResult) {
                isCheckNum = bbg.checkInputNumber(bbg.choiceQuestion());
                if(isCheckNum) state = bbg.checkChoiceQuestion(bbg.choiceQuestion());

            }
        }
    }
}
