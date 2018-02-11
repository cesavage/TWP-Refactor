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
    public void testCreateJsonInputStreamReader() throws IOException {
        InputStreamReader wikiData = new MediaWikiConnection("sample.json").createInputStreamReader();

        Assert.assertFalse(wikiData.read() == -1); //inputStream.read() == -1 means end of file / empty file.
        }


    @Test
    public void testCreateRevisionObjectList() throws ParseException, IOException {
        InputStreamReader inputStreamReader = new MediaWikiConnection("sample.json").createInputStreamReader();
        RevisionCollection revisionCollection = new RevisionCollection(inputStreamReader);

        Assert.assertEquals(4, revisionCollection.getRevisionsByNewestFirst().size());
    }


    @Test
    public void testNewestRevisionObjectAttributes() throws ParseException, IOException {
        InputStreamReader inputStreamReader = new MediaWikiConnection("sample.json").createInputStreamReader();
        RevisionCollection revisionCollection = new RevisionCollection(inputStreamReader);

        Revision newestRevision = revisionCollection.getRevisionsByNewestFirst().get(0);

        //TODO One assert per test.
        Assert.assertEquals("ClueBot NG", newestRevision.username);
        Assert.assertEquals("2018-02-02 06:08:40.0", newestRevision.localTimeStamp.toString());
    }

    @Test
    public void testOldestRevisionObjectAttributes() throws ParseException, IOException {
        InputStreamReader inputStreamReader = new MediaWikiConnection("sample.json").createInputStreamReader();
        RevisionCollection revisionCollection = new RevisionCollection(inputStreamReader);

        Revision oldestRevision = revisionCollection.getRevisionsByNewestFirst().get(3);

        //TODO One assert per test.
        Assert.assertEquals("Samf4u", oldestRevision.username);
        Assert.assertEquals("2018-01-30 17:14:55.0", oldestRevision.localTimeStamp.toString());
    }


    @Test
    public void testGetRevisionsByNewestFirst() throws IOException, ParseException {
        InputStreamReader wikiData = new MediaWikiConnection("sample.json").createInputStreamReader();
        RevisionCollection revisionCollection = new RevisionCollection(wikiData);

        Revision newestRevision = revisionCollection.getRevisionsByNewestFirst().get(0);

        //TODO One assert per test.
        Assert.assertEquals("ClueBot NG", newestRevision.username);
        Assert.assertEquals("2018-02-02 06:08:40.0", newestRevision.localTimeStamp.toString());

    }


//    @Test
//    public void testSortMultipleContributionAuthor() throws ParseException, IOException {
//        InputStreamReader wikiData = new MediaWikiConnection("sample.json").createInputStreamReader();
//        RevisionCollection revisionCollection = new RevisionCollection(wikiData);
//
//
//        List<Revision> sortedRevisions = revisionCollection.getSortedRevisions();
//
//        for(Revision currentRevision : sortedRevisions){
//            System.out.println(currentRevision.username);
//            System.out.println(currentRevision.localTimeStamp);
//            System.out.println("\n");
//        }






}