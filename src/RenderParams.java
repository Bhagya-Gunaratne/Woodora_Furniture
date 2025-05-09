public class RenderParams {
private float[] topColor = new float[]{1,1,1};
private float[] legColor = new float[]{1,1,1};
private float[] roomDim = new float[]{1.2f,0.9f};
private String roomId = "1";
float ScaleVal = 0.5f;

private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getScaleVal() {
        return ScaleVal;
    }

    public void setScaleVal(float scaleVal) {
        ScaleVal = scaleVal;
    }

    private float[] roomColors = new float[]{1f,1f,1f};

    public float[] getRoomColors() {
        return roomColors;
    }

    public void setRoomColors(float[] roomColors) {
        this.roomColors = roomColors;
    }

    public float[] getRoomDim() {
        return roomDim;
    }

    public void setRoomDim(float[] roomDim) {
        this.roomDim = roomDim;
    }
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public float[] getTopColor() {
        return topColor;
    }

    public void setTopColor(float[] topColor) {
        this.topColor = topColor;
    }

    public float[] getLegColor() {
        return legColor;
    }

    public void setLegColor(float[] legColor) {
        this.legColor = legColor;
    }
}
