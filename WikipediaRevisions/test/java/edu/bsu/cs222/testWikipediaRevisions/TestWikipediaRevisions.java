package edu.bsu.cs222.testWikipediaRevisions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.bsu.cs222.wikipediaRevisionsUI.MediaWikiAPIConnection;
import edu.bsu.cs222.wikipediaRevisionsUI.Revision;
import edu.bsu.cs222.wikipediaRevisionsUI.RevisionParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class TestWikipediaRevisions {

    @Test
    public void learningTestReadFirstRevisionAuthorFromSampleData(){
        com.google.gson.JsonParser gsonJsonParser = new com.google.gson.JsonParser();

        InputStream sampleJsonStream = this.getClass().getClassLoader().getResourceAsStream("sample.json");
        InputStreamReader sampleJsonStreamReader = new InputStreamReader(sampleJsonStream);

        JsonArray jsonRevisionsArray = new JsonArray();

        JsonObject jsonRootObject = gsonJsonParser.parse(sampleJsonStreamReader).getAsJsonObject();
        JsonObject jsonQueryObject = jsonRootObject.getAsJsonObject("query");
        JsonObject jsonPagesObject = jsonQueryObject.getAsJsonObject("pages");

        for (Map.Entry<String, JsonElement> entry : jsonPagesObject.entrySet()){
            JsonObject jsonRevisionObject = entry.getValue().getAsJsonObject();
            jsonRevisionsArray = jsonRevisionObject.getAsJsonArray("revisions");
        }

        JsonObject firstRevision = jsonRevisionsArray.get(0).getAsJsonObject();
        String firstAuthor = firstRevision.get("user").getAsString();

        Assert.assertEquals("ClueBot NG", firstAuthor);
    }

    @Test
    public void testSizeOfRevisionListGeneratedByRevisionParser(){
        InputStream sampleJsonStream = this.getClass().getClassLoader().getResourceAsStream("sample.json");
        InputStreamReader sampleJsonStreamReader = new InputStreamReader(sampleJsonStream);
        RevisionParser revisionParser = new RevisionParser(sampleJsonStreamReader);

        List<Revision> revisions = revisionParser.createRevisionsListFromJson();

        Assert.assertEquals(4, revisions.size());
    }

    @Test
    public void learningTestGenerateRevisionListFromURL() throws IOException {
        MediaWikiAPIConnection testConnection = new MediaWikiAPIConnection("soup");
        InputStreamReader testReader = testConnection.connect();
        RevisionParser revisionParser = new RevisionParser(testReader);

        List<Revision> revisions = revisionParser.createRevisionsListFromJson();

        Assert.assertEquals(30, revisions.size());
    }

    @Test
    public void testRevisionParserUtilityMethodGetPagesObjectFromJsonString() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MediaWikiAPIConnection testConnection = new MediaWikiAPIConnection("soup");
        InputStreamReader testReader = testConnection.connect();
        RevisionParser revisionParser = new RevisionParser(testReader);

        //Set utility method as accessible and invoke by reflection.
        Method getPagesMethod = RevisionParser.class.getDeclaredMethod("getPagesObjectFromJsonString");
        getPagesMethod.setAccessible(true);
        JsonObject jsonPagesObject = (JsonObject) getPagesMethod.invoke(revisionParser);

        JsonObject jsonRevisionObject = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonPagesObject.entrySet()){
            jsonRevisionObject = entry.getValue().getAsJsonObject();
        }

        Assert.assertNotEquals(null, jsonRevisionObject.getAsJsonArray("revisions"));
    }

    @Test
    public void testRevisionParserUtilityMethodGetJsonArrayFromPagesObject() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        InputStream sampleJsonStream = this.getClass().getClassLoader().getResourceAsStream("sample.json");
        InputStreamReader sampleJsonStreamReader = new InputStreamReader(sampleJsonStream);
        RevisionParser revisionParser = new RevisionParser(sampleJsonStreamReader);

        //Set utility methods as accessible.
        Method getPagesMethod = RevisionParser.class.getDeclaredMethod("getPagesObjectFromJsonString");
        getPagesMethod.setAccessible(true);
        Method getArrayMethod = RevisionParser.class.getDeclaredMethod("getRevisionsArrayFromPagesObject", JsonObject.class);
        getArrayMethod.setAccessible(true);

        //Invoke utility methods by reflection.
        JsonObject jsonPagesObject = (JsonObject) getPagesMethod.invoke(revisionParser);
        JsonArray jsonRevisionsArray = (JsonArray) getArrayMethod.invoke(revisionParser, jsonPagesObject);

        Assert.assertEquals(4, jsonRevisionsArray.size());
    }

    @Test
    public void testConvertTimestampFromStringToLocalDateTime() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        //Set utility method as accessible.
        InputStream sampleJsonStream = this.getClass().getClassLoader().getResourceAsStream("sample.json");
        InputStreamReader sampleJsonStreamReader = new InputStreamReader(sampleJsonStream);
        RevisionParser revisionParser = new RevisionParser(sampleJsonStreamReader);
        Method convertRevisionTimeFromStringToTimeStamp = RevisionParser.class.getDeclaredMethod("convertFromStringToLocalTimestamp", String.class);
        convertRevisionTimeFromStringToTimeStamp.setAccessible(true);

        //Invoke utility method by reflection.
        Timestamp timestamp = (Timestamp) convertRevisionTimeFromStringToTimeStamp.invoke(revisionParser, "2018-02-02T11:08:37Z");

        Assert.assertEquals("2018-02-02 06:08:37.0", timestamp.toString());
    }

}