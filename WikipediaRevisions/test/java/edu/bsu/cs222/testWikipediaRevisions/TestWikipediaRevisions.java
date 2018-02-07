package edu.bsu.cs222.testWikipediaRevisions;

import edu.bsu.cs222.wikipediaRevisionsUI.WikiPageJsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TestWikipediaRevisions {

    @Test
    public void testGetFirstEditorUsername() {

        WikiPageJsonParser testParser = new WikiPageJsonParser();
        Assert.assertEquals("Samf4u", testParser.getFirstRevisionEditorUsername());


    }


    @Test
    public void testGetLocalTimestampOfLastRevision() throws ParseException {
        WikiPageJsonParser testParser = new WikiPageJsonParser();
        Assert.assertEquals("2018-01-30 17:14:55.0", testParser.getTimestampInLocalTime().toString());
    }

    @Test
    public void testGetLastRevisionObject(){
        WikiPageJsonParser testParser = new WikiPageJsonParser();
        WikiPageRevision lastRevision = new WikiPageRevision();
        lastRevision = testParser.getLastRevision();
        Assert.assertEquals("Samf4u", lastRevision.user);

    }
}