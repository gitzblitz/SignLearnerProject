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
 * Created by George Ng'ethe on 2014/11/18.
 * This class loads an XML from a file, parses it and stores it in the defined
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

    private String currTag = null;
    private Lesson currLesson = null;
    private Screen currScreen = null;

    public ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    public ArrayList<Screen> screenss = new ArrayList<Screen>();

    public ArrayList<Lesson> parse(Context context)  {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            File file = new File(Environment.getExternalStorageDirectory(), "SignSupport/icdl/xml/e learner self study.xml");
/*Check if the file exists*/
            if (!file.exists()) {
                Log.d(LOGTAG, file + " does not exist");
            } else {
                /*Open an inputstream to read from the file*/
                InputStream stream = new FileInputStream(file.getAbsolutePath());
                parser.setInput(stream, null);

                int eventType = parser.getEventType();


                while(eventType != XmlPullParser.END_DOCUMENT){
                    /*Get the XML tag name and switch between the tags parsing the XML*/
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
//                                currLesson.setScreens(screenss);

                                lessons.add(currLesson);
                                if(screenss.isEmpty()){
                                    Log.d(LOGTAG, "screens list is empty");
                                }else {

                                    Log.d(LOGTAG, "Screens list has "+ Integer.toString(screenss.size()) );
//                                    screenss.clear();
                                }

                            }else if (tagname.equalsIgnoreCase(KEY_SCREEN)){

                                Log.d(LOGTAG, "New Screen object");
                                currScreen = new Screen(); // create new screen object once you get a screen tag
//
                                screenss.add(currScreen); // add current screen to the list of screens
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

                                    if(screenss.isEmpty())
                                    {
                                        Log.d(LOGTAG, "No screens for this lesson ");
                                    }else{
                                        currLesson.setScreens(screenss); // Set the current lesson to the list of screens
                                        Log.d(LOGTAG, "list of screens added for Lesson: " + currLesson.getTitle()+ " with size "+ Integer.toString(screenss.size()));
                                        Log.d(LOGTAG, "End of lesson");
//
                                    }
//                                screenss.clear();
//                                lessons.add(currLesson);
                            }else if (tagname.equalsIgnoreCase(KEY_SCREEN)){
//                                screenss.add(currScreen);


                            } else if(tagname.equalsIgnoreCase(KEY_IMAGE)){
                                /*Store the image path*/
                                currScreen.setImagePath(currTag);
                            } else if (tagname.equalsIgnoreCase(KEY_SCREENID)){
                                /*Store the Screen ID*/
                                currScreen.setScreenID(currTag);
                            } else if(tagname.equalsIgnoreCase(KEY_VIDEO)){
                                /*Store the videoURL*/
                                currScreen.setVideoURL(currTag);
                            }else if (tagname.equalsIgnoreCase(KEY_VIDEO_CAPTION)){
                                /*Store the video caption*/
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
}
