package jbfx;

import javafx.animation.AnimationTimer;

/**
 * Created by miller5157r on 4/14/2017.
 */
public abstract class FrameHandler extends AnimationTimer {
    private static final int FPS = 120;
    private long lastRun = 0;

    public void handle(long now) {
        if(lastRun + 1000000000/FrameHandler.FPS < now ) {
            runPerTick(now);
            lastRun = now;
        }
    }
    public abstract void runPerTick(long now);
}
