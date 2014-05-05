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
