package edu.bsu.cs222.wikipediaRevisionsUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.util.Map;

public class RevisionParser {
    com.google.gson.JsonParser gsonJsonParser = new com.google.gson.JsonParser();

    public Revision parse(InputStreamReader sampleJsonStreamReader){

        JsonArray jsonRevisionsArray = new JsonArray();

        JsonObject jsonRootObject = gsonJsonParser.parse(sampleJsonStreamReader).getAsJsonObject();
        JsonObject jsonQueryObject = jsonRootObject.getAsJsonObject("query");
        JsonObject jsonPagesObject = jsonQueryObject.getAsJsonObject("pages");

        for (Map.Entry<String, JsonElement> entry : jsonPagesObject.entrySet()){
            JsonObject jsonRevisionObject = entry.getValue().getAsJsonObject();
            jsonRevisionsArray = jsonRevisionObject.getAsJsonArray("revisions");
        }

        JsonObject firstRevisionElement = jsonRevisionsArray.get(0).getAsJsonObject();

        String firstAuthor = new String();
        firstAuthor = firstRevisionElement.get("user").getAsString();

        String timeStamp = new String();
        timeStamp = firstRevisionElement.get("timestamp").getAsString();

        Revision firstRevision = new Revision(firstAuthor, timeStamp);

        return firstRevision;

    }

}
