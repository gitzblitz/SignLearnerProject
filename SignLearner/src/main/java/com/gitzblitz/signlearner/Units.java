package com.gitzblitz.signlearner;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George Ng'ethe on 2013/10/31.
 */
public class Units {
    private String title;
    private int id;
//    private ArrayList<String> lessonIDs;
    private ArrayList<String> lessonIDs = new ArrayList<String>();
    private ArrayList<String> lessons = new ArrayList<String>();
    private ArrayList<Screen> screens = new ArrayList<Screen>();


    public ArrayList<Screen> getScreens() {
        return screens;
    }

    public void setScreens(ArrayList<Screen> screens) {
        this.screens = screens;
    }

    public ArrayList<String> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<String> lessons) {
        this.lessons = lessons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getLessonIDs() {
        return lessonIDs;
    }

    public void setLessonIDs(ArrayList<String> lessonIDs) {
        this.lessonIDs = lessonIDs;
    }

    @Override
    public String toString(){
        return "Unit: "+ id + ": " + title;
    }


}
