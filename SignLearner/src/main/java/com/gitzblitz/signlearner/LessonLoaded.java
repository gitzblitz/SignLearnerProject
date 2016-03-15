package com.gitzblitz.signlearner;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by George Ng'ethe on 2014/05/13.
 * This class load a list of lesson objects and displays them by Lesson name
 * It takes a bundled object containing a parcelable ArrayList from
 */

public class LessonLoaded extends ListActivity {

    private static final String LOGTAG = "SIGNLEARNER_LESSONLOADED";
    String screen_title = null;
    ArrayList<Screen> screens = null;
    private int counter = 0;
    String section_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_loaded);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        screens = bundle.getParcelableArrayList("listOfScreens");
        screen_title = bundle.getString("lessonTitle");

        setTitle(screen_title);

        /*Instantiate the parser and the */

        if(screens.size() != 0){
            ArrayAdapter<Screen> adapter= new ArrayAdapter<Screen>(this,android.R.layout.simple_list_item_1,screens);
            setListAdapter(adapter);
        }else {
            Toast.makeText(this,"No lesson found",Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Screen s = (Screen) this.getListAdapter().getItem(position);
        section_title = s.toString();

        /*bundle to transfer data to the next activity*/
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        bundle.putParcelable("screen", s);
        bundle.putParcelableArrayList("listOfScreens", screens);
//
        Intent intent = new Intent(this,LessonDetail.class);
        intent.putExtras(bundle);
/*Start intent with the new list of screens*/
        startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lesson_loaded, menu);
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
