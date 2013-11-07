import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main (String[] arg) {
        final List<Integer> fieldSize = Arrays.asList(4, 4);
        final int straightForWin = 3;

        GamePlay game = new GamePlay(fieldSize, straightForWin);

        game.addPlayer('X');
        game.addPlayer('O');
        game.addPlayer('V');

        game.play();
    }
}
