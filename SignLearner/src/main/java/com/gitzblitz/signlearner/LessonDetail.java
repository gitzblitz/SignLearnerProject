package com.gitzblitz.signlearner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;


public class LessonDetail extends Activity {
    private VideoView videoView;
    Button back;
    Button next;

    Screen screen = null;
    int position = 0;
    private ImageView imageView;
    ArrayList<Screen> listOfScreens = null;


    private static final String LOGTAG = "LESSON_DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        /*find the buttons on the view*/
        back = (Button)findViewById(R.id.buttonBack);
        next = (Button)findViewById(R.id.buttonNext);



        Bundle bundle = getIntent().getExtras();

        screen = bundle.getParcelable("screen");
        position = bundle.getInt("position");

        Log.i(LOGTAG, Integer.toString(position));
        listOfScreens = bundle.getParcelableArrayList("listOfScreens");

//        for(Screen screen1: listOfScreens){
//            Log.i(LOGTAG,"Screen " + screen1.getScreenID());
//        }
        
        refreshDisplay();

        /*listeners for the button events*/
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext();
            }
        });




    }

    private void goNext() {
        /*check if button has reached the end of the list. If not increase the position and refresh display else disable next button*/
       if (position < listOfScreens.size()-1){
           position = position + 1;
           back.setEnabled(true);
//           Log.i(LOGTAG, "Position "+ position);

           refreshDisplay();
       } else {
           next.setEnabled(false);
       }
    }

    private void goBack() {

        if(position == 0){
            back.setEnabled(false);
        } else {
            position = position -1;
            refreshDisplay();
            back.setEnabled(true);
        }

    }

    private void refreshDisplay() {

       // ArrayList<Screen> temp = listOfScreens;
        Screen screen1 = listOfScreens.get(position);
        setTitle(screen1.getVidCaption());
        Log.i(LOGTAG, "Position "+ position);

        /*check the position to enable or disable the buttons*/
        if(position == listOfScreens.size()){
            next.setEnabled(false);
        } else if(position == 0){
            back.setEnabled(false);
        } else  {
            back.setEnabled(true);
            next.setEnabled(true);
        }

     //   int pos  = position;

      //  screen1 = listOfScreens.get(pos);

        String screenVideoURL = screen1.getVideoURL();
        Log.i(LOGTAG,"Path to video "+ screenVideoURL);
        File clip = new File(Environment.getExternalStorageDirectory(),screenVideoURL);

        if(clip.exists()){
            Log.d(LOGTAG, clip.getAbsolutePath() +" exists.");
            videoView = (VideoView)findViewById(R.id.lessonDetailVideoView);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            videoView.setMediaController(mediaController);
            videoView.setVideoPath(clip.getAbsolutePath());
            videoView.requestFocus();
            videoView.start();

        }else {
            Toast.makeText(this, screenVideoURL +" cannot be found", Toast.LENGTH_LONG).show();
        }

        TextView textView = (TextView)findViewById(R.id.vidCaptiontextView);
        textView.setText(screen1.getVidCaption());

//        Toast.makeText(this,"video path: "+screenVideoURL, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lesson_detail, menu);
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
