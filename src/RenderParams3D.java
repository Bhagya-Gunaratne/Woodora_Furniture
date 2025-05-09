public class RenderParams3D {
    private float[] topColor = new float[]{1,1,1};
    private float[] legColor = new float[]{1,1,1};
    private float[] roomDim = new float[]{1f,0.8f};
    private String roomId = "1";
    float ScaleVal = 1f;

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

    public RenderParams3D() {
        roomColors = new float[]{0.8196f, 0.7411f, 0.6352f};
    }

    public void setLegColor(float[] legColor) {
        this.legColor = legColor;
    }
}
