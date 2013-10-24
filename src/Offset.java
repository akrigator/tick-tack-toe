public class Offset {

    private String directionName;
    private int xOffset;
    private int yOffset;

    Offset (int x, int y, String name) {
        setDirectionName(name);
        setXOffset(x);
        setYOffset(y);
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public int getXOffset() {
        return xOffset;
    }

    public void setXOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }
}
