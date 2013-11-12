package com.gitzblitz.signlearner;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class SpecialKeysExamples extends Activity {

    private VideoView vid;
    Button back;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_keys_examples);

       /* if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/

        back = (Button)findViewById(R.id.btnSPExamplesBack);
        next = (Button)findViewById(R.id.btnSPExamplesNext);

//        Initialise the video

        File clip = new File(Environment.getExternalStorageDirectory(),"/SignLearner/O4/Special_Keys_examples.mp4");


        if(clip.exists()){

            vid = (VideoView)findViewById(R.id.videoViewSPExamples);
            MediaController media = new MediaController(this);
            media.setAnchorView(vid);
            vid.setMediaController(media);
            vid.setVideoPath(clip.getAbsolutePath());
            vid.requestFocus();
            vid.start();
            Toast.makeText(this, "Starting video", Toast.LENGTH_SHORT).show();
//            Log.i("tag", clip.getAbsolutePath());
        }
        else{

            Toast.makeText(this, clip.getAbsolutePath()+" cannot be found", Toast.LENGTH_LONG).show();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.special_keys_examples, menu);
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


    /*Functions to implement button*/

    private void goBack() {
        Intent i = new Intent(this,SpecialKeysIntro2.class);
        startActivity(i);

    }

    private void goNext(){
        Toast.makeText(this,"Next button clicked", Toast.LENGTH_LONG).show();
//        Intent i = new Intent(this,SpecialKeysExamples.class);
//        startActivity(i);
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
            View rootView = inflater.inflate(R.layout.fragment_special_keys_examples, container, false);
            return rootView;
        }
    }*/



}
