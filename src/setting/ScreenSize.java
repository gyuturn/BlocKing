package setting;

public class ScreenSize {
    /*
    *default값은 400*500
    *
     */

    private int width;

    private int height;

    public ScreenSize() {
        this.width = 400;
        this.height=500;
    }

    public ScreenSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
