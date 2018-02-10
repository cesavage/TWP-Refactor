package edu.bsu.cs222.wikipediaRevisionsUI;


import com.google.gson.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MediaWikiJsonParser {



    private InputStreamReader inputStreamReader;
    private com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();

    public JsonObject wikiRootObject;
    public JsonObject wikiQueryObject;
    public JsonObject wikiPagesObject;
    public JsonObject wikiRevisionsObject;

    public MediaWikiJsonParser(InputStreamReader passedStreamReader) throws IOException {
        this.inputStreamReader = passedStreamReader;
        wikiRootObject = jsonParser.parse(inputStreamReader).getAsJsonObject();
        wikiQueryObject = wikiRootObject.getAsJsonObject("query");
        wikiPagesObject = wikiQueryObject.getAsJsonObject("pages");
        wikiRevisionsObject = wikiPagesObject.getAsJsonObject("revisions");
    }


    public JsonArray getRevisionsJsonArray(){
        JsonArray revisions = null;

        //private JsonObject wikiRevisionsObject = wikiPagesObject.getAsJsonObject("revisions");
        //System.out.println(wikiRevisionsObject.getAsJsonArray());

        for (Map.Entry<String, JsonElement> entry : wikiPagesObject.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisions = entryObject.getAsJsonArray("revisions");
        }
        return revisions;
    }




    public List generateRevisionListFromArray() throws ParseException {
        List<WikiPageRevision> newList = new ArrayList<WikiPageRevision>();
        JsonArray Revisions = this.getRevisionsJsonArray();

        for(JsonElement revision : Revisions){
            JsonObject revisionObject = revision.getAsJsonObject();
            String user = revisionObject.get("user").getAsString();
            String date = revisionObject.get("timestamp").getAsString();
            Timestamp timestamp = getTimestampInLocalTime(date);

            WikiPageRevision wikiPageRevisionObject = new WikiPageRevision(user, timestamp);
            newList.add(wikiPageRevisionObject);
        }

        return newList;
    }

    public Timestamp getTimestampInLocalTime(String timestamp) throws ParseException {
        SimpleDateFormat wikiTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        wikiTimeFormat.setTimeZone(TimeZone.getTimeZone("Z"));
        java.util.Date parsedDate = wikiTimeFormat.parse(timestamp);
        Timestamp localTimestamp = new Timestamp(parsedDate.getTime());

        return localTimestamp;
    }



}


