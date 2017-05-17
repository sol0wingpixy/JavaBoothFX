package jbfx;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miller5157r on 5/3/2017.
 */
public class KeyStates {//remembers which keys have been pressed
    private Map<KeyCode,Boolean> keysPressed = new HashMap<>();//each KeyCode is assosiated with true or false - pressed or not
    public KeyStates()
    {
        for(KeyCode code:KeyCode.values())
        {
            keysPressed.put(code,false);//initialise all values to false
        }
    }
    public void keyPressed(KeyCode code)
    {
        keysPressed.put(code,true);
    }
    public void keyReleased(KeyCode code)
    {
        keysPressed.put(code,false);
    }
    public boolean isKeyPressed(KeyCode code)
    {
        return keysPressed.get(code);
    }
}
