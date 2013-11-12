package com.gitzblitz.signlearner;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

public class UnitList extends Activity {

    Button home;
    Button unit1;
  //  private Vibrator vib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.units_list);

//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }

      //  vib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        home = (Button) findViewById(R.id.btnHome);
        unit1 = (Button)findViewById(R.id.btnUnit1);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   vib.vibrate(100);
                // method call to go back to home
                goHome();
            }
        });

        unit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goITBasics();
            }
        });
    }

    private void goHome(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    private void goITBasics(){
     /*   Intent i = new Intent(this,SpecialKeysIntro.class);
        startActivity(i);*/
        Toast.makeText(this, "Unit 1 - IT Basics button pressed", Toast.LENGTH_SHORT).show();
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
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_units, container, false);
//            return rootView;
//        }
//    }


    @Override
    public void onBackPressed() {
        return;
    }
}
