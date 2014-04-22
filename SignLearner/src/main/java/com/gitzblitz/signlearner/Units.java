package com.gitzblitz.signlearner;



/**
 * Created by George Ng'ethe on 2013/10/31.
 */
public class Units {
    private String title;
    private int id;
//    private ArrayList<String> lessonIDs;


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

//    public ArrayList<String> getLessonIDs() {
//        return lessonIDs;
//    }
//
//    public void setLessonIDs(ArrayList<String> lessonIDs) {
//        this.lessonIDs = lessonIDs;
//    }

    @Override
    public String toString(){
        return "Unit: "+ id + "\n Title: " + title;
    }
}
