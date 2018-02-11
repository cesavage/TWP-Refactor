package edu.bsu.cs222.wikipediaRevisionsUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class RevisionCollection {

    InputStreamReader inputStreamReader;
    List<Revision> revisionsList;


    public RevisionCollection(InputStreamReader inputStream) throws IOException, ParseException {
        this.inputStreamReader = inputStream;
        revisionsList = this.createRevisionList();

    }



    public List<Revision> getRevisionsByNewestFirst() {
        return this.revisionsList;

    }

    public List<Revision> getRevisionsByOldestFirst(){


        return this.revisionsList;
    }

    private List<Revision> createRevisionList() throws IOException, ParseException {

        JsonObject wikiPagesObject = new MediaWikiJsonParser(this.inputStreamReader).wikiPagesObject;
        JsonArray jrevisions = null;
        List<Revision> localRevisionsList = new ArrayList<Revision>();

        for (Map.Entry<String, JsonElement> entry : wikiPagesObject.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            jrevisions = entryObject.getAsJsonArray("revisions");
        }


        for(JsonElement crevision : jrevisions){
            JsonObject revisionObject = crevision.getAsJsonObject();
            String user = revisionObject.get("user").getAsString();
            String date = revisionObject.get("timestamp").getAsString();

            Revision wikiPageRevisionObject = new Revision(user, date);
            localRevisionsList.add(wikiPageRevisionObject);
        }

        return localRevisionsList;
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

