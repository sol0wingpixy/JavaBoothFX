package jbfx;

/**
 * Thrown if Game tries to remove a Sprite that doesn't exist. Shouldn't be thrown anymore.
 */
public class NoSuchSpriteException extends Exception {
    public NoSuchSpriteException(){}
    public NoSuchSpriteException(String message)
    {
        super(message);
    }
}
