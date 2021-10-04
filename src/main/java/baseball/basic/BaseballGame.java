package baseball.basic;

import baseball.BaseBallScore;
import nextstep.utils.Randoms;

public class BaseballGame {

    static final int COUNT = 3;

    BaseBallScore bbs;
    int[] comNumArr;
    int[] playerNumArr;

    public int[] setPlayerNumArr(String inputNum, int[] playerNumArr) {
        String[] strArr = inputNum.split("");

        for(int i = 0; i < COUNT; i++) {
            playerNumArr[i] = Integer.parseInt(strArr[i]);
        }
        return playerNumArr;
    }

    public int[] setComNumArr(int[] comNumArr) {
        boolean loop = true;

        while (loop) {
            loop = setRandomNumber(comNumArr);
        }
        return comNumArr;
    }

    private boolean setRandomNumber(int[] comNumArr) {

        for(int i = 0; i < COUNT; i++) {
            comNumArr[i] = Randoms.pickNumberInRange(1,9);
        }
        return comNumArr[0] == comNumArr[1] || comNumArr[1] == comNumArr[2] || comNumArr[0] == comNumArr[2];
    }

    private void setInfoVal(int[] comNumArr, int[] playerNumArr) {

        this.comNumArr = comNumArr;
        this.playerNumArr = playerNumArr;
        bbs = new BaseBallScore(false, 0, 0);
    }

    public BaseBallScore startBaseballGame(int[] comNumArr, int[] playerNumArr) {

        setInfoVal(comNumArr, playerNumArr);

        for(int i = 0; i < COUNT; i++) {
            checkGameCount(i);
        }
        return bbs;
    }

    private void checkGameCount(int i) {

        for(int j = 0; j < COUNT; j++) {
            checkSameVal(i, j);
        }
    }

    private void checkSameVal(int i, int j) {
        int ball = bbs.getBall();

        if (comNumArr[i] == playerNumArr[j]) {
            bbs.setBall(++ball);
            checkSameIdx(i, j);
        }
    }

    private void checkSameIdx(int i, int j) {
        int strike = bbs.getStrike();
        int ball = bbs.getBall();

        if (i == j) {
            bbs.setStrike(++strike);
            bbs.setBall(--ball);
        }
    }
}
