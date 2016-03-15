package com.gitzblitz.signlearner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by George Ng'ethe.
 * Main activity of the application.
 */
public class MainActivity extends Activity {



    private static final String WELCOME_VIDEO_FILE_PATH = "/SignSupport/icdl/videos/Welcome/Welcome screen.mp4";
    Button lessons;
    Button exit;
    private VideoView videoView;
    int exitButtonCount =0;
    int backButtonCount =0;
    private static final String LOGTAG = "SIGNLEARNER_MAIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lessons = (Button)findViewById(R.id.BtnLessons);
        exit = (Button)findViewById(R.id.BtnExit);
            setTitle("Home");

        // load the introduction video into the video frame
        loadWelcomeVideo();


        /*assign on click listeners to the buttons*/
        lessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLessons();
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                //go to function
                exitApplication();
            }
        });

    }
/*Load the intro video from the phone memory*/
    private void loadWelcomeVideo() {

        File clip = new File(Environment.getExternalStorageDirectory(), WELCOME_VIDEO_FILE_PATH);

        if(clip.exists()){
            videoView = (VideoView)findViewById(R.id.welcomeVideoView);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.setVideoPath(clip.getAbsolutePath());
            videoView.requestFocus();
            videoView.start();
        }else{
            Toast.makeText(this, WELCOME_VIDEO_FILE_PATH +" cannot be found", Toast.LENGTH_LONG).show();
        }
    }

/*This method exits the application using a double tap on the exit button*/

    private void exitApplication(){
//        super.onBackPressed();

        if(exitButtonCount >=1){

            finish();
        }else{
            Toast.makeText(this,"Press the exit button once again to close the application.",Toast.LENGTH_SHORT).show();
            exitButtonCount++;
        }
    }

    private void goLessons(){
        Intent i = new Intent(this,LessonList.class);

        startActivity(i);
//        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public void onBackPressed() {
        if(backButtonCount >=1){
            Log.d(LOGTAG,  Integer.toString(backButtonCount));
         finish();
//            super.onBackPressed();
        }
        else{
            Toast.makeText(this,"Press the back button again to close the application.",Toast.LENGTH_SHORT).show();
            backButtonCount++;
           // super.onBackPressed();
            Log.d(LOGTAG, "back button count " +  Integer.toString(backButtonCount));

        }
    }




}
