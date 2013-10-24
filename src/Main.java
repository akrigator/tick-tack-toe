public class Main {
    public static void main (String[] arg) {
        final int fieldRows = 4;
        final int fieldColumns = 4;
        final int straightForWin = 3;

        GamePlay game = new GamePlay(fieldRows, fieldColumns, straightForWin);

        game.addPlayer('X');
        game.addPlayer('O');
        game.addPlayer('V');

        game.play();
    }
}
