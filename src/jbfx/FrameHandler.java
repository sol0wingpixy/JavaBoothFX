package jbfx;

import javafx.animation.AnimationTimer;

public abstract class FrameHandler extends AnimationTimer {
    private static final int FPS = 120;
    private long lastRun = 0;

    public void handle(long now) {
        if (lastRun + 1000000000 / FrameHandler.FPS < now ) {
            runPerTick(now);
            lastRun = now;
        }
    }
    public abstract void runPerTick(long now);
}
