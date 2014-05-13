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

import java.util.ArrayList;

public class LessonList extends ListActivity {

    private static final String LOGTAG = "SIGNLEARNER_LESSONLIST";
    String lesson_title = null;
    ArrayList<Lesson> lessons = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        LessonPullParser pullParser = new LessonPullParser();
        lessons = pullParser.parseXML(this);

        setTitle("Lessons");


        ArrayAdapter<Lesson> arrayAdapter = new ArrayAdapter<Lesson>(this, android.R.layout.simple_list_item_1,lessons);
        setListAdapter(arrayAdapter);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Lesson lesson = (Lesson) this.getListAdapter().getItem(position);

        lesson_title = lesson.getTitle();

        String lessonId = lesson.getId();

        Log.d(LOGTAG, "lesson id: "+ lessonId + " lesson title " + lesson_title);

        Bundle b = new Bundle();
        b.putString("lessonID", lessonId);

        Intent intent = new Intent(this, LessonLoaded.class);
        intent.putExtras(b);

        startActivity(intent);

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

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_lesson_list, container, false);
            return rootView;
        }
    }*/

}
