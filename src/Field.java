import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Field {
    final private String line1 = "+---";
    final private String line2 = "| %c ";
    final private int straightMaxLength;

    private List<Cell> gameField;
    final private List<Integer> gameFieldSize;
    private List<Offset> rules;

    public List<Offset> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Offset> rules) {
        this.rules = rules;
    }

    Field(List<Integer> gameFieldSize, int straight) {
        this.gameFieldSize = gameFieldSize;
        int minStraight = Collections.min(getGameFieldSize());
        straightMaxLength = straight < minStraight ? straight : minStraight;
        setGameField(new ArrayList<Cell>());

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < fieldSize(); i++) {
            coordinates.add(new Coordinate());
        }

        for (int direction = 0; direction < getGameFieldSize().size(); direction++) {
            for (int repeat = 0; repeat < ( fieldSize() / repeater(direction) ); repeat++) {
                for (int iteration = 0; iteration < getGameFieldSize().get(direction); iteration++) {
                    for (int cell = 0; cell < repeater(direction + 1); cell++) {
                        int index = repeater(direction + 1) * ( repeat * getGameFieldSize().get(direction) + iteration ) + cell;
                        coordinates.get(index).getCoordinates().add(iteration);
                    }
                }
            }
        }

        for (Coordinate coordinate : coordinates) {
            getGameField().add(new Cell(coordinate.getCoordinates()));
        }

        setRules(new ArrayList<Offset>());
        getRules().add(new Offset(Arrays.asList(1, 0), "vertical straight"));
        getRules().add(new Offset(Arrays.asList(0, 1), "horizontal straight"));
        getRules().add(new Offset(Arrays.asList(1, 1), "right diagonal"));
        getRules().add(new Offset(Arrays.asList(-1, 1), "left diagonal"));
    }

    private int repeater(int start) {
        int repeatsCount = 1;
        for (int i=start; i < this.getGameFieldSize().size(); i++) {
            repeatsCount *= this.getGameFieldSize().get(i);
        }
        return repeatsCount;
    }

    private int fieldSize() {
        return repeater(0);
    }

    public List<Integer> getGameFieldSize() {
        return gameFieldSize;
    }

    public int getStraightMaxLength() {
        return straightMaxLength;
    }

    public List<Cell> getGameField() {
        return gameField;
    }

    public void setGameField(List<Cell> gameField) {
        this.gameField = gameField;
    }

    private Cell getCell(List<Integer> coordinates) {
        for (Cell cell: getGameField()) {
            if (cell.getCoordinate().getCoordinates().equals(coordinates)) {
                return cell;
            }
        }
        return null;
    }

    private boolean composeCellsValue(Coordinate c1, Coordinate c2) {
        Cell cell1 = getCell(c1.getCoordinates());
        Cell cell2 = getCell(c2.getCoordinates());
        return cell1 != null && cell2 != null && cell1.getValue() == cell2.getValue();
    }

    public void show () {
        System.out.println();
        for (int j=0; j < getGameFieldSize().get(1); j++) {
            drawLine();
            for (int i=0; i < getGameFieldSize().get(0); i++) {
                Cell cell = getCell(Arrays.asList(i, j));
                if (cell != null) {
                    System.out.format(line2, getCell(Arrays.asList(i, j)).getValue());
                } else {
                    System.out.format(line2, '%');
                }
            }
            System.out.println(line2.toCharArray()[0]);
        }
        drawLine();
    }

    private void drawLine() {
        for (int j = 0; j < getGameFieldSize().get(1); j++) {
            System.out.format(line1);
        }
        System.out.println(line1.toCharArray()[0]);
    }

    public boolean isFull() {
        int full = 0;
        for (Cell cell: getGameField()) {
            if (cell.isEmpty()) {
                full++;
            }
        }
        return full == fieldSize();
    }

    public boolean move(Player player) {
        Cell cell = getCell(player.move().getCoordinates());
        return cell != null && cell.isEmpty() && cell.setValue(player.getMark());
    }

    public Offset findStraight(Player player) {
        for (Offset offset: getRules()) {
            for (Cell cell: getGameField()) {
                if (cell.getValue() == player.getMark()
                        && cell.getCoordinate().getCoordinates().get(0) >= (offset.getOffset().get(0) * offset.getOffset().get(1) < 0 ? 1 : 0)
                        && cell.getCoordinate().getCoordinates().get(0) < getGameFieldSize().get(0) - (offset.getOffset().get(0) < 1 ? 0 : 1)
                        && cell.getCoordinate().getCoordinates().get(1) < getGameFieldSize().get(0) - (offset.getOffset().get(1) <= 0 ? 0 : 1)) {
                    int steps = 1;
                    Coordinate cRoot = new Coordinate(cell.getCoordinate().getCoordinates());
                    Coordinate cNext = cRoot.depose(offset);
                    while (composeCellsValue(cRoot, cNext)) {
                        steps++;
                        cRoot = cNext;
                        cNext = cRoot.depose(offset);
                        if (steps == getStraightMaxLength()) {
                            return offset;
                        }
                    }
                }
            }
        }
        return null;
    }
}
