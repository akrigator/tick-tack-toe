import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private char mark;

    Player (char mark) {
        setMark(mark);
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public Coordinate move() {
        Scanner in = new Scanner(System.in);
        List<Integer> coordinates = new ArrayList<Integer>();
        System.out.print("Column: ");
        coordinates.add(in.nextInt() - 1);
        System.out.print("Row: ");
        coordinates.add(in.nextInt() - 1);
        return new Coordinate(coordinates);
    }
}
