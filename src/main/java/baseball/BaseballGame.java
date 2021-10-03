package baseball;

import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class BaseballGame {

    private static final int COUNT = 3;
    private BaseBallScore bbs;

    int[] comNumArr;
    int[] playerNumArr;

    public BaseballGame(int[] comNumArr, int[] playerNumArr) {
        this.comNumArr = comNumArr;
        this.playerNumArr = playerNumArr;
    }

    public void setComNumArr(int[] comNumArr) {
        boolean loop = true;
        while (loop) {
            loop = randomNumberSet(comNumArr);
        }
    }

    private boolean randomNumberSet(int[] comNumArr) {
        for(int i = 0; i < COUNT; i++) {
            comNumArr[i] = Randoms.pickNumberInRange(1,9);
        }
        return comNumArr[0] == comNumArr[1] || comNumArr[1] == comNumArr[2] || comNumArr[0] == comNumArr[2];
    }

    public String setPlayerNum() {
        System.out.print("\n숫자를 입력해주세요!! : ");
        return Console.readLine();
    }

    public boolean startBaseballGame(int[] comNumArr, int[] playerNumArr) {
        bbs = new BaseBallScore(false,0,0);

        for(int i = 0; i < COUNT; i++) {
            checkSameNumber(comNumArr, playerNumArr, i);
        }
        printResult();
        CheckStrikeCount();
        return bbs.getSuccess();
    }

    public void CheckStrikeCount() {
        if(bbs.getStrike() == COUNT){
            bbs.setSuccess(true);
            System.out.println("\n3개의 숫자를 모두 맞히셨습니다. 게임 끝!");
        }
    }

    public void checkSameNumber(int[] comNumArr, int[] playerNumArr, int i) {
        int ball = bbs.getBall();

        for(int j = 0; j < COUNT; j++) {
            if (comNumArr[i] == playerNumArr[j]) {
                bbs.setBall(++ball);
                checkSameIdx(i, j);
            }
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

    private void printResult() {
        int strike = bbs.getStrike();
        int ball = bbs.getBall();
        if(strike != 0) {
            System.out.print(strike + "스트라이크");
        }
        if(ball != 0) {
            System.out.print(ball + "볼");
        }
        if(strike == 0 && ball == 0) {
            System.out.print("낫싱");
        }
    }

    public void changeType(int[] playerNumArr, String[] strArr) {
        for(int i = 0; i < COUNT; i++) {
            playerNumArr[i] = Integer.parseInt(strArr[i]);
        }
    }

    public boolean checkNumberLength(String[] strArr) {
        if(strArr.length != COUNT) {
            System.out.print("[ERROR]3자리의 숫자를 입력해주세요.");
            return false;
        }
        return true;
    }

    public boolean verification(String inputNum) {
        boolean isResult = checkCharIsNumber(inputNum);
        if(isResult) isResult = checkNumberLength(inputNum.split(""));

        return isResult;
    }

    public boolean checkCharIsNumber(String inputNum) {
        boolean charDigitResult = inValidation(inputNum);
        if(!charDigitResult) {
            System.out.print("[ERROR]숫자만 입력해주세요.");
        }
        return charDigitResult;
    }

    public boolean inValidation(String inputNum) {
        boolean output = true;
        for(int i = 0; i < inputNum.length(); i++) {
            char tmp = inputNum.charAt(i);
            if(!Character.isDigit(tmp)) output = false;
        }
        return output;
    }

    public int choiceQuestion() {
        System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 눌러요!! : ");

        String inputStr = Console.readLine();
        boolean isValidation = inValidation(String.valueOf(inputStr));
        if(!isValidation) return 0;

        return Integer.parseInt(inputStr);
    }

    public boolean checkInputNumber(int inputNum) {
        if (!(inputNum == 1 || inputNum == 2)) {
            choiceFailMsg();
            return true;
        }
        if(inputNum == 1) {
            System.out.print("새로운 게임 start~!");
            return false;
        }
        stopGame();
        return false;
    }

    private void stopGame() {
        System.out.println("게임을 종료합니다. bye~!");
        System.exit(0);
    }

    public void choiceFailMsg() {
        System.out.println("[ERROR]1 또는 2를 정확히 입력해주세요!! : ");
    }

    public void choiceNumber() {
        boolean loop = true;
        while (loop) {
            loop = checkInputNumber(choiceQuestion());
        }
    }
}
