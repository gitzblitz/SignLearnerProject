package com.gitzblitz.signlearner;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

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
 * Created by User on 2014/11/18.
 */
public class CoursePullParser {

    private static final String KEY_COURSE = "course";
    private static final String UNIT_TITLE = "unitTitle";
    private static final String UNIT_ID = "unitID";
    private static final String KEY_UNIT = "unit";
    private static final String KEY_LESSON ="lesson";
    private static final String KEY_SCREEN = "screen";
    private static final String KEY_SCREENID = "screenID";
    private static final String KEY_IMAGE ="image";
    private static final String KEY_VIDEO = "video";
    private static final String KEY_VIDEO_CAPTION = "vid_caption";

    private static final String LOGTAG = "COURSE_PARSER";
    private static final String ns = null;
    private String currTag = null;
    private Lesson currLesson = null;
    private Screen currScreen = null;
    private String tagText = null;

    public ArrayList<Units> units = new ArrayList<Units>();
    public ArrayList<String> lessonIdList = null;
    public ArrayList<Lesson> lessons = null;
    public ArrayList<Screen> screenss = new ArrayList<Screen>();

    public ArrayList<Lesson> parse(Context context)  {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            File file = new File(Environment.getExternalStorageDirectory(), "SignSupport/icdl/xml/e learner self study.xml");

            if (!file.exists()) {
                Log.d(LOGTAG, file + " does not exist");
            } else {
                InputStream stream = new FileInputStream(file.getAbsolutePath());
                parser.setInput(stream, null);

                int eventType = parser.getEventType();

//                while (eventType != XmlPullParser.END_DOCUMENT) {
//                    if (eventType == XmlPullParser.START_TAG) {
//                        processStartTag(parser.getName());
//                    } else if (eventType == XmlPullParser.END_TAG) {
//                        currTag = null;
//
//                    } else if (eventType == XmlPullParser.TEXT) {
//                        processText(parser.getText());
//                    }else {
////                        skip (parser);
//                    }
//                    eventType = parser.next();
//                }

                while(eventType != XmlPullParser.END_DOCUMENT){

                    String tagname = parser.getName();


                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            if(tagname.equalsIgnoreCase(KEY_COURSE)){
                                Log.d(LOGTAG, "new course");
                            }else if (tagname.equalsIgnoreCase(KEY_UNIT)){

                                String unit_id = parser.getAttributeValue(null,"unit_id");
                                String unit_title = parser.getAttributeValue(null, "unit_title");
                                Log.d(LOGTAG, "new unit title= " + unit_title+ " with id= "+unit_id);

                            }else if (tagname.equalsIgnoreCase(KEY_LESSON)){

                                String title = parser.getAttributeValue(null, "lesson_title");
                                String id = parser.getAttributeValue(null, "lesson_id");
                                String lesson_type = parser.getAttributeValue(null, "lesson_type");
                                Log.d(LOGTAG, "lesson title="+title+" lesson id="+id+" lesson type="+lesson_type);
                                currLesson = new Lesson();
                                currLesson.setCategory(lesson_type);
                                currLesson.setId(id);
                                currLesson.setTitle(title);
                                lessons = new ArrayList<Lesson>();

                            }else if (tagname.equalsIgnoreCase(KEY_SCREEN)){

                                Log.d(LOGTAG, "New Screen object");
                                currScreen = new Screen();
                                screenss.add(currScreen);
                            }
                            break;
                        case XmlPullParser.TEXT:
                            currTag = parser.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            if(tagname.equalsIgnoreCase(KEY_COURSE)){
                                Log.d(LOGTAG, "End of course reached");
                            }else if(tagname.equalsIgnoreCase(KEY_UNIT)){
                                Log.d(LOGTAG, "end of unit");
                            }else if(tagname.equalsIgnoreCase(KEY_LESSON)){

                                currLesson.setScreens(screenss);
                                lessons.add(currLesson);
                            }else if (tagname.equalsIgnoreCase(KEY_SCREEN)){
//                                screenss.add(currScreen);
//                                currLesson.setScreens(screenss);
                                Log.d(LOGTAG, "end of screen");
                            } else if(tagname.equalsIgnoreCase(KEY_IMAGE)){
                                Log.d(LOGTAG, "image=" + currTag);
                                currScreen.setImagePath(currTag);
                            } else if (tagname.equalsIgnoreCase(KEY_SCREENID)){
                                currScreen.setScreenID(currTag);
                                Log.d(LOGTAG, "ScreenID=" + currTag);
                            } else if(tagname.equalsIgnoreCase(KEY_VIDEO)){
                                Log.d(LOGTAG, "video="+ currTag);
                                currScreen.setVideoURL(currTag);
                            }else if (tagname.equalsIgnoreCase(KEY_VIDEO_CAPTION)){
                                Log.d(LOGTAG, "caption="+ currTag);
                                currScreen.setVidCaption(currTag);
                            }
                            break;
                        default:
                            break;

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

//    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException{
//        if(parser.getEventType() != XmlPullParser.START_TAG){
//            throw new IllegalStateException();
//
//            int depth = 1;
//            while (depth !=0){
//                switch (parser.next()){
//                    case XmlPullParser.END_TAG:
//                        depth--;
//                        break;
//                    case XmlPullParser.START_TAG:
//                        depth++;
//                        break;
//                }
//            }
//        }
//    }

    private void processStartTag(String name) {
        if(name.equalsIgnoreCase(KEY_LESSON)){
            currLesson = new Lesson();
            lessons.add(currLesson);
        }else if (name.equalsIgnoreCase(KEY_SCREEN)){
            currScreen = new Screen();
            screenss.add(currScreen);
            currLesson.setScreens(screenss);
        }else if(name.equalsIgnoreCase("unit")){
            Log.d(LOGTAG, "New unit detected");
        }
        else if(name.equalsIgnoreCase("course")) {
            Log.d(LOGTAG, "New course detected");
        }else
        {
            currTag = name;
        }

    }
    private void processText(String text){
        String xmlText = text;
        if(currLesson != null && currTag != null && currScreen != null){
            if(currTag.equals(KEY_IMAGE)){
                currScreen.setImagePath(xmlText);
            } else if (currTag.equals(KEY_VIDEO)){
                currScreen.setVideoURL(xmlText);
            } else if (currTag.equals(KEY_SCREENID)){
                currScreen.setScreenID(xmlText);
            }else if (currTag.equals(KEY_VIDEO_CAPTION)){
                currScreen.setVidCaption(xmlText);
            }
        }

    }


}
