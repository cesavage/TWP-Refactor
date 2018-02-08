package edu.bsu.cs222.testWikipediaRevisions;

import edu.bsu.cs222.wikipediaRevisionsUI.WikiPageJsonParser;
import edu.bsu.cs222.wikipediaRevisionsUI.WikiPageRevision;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

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
        Assert.assertEquals(4, parser.generateRevisionListFromArray().size());
    }

    @Test
    public void testLastRevisionObjectAttributes() throws ParseException {
        WikiPageJsonParser parser = new WikiPageJsonParser();
        List revisionList = parser.generateRevisionListFromArray();
        WikiPageRevision firstRevision = (WikiPageRevision) revisionList.get(revisionList.size()-1);
        Assert.assertEquals("Samf4u", firstRevision.username);
        Assert.assertEquals("2018-01-30 17:14:55.0", firstRevision.localTimeStamp.toString());
    }

    @Test
    public void testFirstRevisionObjectAttributes() throws ParseException {
        WikiPageJsonParser parser = new WikiPageJsonParser();
        List revisionList = parser.generateRevisionListFromArray();
        WikiPageRevision firstRevision = (WikiPageRevision) revisionList.get(0);
        Assert.assertEquals("ClueBot NG", firstRevision.username);
        Assert.assertEquals("2018-02-02 06:08:40.0", firstRevision.localTimeStamp.toString());
    }

}