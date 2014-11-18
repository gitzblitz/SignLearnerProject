package com.gitzblitz.signlearner;

import java.util.ArrayList;

/**
 * Created by User on 2014/05/13.
 */
public class Lesson {
    private String id;
    private String title;
    private String category;
    private ArrayList<Screen> screens;
    private Screen screen;


    public ArrayList<Screen> getScreens() {
        return screens;
    }


    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setScreens(ArrayList<Screen> screens) {
        this.screens = screens;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return title;
    }
}
