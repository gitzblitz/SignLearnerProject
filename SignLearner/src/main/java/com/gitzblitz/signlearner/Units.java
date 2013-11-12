package com.gitzblitz.signlearner;

/**
 * Created by User on 2013/10/31.
 */
public class Units {
    private String title;
    private String id;


    public String getTitle(){
        return title;
    }
    public String getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return "Unit [id="+ id + ", title=" + title + "]";
    }
}
