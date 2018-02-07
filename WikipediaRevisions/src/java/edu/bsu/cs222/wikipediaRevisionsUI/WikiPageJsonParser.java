package edu.bsu.cs222.wikipediaRevisionsUI;


import com.google.gson.*;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class WikiPageJsonParser {

    private com.google.gson.JsonParser gsonJsonParser = new com.google.gson.JsonParser();

    private JsonObject getJsonRoot(){
        InputStream jsonInputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader jsonStreamReader = new InputStreamReader(jsonInputStream);
        JsonElement jsonRootElement = gsonJsonParser.parse(jsonStreamReader);

        return jsonRootElement.getAsJsonObject();
    }

    private JsonObject getPage(){
        JsonObject rootObject = this.getJsonRoot();

        return rootObject.getAsJsonObject("query").getAsJsonObject("pages");
    }

    private JsonArray getRevisions(){
        JsonObject page = this.getPage();

        JsonArray revisions = null;
        for (Map.Entry<String, JsonElement> entry : page.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisions = entryObject.getAsJsonArray("revisions");
        }

        return revisions;
    }

    public String getFirstEditor() {
        JsonArray revisions = this.getRevisions();
        int firstRevisionIndex = revisions.size()-1;
        JsonObject firstRevision = revisions.get(firstRevisionIndex).getAsJsonObject();

        return firstRevision.getAsJsonPrimitive("user").toString();
    }


}


