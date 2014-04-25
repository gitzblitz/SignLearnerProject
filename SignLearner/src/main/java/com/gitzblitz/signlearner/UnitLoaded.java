package com.gitzblitz.signlearner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class UnitLoaded extends Activity {

    private static final String LOGTAG = "SIGNLEARNER_UNITONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_loaded);

        //
        Intent i = getIntent();

        Bundle bundle = i.getExtras();

        String unit_title = bundle.getString("unit_title");
        int unit_id = bundle.getInt("unit_id");
        setTitle(unit_title);

//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);



        Log.i(LOGTAG, unit_title);

        Toast.makeText(this, unit_title + " " + unit_id, Toast.LENGTH_SHORT).show();



//        System.out.println(unit_title);
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
