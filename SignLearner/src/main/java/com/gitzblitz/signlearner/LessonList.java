package com.gitzblitz.signlearner;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class loads a list of lesson parsed from XML data in the CoursePullParser
 */
public class LessonList extends ListActivity {

    private static final String LOGTAG = "SIGNLEARNER_LESSONLIST";
    String lesson_title = null;
    ArrayList<Lesson> new_lessons = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);
        /*Initialise the screen widgets*/
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*Initialize the XMLPullParser object and set it to load the new_lessons array list*/
        CoursePullParser coursePullParser = new CoursePullParser();
        new_lessons = coursePullParser.parse(this);

        /*Confirm the list of lessons loaded*/
         Log.d(LOGTAG, " lesson list size= "+ Integer.toString(new_lessons.size()));

        /*Set the title of the screen Activity*/
        setTitle("Lessons");

        /*Loop through the Lesson list and set the list to the ArrayAdapter*/
        if(new_lessons.size() != 0){
            ArrayAdapter<Lesson> arrayAdapter = new ArrayAdapter<Lesson>(this, android.R.layout.simple_list_item_1,new_lessons);
            setListAdapter(arrayAdapter);
        }else {
            Toast.makeText(this, "No lesson found", Toast.LENGTH_SHORT).show();
            Log.d(LOGTAG,"No lesson found");
            finish();
        }

    }

    /*When an lesson is clicked get the */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Lesson lesson = (Lesson) this.getListAdapter().getItem(position);

        lesson_title = lesson.getTitle();

        String lessonId = lesson.getId();
        Log.d(LOGTAG," The lesson clicked is " + lesson_title);

        try {

            ArrayList<Screen> scr = lesson.getScreens();

            if (scr.isEmpty()) {
                /*If listed lesson does not contain any screen (lesson sections) objects*/
                Toast.makeText(this, "No lesson Found", Toast.LENGTH_SHORT).show();
                Log.d(LOGTAG, "No screens found for "+ lesson.getTitle());
            } else {
                /*Log size of lesson loaded for debugging purposes*/
                Log.d(LOGTAG, "size of the list of screens= " + Integer.toString(scr.size()));
                Log.d(LOGTAG, "size of the list of lessons= " + Integer.toString(new_lessons.size()));


                Log.d(LOGTAG, "lesson id: " + lessonId + " lesson title " + lesson_title);
                /*Create a new bundle to send the list of lesson sections to LessonLoaded activity*/
                Bundle b = new Bundle();
                b.putParcelableArrayList("listOfScreens", scr);
                b.putString("lessonTitle", lesson_title);

                Intent intent = new Intent(this, LessonLoaded.class);
                intent.putExtras(b);
                //
                startActivity(intent);
            }
        }catch (NullPointerException e){
            e.getMessage();
            Toast.makeText(this, "No lesson Found", Toast.LENGTH_SHORT).show();
            Log.d(LOGTAG, "No screens found for "+ lesson.getTitle());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lesson_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*Functions for buttons*/

    @Override
    public void onBackPressed() {
        return;
    }



}
