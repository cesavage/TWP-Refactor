package edu.bsu.cs222.wikipediaRevisionsUI;

import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class RevisionCollection {
    private InputStreamReader inputStreamReader;
    private List<Revision> revisionsList;

    public RevisionCollection(InputStreamReader inputStream) throws ParseException {
        this.inputStreamReader = inputStream;
        RevisionJsonParser revisionJsonParser = new RevisionJsonParser(inputStream);
        this.revisionsList = revisionJsonParser.createRevisionListFromJson();
    }

    public List<Revision> sortRevisionsByNewestFirst() {
        //TODO -- Resort this list if the sort could have been changed elsewhere.
        return this.revisionsList;
    }

    public Map<String, List<Revision>> createUserRevisionMap() {
        Map<String, List<Revision>> groupedUsersMap = revisionsList
                .stream()
                .collect(Collectors.groupingBy(Revision::getRevisionUserName));
        return groupedUsersMap;
    }


    public Map<String, List<Revision>> sortRevisionsByUser() {
        Map<String, List<Revision>> revisionsGroupedByUser = this.createUserRevisionMap();

        for (Map.Entry<String, List<Revision>> entry : revisionsGroupedByUser.entrySet()) {
            List<Revision> revisionsFromUser = entry.getValue();

            Collections.sort(revisionsFromUser, new Comparator<Revision>() {
                public int compare(Revision revision2, Revision revision1) {
                    return Integer.valueOf((revision1.localTimeStamp).compareTo(revision2.localTimeStamp));
                }
            });
        }
        return revisionsGroupedByUser;
    }
}



//    public  List<Revision> getSortedRevisions()  {
//        Map<String , List<Revision>> sortedMap = revisionsList
//                .stream()
//                .collect(Collectors.groupingBy(Revision :: getRevisionUserName));
//
//        ArrayList<Revision> sortedRevisions = new ArrayList<Revision>();
//
//        for (Map.Entry<String, List<Revision>> entry : sortedMap.entrySet()){
//            List<Revision> currentList = entry.getValue();
//
//            for(Revision currentRevision : currentList){
//                sortedRevisions.add(currentRevision);
//            }
//        }
//
//        return sortedRevisions;
//    }

