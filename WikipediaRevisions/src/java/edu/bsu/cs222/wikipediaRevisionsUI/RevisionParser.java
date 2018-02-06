package edu.bsu.cs222.wikipediaRevisionsUI;


import com.google.gson.*;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class RevisionParser {

    private JsonParser parser = new JsonParser();
    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
    private Reader reader = new InputStreamReader(inputStream);
    private JsonElement rootElement = parser.parse(reader);


    public JsonArray getRevisions(){
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject page = rootObject.getAsJsonObject("query").getAsJsonObject("pages");

        JsonArray array = null;

        for (Map.Entry<String, JsonElement> entry : page.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");

        }

        return array;


    }

    public String getFirstAuthor() {


        JsonArray revision = getRevisions();
        int lastRevision = revision.size()-1;
        JsonElement firstUser = revision.get(lastRevision);
        JsonObject firstElement = firstUser.getAsJsonObject();
        String  theName = firstElement.getAsJsonPrimitive("user").toString();


        return theName;


    }


}


