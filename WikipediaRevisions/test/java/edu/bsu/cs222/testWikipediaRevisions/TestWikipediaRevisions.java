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
    public void testRevisionParser(){
        InputStream sampleJsonStream = this.getClass().getClassLoader().getResourceAsStream("sample.json");
        InputStreamReader sampleJsonStreamReader = new InputStreamReader(sampleJsonStream);

        RevisionParser revisionParser = new RevisionParser();

        List<Revision> revisions = revisionParser.parse(sampleJsonStreamReader);

        Assert.assertEquals(4, revisions.size());
    }

    @Test
    public void learningTestCreateURLConnection() throws IOException {
        MediaWikiAPIConnection testConnection = new MediaWikiAPIConnection("soup");
        InputStreamReader testReader = testConnection.connect();

        RevisionParser revisionParser = new RevisionParser();

        List<Revision> revisions = revisionParser.parse(testReader);

        Assert.assertEquals(30, revisions.size());


    }




}