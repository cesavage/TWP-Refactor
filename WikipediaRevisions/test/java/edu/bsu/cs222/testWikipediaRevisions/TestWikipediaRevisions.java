package edu.bsu.cs222.testWikipediaRevisions;

import com.google.gson.JsonArray;
import edu.bsu.cs222.wikipediaRevisionsUI.WikiPageJsonParser;
import edu.bsu.cs222.wikipediaRevisionsUI.WikiPageRevision;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class TestWikipediaRevisions {
/*
    @Test
    public void testGetFirstEditorUsername() {

        WikiPageJsonParser testParser = new WikiPageJsonParser();
        Assert.assertEquals("Samf4u", testParser.getFirstRevisionEditorUsername());


    }
*/
/*
    @Test
    public void testGetLocalTimestampOfLastRevision() throws ParseException {
        WikiPageJsonParser testParser = new WikiPageJsonParser();
        Assert.assertEquals("2018-01-30 17:14:55.0", testParser.getTimestampInLocalTime().toString());
    }
 */
/*
    @Test
    public void testGetFirstRevisionObject() throws ParseException {
        WikiPageJsonParser testParser = new WikiPageJsonParser();
        WikiPageRevision firstRevision = testParser.getFirstRevision();

        Assert.assertEquals("Samf4u", firstRevision.username);
        Assert.assertEquals("2018-01-30 17:14:55.0", firstRevision.localTimeStamp.toString());

    }
*/
/*
    @Test
    public void testCreateIterator() {
        WikiPageJsonParser testParser = new WikiPageJsonParser();

        JsonArray emptyArray = new JsonArray();
        Iterator testIterator = testParser.getRevisions().iterator();

        Assert.assertTrue(testIterator.hasNext());
    }
*/
    @Test
    public void testCreateRevisionObjectList() throws ParseException {

        WikiPageJsonParser parser = new WikiPageJsonParser();

        Assert.assertEquals(4, parser.getRevisionsList().size());
    }
}