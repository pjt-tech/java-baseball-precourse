package baseball.basic;

import baseball.BaseBallScore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BaseballGameTest {

    private BaseballGame bbg;
    private BaseBallScore bbs;

    @BeforeEach
    void beforeEach() {
        bbg = new BaseballGame();
        bbs = new BaseBallScore(false,0,0);
    }

    @Test
    @DisplayName("등록된 숫자의 길이는 반드시 3이어야 한다")
    void comNumberLength() {
        int[] randomArr = {1,2,3};
        int[] result = bbg.setComNumArr(randomArr);

        Assertions.assertThat(result.length).isEqualTo(3);
    }

    @Test
    @DisplayName("등록된 숫자의 길이는 반드시 3이어야 한다")
    void playerNumberLength() {
        String inputNum = "123";
        int[] playerNum = new int[3];
        int[] result = bbg.setPlayerNumArr(inputNum, playerNum);

        Assertions.assertThat(result.length).isEqualTo(3);
    }

    @Test
    @DisplayName("스트라이크는 몇개일까???")
    void getStrikeCount() {
        int[] comNumArr = {1,2,3};
        int[] playerNumArr = {1,5,3};

        BaseBallScore bbs = bbg.startBaseballGame(comNumArr,playerNumArr);

        Assertions.assertThat(bbs.getStrike()).isEqualTo(2);
    }

    @Test
    @DisplayName("볼은 몇개일까???")
    void getBallCount() {
        int[] comNumArr = {1,2,3};
        int[] playerNumArr = {1,3,2};

        BaseBallScore bbs = bbg.startBaseballGame(comNumArr,playerNumArr);

        Assertions.assertThat(bbs.getBall()).isEqualTo(2);
    }

}
