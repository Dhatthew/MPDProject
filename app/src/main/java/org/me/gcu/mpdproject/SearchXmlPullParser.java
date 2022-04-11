package org.me.gcu.mpdproject;
/* Matthew Johnston S1824385 */

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SearchXmlPullParser {

    static final String KEY_ITEM = "item";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";

    public static List<StackTraffic> getStackTrafficFromFile(Context ctx) {

        // List of StackTraffic that we will return
        List<StackTraffic> stackTraffic;
        stackTraffic = new ArrayList<StackTraffic>();

        // temp holder for current StackTraffic while parsing
        StackTraffic curStackTraffic = new StackTraffic();
        // temp holder for current text value while parsing
        String curText = "";

        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput("StackIncident.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            // point the parser to our file.
            xpp.setInput(reader);

            // get initial eventType
            int eventType = xpp.getEventType();

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            // If we are starting a new <item> block we need
                            //a new StackTraffic object to represent it
                            curStackTraffic = new StackTraffic();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            // if <item> then we are done with current item
                            // add it to the list.
                            stackTraffic.add(curStackTraffic);
                        } else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
                                // if </name> use setName() on curTraffic
                                curStackTraffic.setTitle(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION)) {
                            // if </name> use setName() on curTraffic
                            String[] arr = curText.split("<br />");
                            StringBuilder stitch = new StringBuilder();
                            for (String s: arr) {
                                stitch.append(s).append(" ");
                            }
                            curStackTraffic.setDescription(stitch.toString());
                            curStackTraffic.setDates((curText));
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput("StackTraffic.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            // point the parser to our file.
            xpp.setInput(reader);

            // get initial eventType
            int eventType = xpp.getEventType();

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            // If we are starting a new <item> block we need
                            //a new StackTraffic object to represent it
                            curStackTraffic = new StackTraffic();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            // if <item> then we are done with current item
                            // add it to the list.
                            stackTraffic.add(curStackTraffic);
                        } else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
                            // if </name> use setName() on curTraffic
                            curStackTraffic.setTitle(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION)) {
                            // if </name> use setName() on curTraffic
                            String[] arr = curText.split("<br />");
                            StringBuilder stitch = new StringBuilder();
                            for (String s: arr) {
                                stitch.append(s).append(" ");
                            }
                            curStackTraffic.setDescription(stitch.toString());
                            curStackTraffic.setDates((curText));
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput("StackWorks.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            // point the parser to our file.
            xpp.setInput(reader);

            // get initial eventType
            int eventType = xpp.getEventType();

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            // If we are starting a new <item> block we need
                            //a new StackTraffic object to represent it
                            curStackTraffic = new StackTraffic();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            // if <item> then we are done with current item
                            // add it to the list.
                            stackTraffic.add(curStackTraffic);
                        } else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
                            // if </name> use setName() on curTraffic
                            curStackTraffic.setTitle(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION)) {
                            // if </name> use setName() on curTraffic
                            String[] arr = curText.split("<br />");
                            StringBuilder stitch = new StringBuilder();
                            for (String s: arr) {
                                stitch.append(s).append(" ");
                            }
                            curStackTraffic.setDescription(stitch.toString());
                            curStackTraffic.setDates((curText));
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // return the populated list.
        return stackTraffic;
    }
}