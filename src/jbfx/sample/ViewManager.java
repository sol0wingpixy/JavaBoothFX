package jbfx.sample;

/**
 * Created by miller5157r on 5/16/2017.
 */
public class ViewManager {
    public ViewManager(double offsetX,double offsetY)
    {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    private double offsetX;

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }

    private double offsetY;

}
