package com.gitzblitz.signlearner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.VideoView;

import java.util.ArrayList;


public class LessonDetail extends Activity {
    private VideoView videoView;
    Screen screen = null;
//    int position = 0;
    private ImageView imageView;


    private static final String LOGTAG = "LESSON_DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        Bundle bundle = getIntent().getExtras();

        screen = bundle.getParcelable("screen");
        int position = bundle.getInt("position");

        Log.i(LOGTAG, Integer.toString(position));
        ArrayList<Screen> listOfScreens = bundle.getParcelableArrayList("listOfScreens");

        for(Screen screen1: listOfScreens){
            Log.i(LOGTAG,"Screen " + screen1.getScreenID());
        }
        
        refreshDisplay(screen);

    }

    private void refreshDisplay(Screen screen) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lesson_detail, menu);
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
}
