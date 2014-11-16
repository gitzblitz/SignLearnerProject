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

public class MainActivity extends Activity {

//    Button btnUnit;
    String file_path = "/SignSupport/icdl/videos/Welcome/Welcome screen.mp4";
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

//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
//        btnUnit = (Button)findViewById(R.id.BtnUnits);
        lessons = (Button)findViewById(R.id.BtnLessons);
        exit = (Button)findViewById(R.id.BtnExit);
            setTitle("Home");
//        btnUnit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goUnits();
//            }
//        });

        // load the introvideo into the video frame
        loadWelcomeVideo();

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

    private void loadWelcomeVideo() {

        File clip = new File(Environment.getExternalStorageDirectory(),file_path);

        if(clip.exists()){
            videoView = (VideoView)findViewById(R.id.welcomeVideoView);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.setVideoPath(clip.getAbsolutePath());
            videoView.requestFocus();
            videoView.start();
        }else{
            Toast.makeText(this, file_path +" cannot be found", Toast.LENGTH_LONG).show();
        }
    }


    //    private void goUnits(){
//       Intent i = new Intent(this,UnitList.class);
//       startActivity(i);
//    }
    private void exitApplication(){
//        super.onBackPressed();

        if(exitButtonCount >=1){
//            Toast.makeText(this,"Press the exit button once again to close the application.",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"Press the exit button once again to close the application.",Toast.LENGTH_SHORT).show();
            exitButtonCount++;
        }
    }

    private void goLessons(){
        Intent i = new Intent(this,LessonList.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
            Toast.makeText(this,"Press the back button once again to close the application.",Toast.LENGTH_SHORT).show();
            backButtonCount++;
           // super.onBackPressed();
            Log.d(LOGTAG, "back button count " +  Integer.toString(backButtonCount));

        }
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
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//    }

}
