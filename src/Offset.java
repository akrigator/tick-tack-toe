import java.util.List;

public class Offset {

    private String directionName;
    private List<Integer> offset;

    Offset(List<Integer> offset, String name) {
        setDirectionName(name);
        setOffset(offset);
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public void setOffset(List<Integer> offset) {
        this.offset = offset;
    }

    public List<Integer> getOffset() {
        return offset;
    }
}
