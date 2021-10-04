package baseball.basic;

import baseball.BaseBallScore;
import nextstep.utils.Console;

public class BaseballGameView {

    public String setPlayerNum() {
        System.out.print("\n숫자를 입력해주세요!! : ");
        return Console.readLine();
    }

    public boolean verification(String inputNum) {
        boolean isResult = checkCharIsNumber(inputNum);

        if(isResult) isResult = checkNumberLength(inputNum.split(""));

        return isResult;
    }

    public boolean checkCharIsNumber(String inputNum) {
        boolean charDigitResult = inValidation(inputNum);

        if(!charDigitResult) System.out.print("[ERROR]숫자만 입력해주세요.");

        return charDigitResult;
    }

    public boolean checkNumberLength(String[] strArr) {
        if(strArr.length != BaseballGame.COUNT) {
            System.out.print("[ERROR]3자리의 숫자를 입력해주세요.");
            return false;
        }
        return true;
    }

    public boolean inValidation(String inputNum) {
        boolean isNumber = true;
        for(int i = 0; i < inputNum.length(); i++) {
            char tmp = inputNum.charAt(i);
            isNumber = verificationNumber(tmp);
        }
        return isNumber;
    }

    private boolean verificationNumber(char tmp) {
        return Character.isDigit(tmp);
    }

    public void choiceNumber() {
        boolean loop = true;
        while (loop) {
            loop = checkInputNumber(choiceQuestion());
        }
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
            System.out.println("[ERROR]1 또는 2를 정확히 입력해주세요!!");
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

    public boolean CheckStrikeCount(BaseBallScore bbs) {
        if(bbs.getStrike() == BaseballGame.COUNT){
            bbs.setSuccess(true);
            System.out.println("\n3개의 숫자를 모두 맞히셨습니다. 게임 끝!");
            return true;
        }
        return false;
    }

    public void printResult(BaseBallScore bbs) {
        int strike = bbs.getStrike();
        int ball = bbs.getBall();
        if(strike != 0) System.out.print(strike + "스트라이크");

        if(ball != 0) System.out.print(ball + "볼");

        if(strike == 0 && ball == 0) System.out.print("낫싱");
    }
}
