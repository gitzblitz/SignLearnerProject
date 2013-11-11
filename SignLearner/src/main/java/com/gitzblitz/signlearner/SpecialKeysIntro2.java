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
import android.widget.Toast;
import android.widget.VideoView;

public class SpecialKeysIntro2 extends Activity {

    private VideoView vid;
    Button back;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_keys_intro2);

       /* if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
        onBackPressed();
        back = (Button)findViewById(R.id.BtnBack);
        next = (Button)findViewById(R.id.BtnNext);

        /* Load the video from file*/



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                goNext();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.special_keys_intro2, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
   /* public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_special_keys_intro2, container, false);
            return rootView;
        }
    }*/


    /*Implementation of the buttons*/
    private void goBack()
    {
        Intent i = new Intent(this,SpecialKeysIntro.class);
        startActivity(i);
        Toast.makeText(this,"Back button pressed ", Toast.LENGTH_LONG).show();
    }


    /*private void goNext(){
        Intent i = new Intent(this,SpecialKeysIntro2.class);

        startActivity(i);
         Toast.makeText(this,"Next button pressed ", Toast.LENGTH_LONG).show();
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    @Override
    public void onBackPressed() {
        return;
    }

}
