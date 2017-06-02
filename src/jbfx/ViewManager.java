package jbfx;

import javafx.scene.input.KeyCode;

/**
 * Keeps track of offset values, simulates moving camera
 */
public class ViewManager {
    private double offsetX;
    private double offsetY;
    private KeyStates states;

    public ViewManager(KeyStates states,double offsetX,double offsetY)
    {
        this.states = states;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public ViewManager(KeyStates states)
    {
        this(states,0,0);
    }

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

    public void moveX(double moveX)
    {
        offsetX+=moveX;
    }
    public void moveY(double moveY)
    {
        offsetY+=moveY;
    }
    public void move(double moveX,double moveY)
    {
        offsetX+=moveX;
        offsetY+=moveY;
    }

    public void offsetTo(double xCo,double yCo)
    {
        offsetX = xCo;
        offsetY = yCo;
    }
}
