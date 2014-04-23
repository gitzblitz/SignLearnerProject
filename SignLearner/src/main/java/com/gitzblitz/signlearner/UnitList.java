package com.gitzblitz.signlearner;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UnitList extends ListActivity {

//    Button home;
//    Button unit1;
//  private Vibrator vib;

    private static final String LOGTAG = "SIGNLEARNER";
//    UnitsPullParser parser = null;
//    List<Units> units = null;
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





//        home = (Button) findViewById(R.id.btnHome);
//        unit1 = (Button)findViewById(R.id.btnUnit1);
//
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//             //   vib.vibrate(100);
//                // method call to go back to home
//                goHome();
//            }
//        });

//        unit1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goITBasics();
//            }
//        });
    }




//    private void goHome(){
//        Intent i = new Intent(this,MainActivity.class);
//        startActivity(i);
//    }
//    private void goITBasics(){
//     /*   Intent i = new Intent(this,SpecialKeysIntro.class);
//        startActivity(i);*/
//        Toast.makeText(this, "Unit 1 - IT Basics button pressed", Toast.LENGTH_SHORT).show();
//    }


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
        Toast.makeText(l.getContext(), unit.getTitle(), Toast.LENGTH_SHORT).show();

        Log.i(LOGTAG,unit.getTitle());

    }
}
