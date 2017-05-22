package jbfx;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Family on 5/20/17.
 */
public class GameInfo {
    private String text;
    private Text node;
    public GameInfo(Game game)
    {
        node = new Text();
        update(game);
    }
    public void update(Game game)
    {
        text = "";
        List<Sprite> sprites = game.getSprites();
        for(Sprite sprite:sprites)
        {
            text += sprite.getClass().getSimpleName()+"\n";
            text += "X offset: "+sprite.getOffsetX()+"\n";
            text += "Y offset: "+sprite.getOffsetY()+"\n";
        }
        node.setText(text);
    }
    public Node getNode()
    {
        return node;
    }
}
