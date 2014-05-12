package com.gitzblitz.signlearner;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by User on 2014/05/11.
 */
public class ScreenPullParser {

    private static final String LOGTAG = "SCREEN_PARSER";

    private static final String SCREEN_ID = "screenID";
    private static final String VIDEO_FRAME = "video_frame";
    private static final String VIDEO_CAPTION ="video_caption";
    private static final String IMAGEPATH = "image";

    private static final String BASE = "/SignSupport/icdl/xml/";
    private String filepath;

    private Screen currentScreen = null;
    private String currentTag = null;
    ArrayList<Screen> screens = new ArrayList<Screen>();

    public ArrayList<Screen> parseXML(Context context, String file_prefix){

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            String file_name = getFile(file_prefix);
            Log.d(LOGTAG, "file name is "+ file_name);

            File f = new File(Environment.getExternalStorageDirectory(),BASE+file_name);

            if(!f.exists()){
                Log.d(LOGTAG, f+" does not exist");
            }else {
                InputStream stream = new FileInputStream(f.getAbsolutePath());

                parser.setInput(stream, null);

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        // handle start tag
                        processStartTag(parser.getName());
                    } else if (eventType == XmlPullParser.END_TAG){
                        currentTag = null;
                    } else if (eventType == XmlPullParser.TEXT){
                        //handle text
                        processText(parser.getText());
                    }
                    eventType = parser.next();
                }
            }





            // check for the file prefix

        }catch (Resources.NotFoundException e){
            Log.d(LOGTAG,e.getMessage());
        }catch (XmlPullParserException e){
            Log.d(LOGTAG, e.getMessage());
        }catch (IOException e){
            Log.d(LOGTAG, e.getMessage());
        }




        return screens;
    }

    private void processText(String text) {
        String xmlText = text;

        if(currentScreen != null && currentTag != null){
            if(currentTag.equals(SCREEN_ID)){
                currentScreen.setScreenID(xmlText);
            } else if(currentTag.equals(VIDEO_FRAME)){
                currentScreen.setVideoURL(xmlText);
            } else if(currentTag.equals(VIDEO_CAPTION)){
                currentScreen.setVidCaption(xmlText);
            } else if (currentTag.equals(IMAGEPATH)){
                currentScreen.setImagePath(xmlText);
            }
        }

    }

    private void processStartTag(String name) {
        if(name.equalsIgnoreCase("screen")){
            currentScreen = new Screen();
            screens.add(currentScreen);
        } else {
            currentTag = name;
        }
    }


    public String getFile(String prefix) throws IOException{
        // return the file name after checking the prefix of the file
        String path = Environment.getExternalStorageDirectory().toString()+BASE;
        Log.d(LOGTAG, "Path: " + path);

        File dir = new File(path);

        String filename = null;

        File file[] = dir.listFiles();
        Log.d("Files", "Size: " + file.length);


        if (file !=null){
            for (int i=0; i< file.length; i++){



                if(file[i].getName().startsWith(prefix)) {

                    filename = file[i].getName();
                    Log.d(LOGTAG, filename + " found!");
                    break;
                }

            }
        }
            if(filename == null)
            {
                Log.d(LOGTAG, "Lesson not found!");


            }
           // Log.d(LOGTAG, "File name returned is " + filename);
           return filename;
    }
}
