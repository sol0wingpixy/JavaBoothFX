package jbfx;

import javafx.scene.input.KeyCode;

/**
 * Created by miller5157r on 5/16/2017.
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

    public void tick()
    {
        if(states.isKeyPressed(KeyCode.I))
        {
            offsetY += 3;
        }
        if(states.isKeyPressed(KeyCode.J))
        {
            offsetX += 3;
        }
        if(states.isKeyPressed(KeyCode.K))
        {
            offsetY -= 3;
        }
        if(states.isKeyPressed(KeyCode.L))
        {
            offsetX -= 3;
        }
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


}
