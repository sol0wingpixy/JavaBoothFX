package jbfx.animation;

import javafx.animation.AnimationTimer;

/**
 * Created by miller5157r on 4/14/2017.
 */
public abstract class FrameHandler extends AnimationTimer {
    private int fps = 120;
    private long lastRun = 0;

    public void handle(long now) {
        if(lastRun + 1000000000/fps > now) {
            runPerTick(now);
            lastRun = now;
        }
    }

    public abstract void runPerTick(long now);

    public void setFPS(int fps) {
        this.fps = fps;
    }
}
