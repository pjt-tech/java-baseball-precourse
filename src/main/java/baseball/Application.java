package baseball;

import baseball.basic.BaseballGameController;

public class Application {

    public static void main(String[] args) {
        BaseballGameController controller = new BaseballGameController();
        controller.gameControl();
    }
}
