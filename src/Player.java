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
        System.out.print("Column: ");
        int column = in.nextInt() - 1;
        System.out.print("Row: ");
        int row = in.nextInt() - 1;
        return new Coordinate(row, column);
    }
}
