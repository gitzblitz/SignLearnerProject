package com.gitzblitz.signlearner;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2014/05/13.
 */
public class LessonPullParser {

    private static final String LOGTAG = "LESSON_PARSER";
    private static final String LESSON_TITLE = "title";
    private static final String LESSON_ID = "id";
    private static final String KEY_CATEGORY = "unit";

    private Lesson currLesson = null;
    private String currTag = null;

    public ArrayList<Lesson> lessons = new ArrayList<Lesson>();

    public ArrayList<Lesson> parseXML(Context context){


        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            File file = new File(Environment.getExternalStorageDirectory(),"/SignSupport/icdl/xml/signsupportv4.xml");

            if(!file.exists()){
                Log.d(LOGTAG, file + " does not exist");
            }else {
                InputStream stream = new FileInputStream(file.getAbsolutePath());

                parser.setInput(stream, null);

                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        // handle start tag
                        processStartTag(parser.getName());
                    } else if (eventType == XmlPullParser.END_TAG){
                        currTag = null;
                    } else if (eventType == XmlPullParser.TEXT){
                        //handle text
                        processText(parser.getText());
                    }
                    eventType = parser.next();
                }
            }

        }catch (Resources.NotFoundException e){
            e.getMessage();
        }catch (XmlPullParserException e){
            e.getMessage();
        }catch (IOException e){
            e.getMessage();
        }

        return lessons;
    }

    private void processStartTag(String name) {
        if(name.equalsIgnoreCase("lesson")){
            currLesson = new Lesson();
            lessons.add(currLesson);
        }else {
            currTag = name;
        }
    }

    private void processText(String text) {
        String xmlText = text;

        if(currLesson != null && currTag !=null){
            if(currTag.equals(LESSON_TITLE)){
                currLesson.setTitle(xmlText);
            } else if (currTag.equals(LESSON_ID)){
                currLesson.setId(xmlText);
            } else if ((currTag.equals(KEY_CATEGORY))){
                currLesson.setCategory(xmlText);
            }
        }

    }
}
