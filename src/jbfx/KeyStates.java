package jbfx;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Keeps track of which keys are pressed, internally - user accesses through Game
 */
public class KeyStates {//remembers which keys have been pressed
    private Map<KeyCode,Boolean> keysPressed = new HashMap<>();//each KeyCode is assosiated with true or false - pressed or not

    KeyStates()
    {
        for(KeyCode code:KeyCode.values())
        {
            keysPressed.put(code,false);//initialise all values to false
        }
    }
    void keyPressed(KeyCode code)
    {
        keysPressed.put(code,true);
    }
    void keyReleased(KeyCode code)
    {
        keysPressed.put(code,false);
    }
    boolean isKeyPressed(KeyCode code)
    {
        return keysPressed.get(code);
    }
}
