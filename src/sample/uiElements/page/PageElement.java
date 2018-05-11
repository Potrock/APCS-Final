package sample.uiElements.page;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public abstract class PageElement {

    Group out;

    public PageElement(){

        Rectangle bg = new Rectangle(350, 350);
        bg.setId("bg");
        this.out = new Group(bg);
    }

    abstract void setStyles();

    public Group get(){
        return out;
    }

}
