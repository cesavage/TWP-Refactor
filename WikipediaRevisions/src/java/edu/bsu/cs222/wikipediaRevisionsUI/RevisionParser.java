package edu.bsu.cs222.wikipediaRevisionsUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RevisionParser {
    private com.google.gson.JsonParser gsonJsonParser = new com.google.gson.JsonParser();
    private InputStreamReader jsonStreamReader;

    public RevisionParser(InputStreamReader jsonStreamReader){
        this.jsonStreamReader = jsonStreamReader;
    }



    public List<Revision> createRevisionsListFromJson(){
        JsonObject jsonPagesObject = this.getPagesObjectFromJsonString();
        JsonArray jsonRevisionsArray = this.getRevisionsArrayFromPagesObject(jsonPagesObject);

        List<Revision> revisionList = new ArrayList<>();

        for (JsonElement jsonSingleRevision : jsonRevisionsArray){
            JsonObject jsonCurrentRevision = jsonSingleRevision.getAsJsonObject();
            String user = jsonCurrentRevision.get("user").getAsString();
            String timestamp = jsonCurrentRevision.get("timestamp").getAsString();

            Revision currentRevision = new Revision(user, timestamp);
            revisionList.add(currentRevision);
        }
        return revisionList;
    }



    private JsonObject getPagesObjectFromJsonString(){
        JsonObject jsonRootObject = gsonJsonParser.parse(this.jsonStreamReader).getAsJsonObject();
        JsonObject jsonQueryObject = jsonRootObject.getAsJsonObject("query");
        return jsonQueryObject.getAsJsonObject("pages");
    }




    private JsonArray getRevisionsArrayFromPagesObject(JsonObject jsonPagesObject){
        JsonArray jsonRevisionsArray = new JsonArray();

        for (Map.Entry<String, JsonElement> entry : jsonPagesObject.entrySet()){
            JsonObject jsonRevisionObject = entry.getValue().getAsJsonObject();
            jsonRevisionsArray = jsonRevisionObject.getAsJsonArray("revisions");
        }

        return jsonRevisionsArray;
    }



    private Timestamp convertFromStringToLocalTimestamp(String timestamp) throws ParseException {
        SimpleDateFormat wikiDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        wikiDateTimeFormat.setTimeZone(TimeZone.getTimeZone("Z"));
        java.util.Date parsedTimeStamp = wikiDateTimeFormat.parse(timestamp);
        return new Timestamp(parsedTimeStamp.getTime());
    }
}
