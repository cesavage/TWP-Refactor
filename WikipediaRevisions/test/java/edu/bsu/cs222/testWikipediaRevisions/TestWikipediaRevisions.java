package edu.bsu.cs222.testWikipediaRevisions;

import edu.bsu.cs222.wikipediaRevisionsUI.MediaWikiConnection;
import edu.bsu.cs222.wikipediaRevisionsUI.MediaWikiJsonParser;
import edu.bsu.cs222.wikipediaRevisionsUI.RevisionCollection;
import edu.bsu.cs222.wikipediaRevisionsUI.WikiPageRevision;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

public class TestWikipediaRevisions {
/*
    @Test
    public void testGetFirstEditorUsername() {

        MediaWikiJsonParser testParser = new MediaWikiJsonParser();
        Assert.assertEquals("Samf4u", testParser.getFirstRevisionEditorUsername());


    }
*/
/*
    @Test
    public void testGetLocalTimestampOfLastRevision() throws ParseException {
        MediaWikiJsonParser testParser = new MediaWikiJsonParser();
        Assert.assertEquals("2018-01-30 17:14:55.0", testParser.getTimestampInLocalTime().toString());
    }
 */
/*
    @Test
    public void testGetFirstRevisionObject() throws ParseException {
        MediaWikiJsonParser testParser = new MediaWikiJsonParser();
        WikiPageRevision firstRevision = testParser.getFirstRevision();

        Assert.assertEquals("Samf4u", firstRevision.username);
        Assert.assertEquals("2018-01-30 17:14:55.0", firstRevision.localTimeStamp.toString());

    }
*/
/*
    @Test
    public void testCreateIterator() {
        MediaWikiJsonParser testParser = new MediaWikiJsonParser();

        JsonArray emptyArray = new JsonArray();
        Iterator testIterator = testParser.getRevisions().iterator();

        Assert.assertTrue(testIterator.hasNext());
    }

*/
    @Test
    public void testCreateRevisionsCollection(){
        //RevisionCollection revisionCollection = new RevisionCollection();



    }

    @Test
    public void testCreateJsonInputStreamReader() throws IOException {
        MediaWikiConnection mediaWikiConnection = new MediaWikiConnection("sample.json");
        InputStreamReader inputStreamReader = mediaWikiConnection.createInputStreamReader();

        //inputStream.read() == -1 means end of file / empty file.
        Assert.assertFalse(inputStreamReader.read() == -1);
        }


    @Test
    public void testCreateRevisionObjectList() throws ParseException, IOException {
        MediaWikiConnection mediaWikiConnection = new MediaWikiConnection("sample.json");
        InputStreamReader inputStreamReader = mediaWikiConnection.createInputStreamReader();

        MediaWikiJsonParser parser = new MediaWikiJsonParser(inputStreamReader);
        Assert.assertEquals(4, parser.generateRevisionListFromArray().size());
    }

    @Test
    public void testLastRevisionObjectAttributes() throws ParseException, IOException {
        MediaWikiConnection mediaWikiConnection = new MediaWikiConnection("sample.json");
        InputStreamReader inputStreamReader = mediaWikiConnection.createInputStreamReader();

        MediaWikiJsonParser parser = new MediaWikiJsonParser(inputStreamReader);
        List revisionList = parser.generateRevisionListFromArray();
        WikiPageRevision firstRevision = (WikiPageRevision) revisionList.get(revisionList.size()-1);
        Assert.assertEquals("Samf4u", firstRevision.username);
        Assert.assertEquals("2018-01-30 17:14:55.0", firstRevision.localTimeStamp.toString());
    }

    @Test
    public void testFirstRevisionObjectAttributes() throws ParseException, IOException {
        MediaWikiConnection mediaWikiConnection = new MediaWikiConnection("sample.json");
        InputStreamReader inputStreamReader = mediaWikiConnection.createInputStreamReader();

        MediaWikiJsonParser parser = new MediaWikiJsonParser(inputStreamReader);
        List revisionList = parser.generateRevisionListFromArray();
        WikiPageRevision firstRevision = (WikiPageRevision) revisionList.get(0);
        Assert.assertEquals("ClueBot NG", firstRevision.username);
        Assert.assertEquals("2018-02-02 06:08:40.0", firstRevision.localTimeStamp.toString());
    }



}