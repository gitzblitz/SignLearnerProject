package com.gitzblitz.signlearner;

/**
 * Created by User on 2014/05/13.
 */
public class Lesson {
    private String id;
    private String title;
    private String category;

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
        return id + " - "+ title;
    }
}
