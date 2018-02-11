package edu.bsu.cs222.wikipediaRevisionsUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class RevisionCollection {
    private InputStreamReader inputStreamReader;
    private List<Revision> revisionsList;

    public RevisionCollection(InputStreamReader inputStream) throws IOException, ParseException {
        this.inputStreamReader = inputStream;
        this.revisionsList = this.createRevisionListFromJson();
    }

    public List<Revision> getRevisionsByNewestFirst() {
        //TODO -- Resort this list if the sort could have been changed elsewhere.
        return this.revisionsList;
    }

    private List<Revision> createRevisionListFromJson() throws IOException, ParseException {
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

    public  List<Revision> getSortedRevisions()  {
       // List<Revision> sortedList = new ArrayList<Revision>();

        Map<String , List<Revision>> sortedMap = revisionsList.stream().collect(Collectors.groupingBy(Revision :: getRevisionUserName));
        //sortedMap.forEach((userNameString, revisionArray ) -> System.out.print(userNameString + "---->" + revisionArray  ));

        //Empty sorted revision list
        ArrayList<Revision> sortedRevisions = new ArrayList<Revision>();

        for (Map.Entry<String, List<Revision>> entry : sortedMap.entrySet()){
                //String currentUserName = entry.getKey();
                List<Revision> currentList = entry.getValue();

                for(Revision currentRevision : currentList){
                    sortedRevisions.add(currentRevision);
                }


        }

        return sortedRevisions;

    }
}

