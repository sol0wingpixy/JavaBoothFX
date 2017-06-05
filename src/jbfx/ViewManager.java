package jbfx;

/**
 * Keeps track of offset values, simulates moving camera
 */
public class ViewManager {
    private double offsetX;
    private double offsetY;

    /**
     * Defaults offset variables
     * @param offsetX default offset in X axis
     * @param offsetY default offset in Y axis
     */
    public ViewManager(double offsetX,double offsetY)
    {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * Defaults to offset of 0,0 - no offset
     */
    public ViewManager()
    {}

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

    /**
     * Changes the X offset by sent value.
     * @param moveX changes X offset by value
     */
    public void moveX(double moveX)
    {
        offsetX+=moveX;
    }
    /**
     * Changes the Y offset by sent value.
     * @param moveY changes Y offset by value
     */
    public void moveY(double moveY)
    {
        offsetY+=moveY;
    }

    /**
     * Changes X and Y offset by sent values.
     * @param moveX changes X offset by value
     * @param moveY changes Y offset by value
     */
    public void move(double moveX,double moveY)
    {
        offsetX+=moveX;
        offsetY+=moveY;
    }

    /**
     * Sets X and Y offset to sent values
     * @param xCo New value of X offset
     * @param yCo New value of Y offset
     */
    public void offsetTo(double xCo,double yCo)
    {
        offsetX = xCo;
        offsetY = yCo;
    }
}
