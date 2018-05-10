package sample.uiElements.page;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public abstract class PageElement {

    Group out;
    private Rectangle bg;

    public PageElement(){
        this.bg = new Rectangle(300, 200);
        this.bg.setId("bg");
        this.out = new Group(bg);
    }

    abstract void setStyles();

    public Group get(){
        return out;
    }

}
