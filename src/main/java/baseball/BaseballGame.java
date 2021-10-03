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
        for(int i = 0; i < COUNT; i++) {
            comNumArr[i] = Randoms.pickNumberInRange(1,9);
        }
    }

    public String setPlayerNum() {
        System.out.print("숫자를 입력해주세요!! : ");

        return Console.readLine();
    }

    public boolean startBaseballGame(int[] comNumArr, int[] playerNumArr) {
        bbs = new BaseBallScore();

        for(int i = 0; i < COUNT; i++) {
            checkSameNumber(comNumArr, playerNumArr, i);
        }
        printResult();
        CheckStrikeCount();
        return bbs.getSuccess();
    }

    public void CheckStrikeCount() {
        if(bbs.getStrike() == COUNT && bbs.getBall() == 0){
            bbs.setSuccess(true);
            System.out.println("3개의 숫자를 모두 맞히셨습니다. 게임 끝!");
        }
    }

    public void checkSameNumber(int[] comNumArr, int[] playerNumArr, int i) {

        for(int j = 0; j < COUNT; j++) {
            if (comNumArr[i] == playerNumArr[j]) {
                checkSameIdx(i, j);
            }
        }
    }

    private void checkSameIdx(int i, int j) {
        int strike = bbs.getStrike();
        int ball = bbs.getBall();

        if (i != j) {
            bbs.setBall(++ball);
        } else {
            bbs.setStrike(++strike);
        }
    }

    private void printResult() {
        int strike = bbs.getStrike();
        int ball = bbs.getBall();
        if(strike > 0 && ball > 0) {
            System.out.println(strike + "스트라이크 " + ball + "볼");
        } else if(strike == 0 || ball > 0) {
            System.out.println(ball + "볼");
        } else if(strike < 0 || ball == 0) {
            System.out.println(strike + "스트라이크");
        } else {
            System.out.println("낫싱");
        }
    }

    public void changeType(int[] playerNumArr, String[] strArr) {
        for(int i = 0; i < COUNT; i++) {
            playerNumArr[i] = Integer.parseInt(strArr[i]);
        }
    }

    public boolean checkNumberLength(String[] strArr) {
        if(strArr.length != COUNT) {
            System.out.println("[ERROR" +
                    "]3자리의 숫자를 입력해주세요.");
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
            System.out.println("[ERROR]숫자만 입력해주세요.");
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

    public boolean checkChoiceQuestion(int inputNum) {

        checkInputNumber(inputNum);

        if(inputNum == 1) {
            return true;
        } else if(inputNum == 2) {
            System.out.print("게임을 종료합니다. bye~!");
            return false;
        }
        return false;
    }

    public boolean checkInputNumber(int inputNum) {
        return inputNum == 1 || inputNum == 2;
    }

    private void choiceFail() {
        System.out.println("[ERROR]1 또는 2를 정확히 입력해주세요!! : ");
        choiceQuestion();
    }
}
