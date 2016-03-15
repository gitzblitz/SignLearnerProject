package com.gitzblitz.signlearner;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class LessonsActivity extends Activity {


    ArrayList<Lesson> lessons = null;
    ListView listView;

private static final String LOGTAG = "LESSONS_ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Lessons");
        CoursePullParser coursePullParser = new CoursePullParser();
        lessons = coursePullParser.parse(this);
        Log.d(LOGTAG, " lesson list size= " + Integer.toString(lessons.size()));

        listView = (ListView)findViewById(R.id.lessonsListView);

        if(lessons.size() != 0){
            ArrayAdapter<Lesson> arrayAdapter = new ArrayAdapter<Lesson>(this, android.R.layout.simple_list_item_1,lessons);
//            setListAdapter(arrayAdapter);
           listView.setAdapter(arrayAdapter);

        }else {
            Toast.makeText(this, "No lesson found", Toast.LENGTH_SHORT).show();
            Log.d(LOGTAG,"No lesson found");
            finish();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lessons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
