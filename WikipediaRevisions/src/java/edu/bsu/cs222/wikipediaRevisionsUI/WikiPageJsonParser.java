package edu.bsu.cs222.wikipediaRevisionsUI;


import com.google.gson.*;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WikiPageJsonParser {

    private InputStream getJsonInputStream(){
        return getClass().getClassLoader().getResourceAsStream("sample.json");
    }

    private Reader getJsonStreamReader(){
        InputStream jsonInputStream = this.getJsonInputStream();
        return new InputStreamReader(jsonInputStream);

    }

    private JsonObject getJsonRoot(){
        Reader jsonStreamReader = this.getJsonStreamReader();
        com.google.gson.JsonParser gsonJsonParser = new com.google.gson.JsonParser();
        JsonElement jsonRootElement = gsonJsonParser.parse(jsonStreamReader);
        System.out.println(jsonRootElement.getAsJsonObject());
        return jsonRootElement.getAsJsonObject();
    }

    private JsonObject getPage(){
        JsonObject rootObject = this.getJsonRoot();

        return rootObject.getAsJsonObject("query").getAsJsonObject("pages");
    }

    public JsonArray getRevisionsJsonArray(){
        JsonObject page = this.getPage();

        JsonArray revisions = null;
        for (Map.Entry<String, JsonElement> entry : page.entrySet()){
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
            //TODO add anonymous option
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


