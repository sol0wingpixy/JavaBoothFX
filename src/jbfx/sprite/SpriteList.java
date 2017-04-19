package jbfx.sprite;

import java.util.ArrayList;

/**
 * Created by miller5157r on 4/14/2017.
 */
public class SpriteList {
    private ArrayList<Sprite> list;

    public SpriteList() {
        list = new ArrayList<>();
    }

    public void add(Sprite in) {
        list.add(in);
    }

    public ArrayList<Sprite> getList() {
        return list;
    }
}
