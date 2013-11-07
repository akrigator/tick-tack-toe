import java.util.ArrayList;
import java.util.List;

public class Coordinate {
    private List<Integer> coordinates;

    Coordinate () {
        coordinates = new ArrayList<Integer>();
    }

    Coordinate (List<Integer> coordinates) {
        setCoordinates(coordinates);
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinate depose(Offset offset) {
        ArrayList<Integer> c = new ArrayList<Integer>();
        for (int i = 0; i < getCoordinates().size(); i++) {
            c.add( getCoordinates().get(i) + offset.getOffset().get(i) );
        }
        return new Coordinate(c);
    }
}
