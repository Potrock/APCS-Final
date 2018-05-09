package sample.UIElements.Page;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public abstract class PageElement {

    protected Group out;
    protected Rectangle bg;

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
