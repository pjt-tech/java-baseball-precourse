package baseball.basic;

public class BaseballGameController {

    private static final int COUNT = 3;

    BaseballGame bbg = null;

    int[] comNumArr = new int[COUNT]; //시작시 상대방(컴퓨터)의 임의의 숫자 3개
    int[] playerNumArr = new int[COUNT]; //사용자에게 입력받을 숫자

    boolean state = true;
    boolean isFirst = true; //게임이 새로 시작하는지

    public void gameControl() {
        boolean isValidation;

        while (state) {
            setNewGames();
            String inputNum = bbg.setPlayerNum(); //player 숫자 입력
            isValidation = bbg.verification(inputNum);

            if (isValidation) {
                runGames(inputNum);
            }
        }
    }

    private void setNewGames() {
        //새로운 게임 셋팅
        if(isFirst) {
            bbg = new BaseballGame(comNumArr, playerNumArr);
            bbg.setComNumArr(comNumArr);//상대방(컴퓨터)의 임의의 숫자 셋팅
            isFirst = false;
        }
    }

    private void runGames(String inputNum) {
        //검증 후 게임 시작
        boolean gameResult;

        bbg.changeType(playerNumArr, inputNum.split(""));
        gameResult = bbg.startBaseballGame(comNumArr, playerNumArr);

        if(gameResult) {
            bbg.choiceNumber();
            isFirst = true;
        }
    }

}
