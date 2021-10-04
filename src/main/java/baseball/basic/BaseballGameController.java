package baseball.basic;

import baseball.BaseBallScore;

public class BaseballGameController {

    BaseballGame bbg = null; //view
    BaseballGameView bbv = null; //비지니스
    BaseBallScore bbs = null;

    int[] playerNumArr;   //시작시 상대방(컴퓨터)의 임의의 숫자 3개
    int[] comNumArr; //사용자에게 입력받을 숫자

    boolean state = true;
    boolean isFirst = true; //게임이 새로 시작하는지

    public BaseballGameController() {
        this.comNumArr = new int[BaseballGame.COUNT];
        this.playerNumArr = new int[BaseballGame.COUNT];
    }

    public void gameControl() {
        boolean isValidation;

        while (state) {
            setNewGames();
            String inputNum = bbv.setPlayerNum(); //player 숫자 입력
            isValidation = bbv.verification(inputNum); //입력 값 검증

            if (isValidation) {
                playerNumArr = bbg.setPlayerNumArr(inputNum, playerNumArr);
                runGames();
            }
        }
    }

    private void setNewGames() {
        //새로운 게임 셋팅
        if(isFirst) {
            bbv = new BaseballGameView();
            bbg = new BaseballGame();
            comNumArr = bbg.setComNumArr(comNumArr);//상대방(컴퓨터)의 임의의 숫자 셋팅
            isFirst = false;
        }
    }

    private void runGames() {
        //게임 시작
        bbs = bbg.startBaseballGame(comNumArr, playerNumArr);
        bbv.printResult(bbs);

        if(bbv.CheckStrikeCount(bbs)) {
            bbv.choiceNumber();
            isFirst = true;
        }
    }
}
