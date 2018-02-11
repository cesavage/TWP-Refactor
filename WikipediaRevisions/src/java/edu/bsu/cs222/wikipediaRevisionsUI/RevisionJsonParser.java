package edu.bsu.cs222.wikipediaRevisionsUI;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionJsonParser {

    InputStreamReader inputStreamReader;

    public RevisionJsonParser(InputStreamReader passedInputStreamReader){
        this.inputStreamReader = passedInputStreamReader;
    }

    public List<Revision> createRevisionListFromJson() throws ParseException {
        JsonObject wikiPagesObject = new MediaWikiJsonParser(this.inputStreamReader).wikiPagesObject;
        JsonArray revisionsArrayFromJsonObject = new JsonArray();
        List<Revision> revisionsFromJsonArray = new ArrayList<Revision>();

        for (Map.Entry<String, JsonElement> entry : wikiPagesObject.entrySet()){
            JsonObject revisionsJsonObject = entry.getValue().getAsJsonObject();
            revisionsArrayFromJsonObject = revisionsJsonObject.getAsJsonArray("revisions");
        }

        for(JsonElement singleJsonRevision : revisionsArrayFromJsonObject){
            JsonObject revisionObject = singleJsonRevision.getAsJsonObject();
            String user = revisionObject.get("user").getAsString();
            String date = revisionObject.get("timestamp").getAsString();

            Revision revision = new Revision(user, date);
            revisionsFromJsonArray.add(revision);
        }

        return revisionsFromJsonArray;
    }
}

