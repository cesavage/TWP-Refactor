package edu.bsu.cs222.wikipediaRevisionsUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jdk.internal.util.xml.impl.Input;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {
    private com.google.gson.JsonParser gsonJsonParser = new com.google.gson.JsonParser();
    private InputStreamReader jsonStreamReader;

    public RevisionParser(InputStreamReader jsonStreamReader){
        this.jsonStreamReader = jsonStreamReader;
    }

    private JsonObject getPagesObjectFromJsonString(){
        JsonObject jsonRootObject = gsonJsonParser.parse(this.jsonStreamReader).getAsJsonObject();
        JsonObject jsonQueryObject = jsonRootObject.getAsJsonObject("query");
        JsonObject jsonPagesObject = jsonQueryObject.getAsJsonObject("pages");

        return jsonPagesObject;
    }



    public List<Revision> parse(){
        JsonObject jsonPagesObject = this.getPagesObjectFromJsonString();
        JsonArray jsonRevisionsArray = new JsonArray();

        for (Map.Entry<String, JsonElement> entry : jsonPagesObject.entrySet()){
            JsonObject jsonRevisionObject = entry.getValue().getAsJsonObject();
            jsonRevisionsArray = jsonRevisionObject.getAsJsonArray("revisions");
        }

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

}
