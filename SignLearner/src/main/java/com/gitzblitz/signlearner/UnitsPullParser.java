package com.gitzblitz.signlearner;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2013/12/02.
 */
public class UnitsPullParser {


    private static final String KEY_COURSE = "course";
    private static final String UNIT_TITLE = "unitTitle";
    private static final String UNIT_ID = "unitId";
    private static final String KEY_UNIT = "unit";
//    private static final String KEY_LESSON_ID ="lesson_id";

    private Units currUnit = null;
    private String currTag = null;

    public List<Units> units = new ArrayList<Units>();


    public List<Units> getUnitsFromFile(Context context) {


        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            // Open inputStream to read file
            File file = new File(Environment.getExternalStorageDirectory(),"/SignLearner/Xml/signsupport.xml");

//            InputStream stream = context.getResources().openRawResource(R.raw.signsupport);
            InputStream stream = new FileInputStream(file.getAbsolutePath());
            parser.setInput(stream, null);


            //get initial eventType
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    handleStartTag(parser.getName());
//                    String attr = parser.getAttributeValue(null,"id");
                } else if (eventType == XmlPullParser.END_TAG) {
                    currTag = null;
                } else if (eventType == XmlPullParser.TEXT) {
                    handleText(parser.getText());
                }
                eventType = parser.next();
            }

        } catch (Resources.NotFoundException e) {
            e.getMessage();
        } catch (XmlPullParserException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }

        return units;
    }

    private void handleText(String text) {
        String xmlText = text;

        if (currUnit != null && currTag != null) {
            if (currTag.equals(UNIT_ID)) {
                Integer id = Integer.parseInt(xmlText);
                currUnit.setId(id);
            } else if (currTag.equals(UNIT_TITLE)) {
                currUnit.setTitle(xmlText);
            }
        }
    }


    private void handleStartTag(String name) {

        if (name.equals("unit")) {

            currUnit = new Units();
            units.add(currUnit);
        } else {
            currTag = name;
        }
    }


}
