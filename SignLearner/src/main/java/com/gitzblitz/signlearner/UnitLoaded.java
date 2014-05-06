package com.gitzblitz.signlearner;

import android.app.Activity;
import android.content.Intent;
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


public class UnitLoaded extends Activity {

    private static final String LOGTAG = "SIGNLEARNER_UNITLOADED";
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_loaded);

        lv = (ListView)findViewById(R.id.unitLoadedListview);
        //
        Intent i = getIntent();

        Bundle bundle = i.getExtras();

        String unit_title = bundle.getString("unit_title");
        int unit_id = bundle.getInt("unit_id");
        ArrayList<String> lessons = bundle.getStringArrayList("lessonIDs");
        setTitle(unit_title);

//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        Toast.makeText(this, unit_title + " ID: " + unit_id, Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lessons);
        lv.setAdapter(arrayAdapter);

//        Log.i(LOGTAG, unit_title);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = (String) adapterView.getItemAtPosition(i);

                Toast.makeText(adapterView.getContext(), id, Toast.LENGTH_SHORT).show();
            }
        });

        for(int k=0; k< lessons.size(); k++){
            Log.i(LOGTAG , unit_title + " has " + lessons.get(k));
        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.unit_loaded, menu);
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
