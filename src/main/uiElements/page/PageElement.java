package main.uiElements.page;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * Abstract class as a template for the pages within the application
 *
 * @author sawyertang
 */
public abstract class PageElement {

    Group out;

    /**
     * Constructor of the Page: inits the background and sets the size of the Page
     */
    public PageElement() {
        Rectangle bg = new Rectangle(350, 350);
        bg.setId("bg");
        this.out = new Group(bg);
    }

    /**
     * abstract method sets the .css stylesheets for the Page
     */
    abstract void setStyles();

    /**
     * gets the page UI elements
     *
     * @return The Group of nodes that are a Page
     */
    public Group get() {
        return out;
    }

}
