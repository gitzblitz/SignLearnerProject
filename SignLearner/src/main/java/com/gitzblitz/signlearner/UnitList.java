package com.gitzblitz.signlearner;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UnitList extends ListActivity {

//    Button home;
//    Button unit1;
//  private Vibrator vib;

    private static final String LOGTAG = "SIGNLEARNER_UNITLIST";
//    UnitsPullParser parser = null;
    public ArrayList<String> lessons = null;
//
//    ListView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.units_list);

        UnitsPullParser parser = new UnitsPullParser();
        List<Units> units = parser.getUnitsFromFile(this);
        ArrayAdapter<Units> adapter = new ArrayAdapter<Units>(this,android.R.layout.simple_list_item_1,units);
        setListAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.unit_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    @Override
    public void onBackPressed() {
//        return;

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
       // String s = v.getContext().toString();
        Units unit = (Units) l.getItemAtPosition(position);
//        Toast.makeText(l.getContext(), ((TextView)v).getText(), Toast.LENGTH_SHORT).show();

        String unit_name_passed = unit.getTitle();
        lessons = unit.getLessonIDs();

//        for(int i=0; i< lessons.size(); i++){
//            Log.i("Lesson ID " , unit_name_passed + " has " + lessons.get(i));
//        }

//        Toast.makeText(l.getContext(), unit_name_passed, Toast.LENGTH_SHORT).show();

//        Log.i(LOGTAG,unit_name_passed);

        Bundle bundle = new Bundle();
        bundle.putString("unit_title", unit_name_passed);
        bundle.putInt("unit_id", unit.getId());
        bundle.putStringArrayList("lessonIDs", lessons);

        Intent intent = new Intent(this,UnitLoaded.class);
        intent.putExtras(bundle);
//        startActivityForResult(intent,0);
        startActivity(intent);

    }
}
