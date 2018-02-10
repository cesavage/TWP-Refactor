package edu.bsu.cs222.testWikipediaRevisions;

import edu.bsu.cs222.wikipediaRevisionsUI.MediaWikiConnection;
import edu.bsu.cs222.wikipediaRevisionsUI.Revision;
import edu.bsu.cs222.wikipediaRevisionsUI.RevisionCollection;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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
        Revision firstRevision = testParser.getFirstRevision();

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
    public void testCreateRevisionsCollection() throws IOException, ParseException {
        InputStreamReader wikiData = new MediaWikiConnection("sample.json").createInputStreamReader();
        RevisionCollection revisionCollection = new RevisionCollection(wikiData);

        List<Revision> revisionList = revisionCollection.getRevisionsByNewestFirst();

    }

    @Test
    public void testCreateJsonInputStreamReader() throws IOException {
        InputStreamReader wikiData = new MediaWikiConnection("sample.json").createInputStreamReader();

        Assert.assertFalse(wikiData.read() == -1); //inputStream.read() == -1 means end of file / empty file.
        }


    @Test
    public void testCreateRevisionObjectList() throws ParseException, IOException {
        InputStreamReader inputStreamReader = new MediaWikiConnection("sample.json").createInputStreamReader();
        RevisionCollection revisionCollection = new RevisionCollection(inputStreamReader);

        Assert.assertEquals(4, revisionCollection.revisionsList.size());
    }

    @Test
    public void testLastRevisionObjectAttributes() throws ParseException, IOException {
        InputStreamReader inputStreamReader = new MediaWikiConnection("sample.json").createInputStreamReader();
        RevisionCollection revisionCollection = new RevisionCollection(inputStreamReader);

        Revision firstRevision = revisionCollection.revisionsList.get(revisionCollection.revisionsList.size()-1);

        //TODO One assert per test.
        Assert.assertEquals("Samf4u", firstRevision.username);
        Assert.assertEquals("2018-01-30 17:14:55.0", firstRevision.localTimeStamp.toString());
    }

    @Test
    public void testFirstRevisionObjectAttributes() throws ParseException, IOException {
        InputStreamReader inputStreamReader = new MediaWikiConnection("sample.json").createInputStreamReader();
        RevisionCollection revisionCollection = new RevisionCollection(inputStreamReader);

        Revision lastRevision = revisionCollection.revisionsList.get(0);

        //TODO One assert per test.
        Assert.assertEquals("ClueBot NG", lastRevision.username);
        Assert.assertEquals("2018-02-02 06:08:40.0", lastRevision.localTimeStamp.toString());
    }



}