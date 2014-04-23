package com.gitzblitz.signlearner;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
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

public class SpecialKeysIntro extends Activity {

    private VideoView vid;
    Button back;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialkeys_intro);

        onBackPressed();
        back = (Button)findViewById(R.id.btnBack);
        next = (Button)findViewById(R.id.btnNext);

       File clip = new File(Environment.getExternalStorageDirectory(),"/SignLearner/O4/Special_keys_introduction.mp4");


        if(clip.exists()){

            vid = (VideoView)findViewById(R.id.videoView);
            MediaController media = new MediaController(this);
            media.setAnchorView(vid);
            vid.setMediaController(media);
            vid.setVideoPath(clip.getAbsolutePath());
            vid.requestFocus();
            vid.start();
            Toast.makeText(this, "Starting video", Toast.LENGTH_SHORT).show();
            Log.i("tag", clip.getAbsolutePath());
          }
        else{

            Toast.makeText(this, clip.getAbsolutePath()+" cannot be found", Toast.LENGTH_LONG).show();
        }

       // listener on defined back button on the layout

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

    public boolean checkExternalStorage()
    {
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)){
            return true;

        } else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            Toast.makeText(this, "External storage is read-only", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "External storage is unavailable", Toast.LENGTH_LONG).show();
        }

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.special_keys_intro, menu);
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

    private void goBack(){
        Intent i = new Intent(this, LessonList.class);
        startActivity(i);
    }

    private void goNext(){
        Intent i = new Intent(this,SpecialKeysIntro2.class);
        startActivity(i);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    @Override
    public void onBackPressed() {
        return;
    }
}
