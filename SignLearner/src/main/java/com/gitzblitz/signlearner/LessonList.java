package com.gitzblitz.signlearner;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;

public class LessonList extends Activity {

    Button goHome;
    Button specialKeysLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);


       /* if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/

        goHome = (Button)findViewById(R.id.btnGoHomeLessons);
        specialKeysLessons = (Button)findViewById(R.id.btnSpecialKeys);


        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateHomefromLessonList();
            }
        });

        specialKeysLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSpecialKeysLesson();
            }
        });
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

    private void navigateHomefromLessonList(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    private void goToSpecialKeysLesson(){
        Intent i = new Intent(this, SpecialKeysIntro.class);
        startActivity(i);
    }

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
