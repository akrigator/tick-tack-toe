public class Cell {
    private char value;
    final private char defaultValue = '\0';
    private Coordinate coordinate;

    Cell (int x, int y) {
        setValue(defaultValue);
        setCoordinate(new Coordinate(x, y));
    }

    public char getValue() {
        return value;
    }

    public boolean setValue(char value) {
        this.value = value;
        return value != defaultValue;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isEmpty() {
        return getValue() == defaultValue;
    }
}
