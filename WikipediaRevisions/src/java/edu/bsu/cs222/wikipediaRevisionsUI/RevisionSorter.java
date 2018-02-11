package edu.bsu.cs222.wikipediaRevisionsUI;

import java.util.*;
import java.util.stream.Collectors;

public class RevisionSorter {
    public Map<String, List<Revision>> groupRevisionsByUser(List<Revision> revisionsList) {
        Map<String, List<Revision>> userToRevisionMap = this.createUserToRevisionMap(revisionsList);
        // ArrayList<Revision> groupedRevisionsList = new ArrayList<Revision>();
//        for (Map.Entry<String, List<Revision>> entry: userToRevisionMap.entrySet()){
//            List<Revision> currentList = entry.getValue();
//
////            for(Revision currentRevision : currentList){
////                groupedRevisionsList.add(currentRevision);
////            }
//        }
        return userToRevisionMap;
    }


    private Map<String, List<Revision>> createUserToRevisionMap(List<Revision> revisionsList) {
        Map<String, List<Revision>> userToRevisionMap = revisionsList
                .stream()
                .collect(Collectors.groupingBy(Revision::getUser));

        return userToRevisionMap;
    }

    //TODO Start here.
    private void sortUserToRevisionMapRevisionsByTimestamp(Map<String, List<Revision>> userToRevisionMap) {

    }
}
