package edu.bsu.cs222.wikipediaRevisionsUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class RevisionCollection {

    public List<Revision> revisionsList = new ArrayList<Revision>();

    public RevisionCollection(InputStreamReader inputStream) throws IOException, ParseException {
        JsonObject wikiPagesObject = new MediaWikiJsonParser(inputStream).wikiPagesObject;
        JsonArray jrevisions = null;

        for (Map.Entry<String, JsonElement> entry : wikiPagesObject.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            jrevisions = entryObject.getAsJsonArray("revisions");
        }


        for(JsonElement crevision : jrevisions){
            JsonObject revisionObject = crevision.getAsJsonObject();
            String user = revisionObject.get("user").getAsString();
            String date = revisionObject.get("timestamp").getAsString();
            Timestamp timestamp = getTimestampInLocalTime(date);

            Revision wikiPageRevisionObject = new Revision(user, timestamp);
            revisionsList.add(wikiPageRevisionObject);
        }

    }

    public Timestamp getTimestampInLocalTime(String timestamp) throws ParseException {
        SimpleDateFormat wikiTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        wikiTimeFormat.setTimeZone(TimeZone.getTimeZone("Z"));
        java.util.Date parsedDate = wikiTimeFormat.parse(timestamp);
        Timestamp localTimestamp = new Timestamp(parsedDate.getTime());

        return localTimestamp;
    }

    public List<Revision> getRevisionsByNewestFirst() {


        return null;
    }
}

