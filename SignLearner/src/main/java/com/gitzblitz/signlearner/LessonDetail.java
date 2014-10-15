package com.gitzblitz.signlearner;

import android.app.ActionBar;
import android.app.Activity;
import android.drm.DrmStore;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Switch;
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

         getActionBar().setDisplayHomeAsUpEnabled(true);

        /*find the buttons on the view*/
        back = (Button)findViewById(R.id.buttonBack);
        next = (Button)findViewById(R.id.buttonNext);


        Bundle bundle = getIntent().getExtras();

        screen = bundle.getParcelable("screen");
        position = bundle.getInt("position");

//        Log.i(LOGTAG, Integer.toString(position));
        listOfScreens = bundle.getParcelableArrayList("listOfScreens");

        
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
//           back.setEnabled(true);
           back.setVisibility(View.VISIBLE);
//           Log.i(LOGTAG, "Position "+ position);

           refreshDisplay();
       } else {
//           next.setEnabled(false);
           next.setVisibility(View.INVISIBLE);
       }
    }

    private void goBack() {

        if(position == 0){
//            back.setEnabled(false);
            back.setVisibility(View.INVISIBLE);
        } else {
            position = position -1;
            refreshDisplay();
//            back.setEnabled(true);
            back.setVisibility(View.VISIBLE);
        }

    }

    private void refreshDisplay() {
        Screen screen1 = listOfScreens.get(position);
        setTitle(screen1.getVidCaption());
        Log.i(LOGTAG, "Position "+ position);

        /*check the position to enable or disable the buttons*/
        if(position >= listOfScreens.size()){
//            next.setEnabled(false);
            next.setVisibility(View.INVISIBLE);
            Log.i(LOGTAG, "Position " + position);
        } else if(position == 0){
//            back.setEnabled(false);
            back.setVisibility(View.INVISIBLE);
        } else  {
//            back.setEnabled(true);
            back.setVisibility(View.VISIBLE);
//            next.setEnabled(true);
            next.setVisibility(View.VISIBLE);
        }


        String screenVideoURL = screen1.getVideoURL();
//        Log.i(LOGTAG,"Path to video "+ screenVideoURL);
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

        String image_path = screen1.getImagePath();

        if(!image_path.equals("No image")){
            File image = new File(Environment.getExternalStorageDirectory(),screen1.getImagePath());
//            Log.d(LOGTAG, image.getAbsolutePath());
            if(image.exists()){
           //     imageView.invalidate();
                imageView = (ImageView)findViewById(R.id.screenImageView);
                imageView.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
            }
        }


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
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        return;
    }
}
