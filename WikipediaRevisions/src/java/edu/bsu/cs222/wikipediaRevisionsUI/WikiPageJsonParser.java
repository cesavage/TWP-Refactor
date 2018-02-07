package edu.bsu.cs222.wikipediaRevisionsUI;


import com.google.gson.*;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

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

    public String getFirstRevisionEditorUsername() {
        JsonArray revisions = this.getRevisions();
        int firstRevisionIndex = revisions.size()-1;
        JsonObject firstRevision = revisions.get(firstRevisionIndex).getAsJsonObject();

        String username = firstRevision.getAsJsonPrimitive("user").toString();

        //MediaWiki API returns username in quotations.
        //Remove quotations by stripping first and last characters from the string.
        username = username.substring(1, username.length()-1);

        return username;

    }

    public String getFirstRevisionTimestamp(){
        JsonArray revisions = this.getRevisions();
        int firstRevisionIndex = revisions.size()-1;
        JsonObject firstRevision = revisions.get(firstRevisionIndex).getAsJsonObject();

        String timestamp = firstRevision.getAsJsonPrimitive("timestamp").toString();

        //MediaWiki API returns username in quotations.
        //Remove quotations by stripping first and last characters from the string.
        timestamp = timestamp.substring(1, timestamp.length()-1);

        return timestamp;
    }

    public Timestamp getTimestampInLocalTime() throws ParseException {
        SimpleDateFormat wikiTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        wikiTimeFormat.setTimeZone(TimeZone.getTimeZone("Z"));
        java.util.Date parsedDate = wikiTimeFormat.parse(this.getFirstRevisionTimestamp());
        Timestamp localTimestamp = new Timestamp(parsedDate.getTime());

        return localTimestamp;
    }

}


