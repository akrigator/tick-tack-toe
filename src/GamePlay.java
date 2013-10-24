import java.util.ArrayList;

public class GamePlay {
    private ArrayList<Player> players;
    private Field field;

    GamePlay(int x, int y, int straight) {
        players = new ArrayList<Player>();
        field = new Field(x,y,straight);
    }

    public void addPlayer(char mark) {
        players.add(new Player(mark));
    }

    public void play() {
        field.show();
        while ( true ) {
            for (Player player: players) {
                System.out.println("Coordinates of " + player.getMark());
                while (!field.move(player)) {
                    System.out.println(player.getMark() + " - You are stupid sheep. Repeat a move, please");
                }
                field.show();
                Offset winCombination = field.findStraight(player);
                if (winCombination != null) {
                    System.out.println(player.getMark() + " wins by " + winCombination.getDirectionName());
                    return;
                }
                if (field.isFull()) {
                    System.out.println("Game over, losers");
                    return;
                }
            }
        }
    }
}
