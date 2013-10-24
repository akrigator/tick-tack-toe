import java.util.ArrayList;

public class Field {
    final private String line1 = "+---";
    final private String line2 = "| %c ";
    final private int straightMaxLength;

    private ArrayList<ArrayList<Cell>> gameField;
    private ArrayList<Offset> rules;
    private int x;
    private int y;

    public ArrayList<Offset> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Offset> rules) {
        this.rules = rules;
    }

    Field(int x, int y, int straight) {
        setX(x);
        setY(y);

        int maxLength = getX() > getY() ? getX() : getY();
        straightMaxLength = straight > maxLength ? maxLength : straight;

        setGameField(new ArrayList<ArrayList<Cell>>());
        for (int i=0; i<getX(); i++) {
            getGameField().add(new ArrayList<Cell>());
            for (int j=0; j<getY(); j++) {
                getGameField().get(i).add(new Cell(i, j));
            }
        }

        setRules(new ArrayList<Offset>());
        getRules().add(new Offset(1, 0, "vertical straight"));
        getRules().add(new Offset(0, 1, "horizontal straight"));
        getRules().add(new Offset(1, 1, "right diagonal"));
        getRules().add(new Offset(-1, 1, "left diagonal"));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStraightMaxLength() {
        return straightMaxLength;
    }

    public ArrayList<ArrayList<Cell>> getGameField() {
        return gameField;
    }

    public void setGameField(ArrayList<ArrayList<Cell>> gameField) {
        this.gameField = gameField;
    }

    private Cell getCell(int x, int y) {
        if ( x >= 0 && x < getX() && y >= 0 && y < getY()) {
            return gameField.get(x).get(y);
        } else {
            return null;
        }
    }

    private boolean composeCellsValue(Coordinate c1, Coordinate c2) {
        Cell cell1 = getCell(c1.getX(), c1.getY());
        Cell cell2 = getCell(c2.getX(), c2.getY());
        return cell1 != null && cell2 != null && cell1.getValue() == cell2.getValue();
    }

    public void show () {
        System.out.println();
        for (ArrayList<Cell> array: gameField) {
            drawLine();
            for (Cell cell: array) {
                System.out.format(line2, cell.getValue());
            }
            System.out.println(line2.toCharArray()[0]);
        }
        drawLine();
    }

    private void drawLine() {
        for (int j = 0; j < getY(); j++) {
            System.out.format(line1);
        }
        System.out.println(line1.toCharArray()[0]);
    }

    public boolean isFull() {
        int full = 0;
        for (ArrayList<Cell> array: gameField) {
            for (Cell cell: array) {
                if (cell.isEmpty()) {
                    full++;
                }
            }
        }
        return full == getX() * getY();
    }

    public boolean move(Player player) {
        Coordinate coordinate = player.move();
        Cell cell = getCell(coordinate.getX(), coordinate.getY());
        return cell != null && cell.isEmpty() && cell.setValue(player.getMark());
    }

    public Offset findStraight(Player player) {
        for (Offset offset: getRules()) {
            for (ArrayList<Cell> array: getGameField()) {
                for (Cell cell: array) {
                    if (cell.getValue() == player.getMark()
                            && cell.getCoordinate().getX() >= (offset.getXOffset() * offset.getYOffset() < 0 ? 1 : 0)
                            && cell.getCoordinate().getX() < getX() - (offset.getXOffset() < 1 ? 0 : 1)
                            && cell.getCoordinate().getY() < getY() - (offset.getYOffset() <= 0 ? 0 : 1)) {
                        int steps = 1;
                        Coordinate cRoot = new Coordinate(cell.getCoordinate().getX(), cell.getCoordinate().getY());
                        Coordinate cNext = cRoot.Offset(offset);
                        while (composeCellsValue(cRoot, cNext)) {
                            steps++;
                            cRoot = cNext;
                            cNext = cRoot.Offset(offset);
                            if (steps == getStraightMaxLength()) {
                                return offset;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
