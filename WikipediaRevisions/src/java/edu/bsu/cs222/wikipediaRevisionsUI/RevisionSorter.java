package edu.bsu.cs222.wikipediaRevisionsUI;

import java.util.*;
import java.util.stream.Collectors;

public class RevisionSorter {

    public Map<String, List<Revision>> groupRevisionsByUser(List<Revision> revisionsList) {
        Map<String, List<Revision>> userToRevisionMap = this.createUserToRevisionMap(revisionsList);
        this.sortUserToRevisionMapRevisionsByTimestamp(userToRevisionMap);

        return userToRevisionMap;
    }



    private Map<String, List<Revision>> createUserToRevisionMap(List<Revision> revisionsList) {
        return revisionsList.stream().collect(Collectors.groupingBy(Revision::getUser));
    }



    private void sortUserToRevisionMapRevisionsByTimestamp(Map<String, List<Revision>> userToRevisionMap) {
        for (Map.Entry<String, List<Revision>> entry : userToRevisionMap.entrySet()) {
            List<Revision> revisionsFromUser = entry.getValue();

            revisionsFromUser.sort(new Comparator<Revision>() {
                public int compare(Revision revision1, Revision revision2) {
                    return revision2.timestamp.compareTo(revision1.timestamp);
                    }
                });
            }
        }

    private List<String> indexMostActiveUsers(Map<String, List<Revision>> userToRevisionMap){
        List<String> mostActiveUsers = new ArrayList<>();
        int mostRevisionsFound = 0;

        for (Map.Entry<String, List<Revision>> entry: userToRevisionMap.entrySet()){
            int numberOfRevisionsForUser = entry.getValue().size();

            if (numberOfRevisionsForUser > mostRevisionsFound){
                mostRevisionsFound = numberOfRevisionsForUser;
                mostActiveUsers.clear();
                mostActiveUsers.add(entry.getKey());
            }
        }
        //TODO add logic for a tie
        return mostActiveUsers;
    }

//TODO rebuild array list
//Rebuild array list
    // ArrayList<Revision> groupedRevisionsList = new ArrayList<Revision>();
//        for (Map.Entry<String, List<Revision>> entry: userToRevisionMap.entrySet()){
//            List<Revision> currentList = entry.getValue();
//
////            for(Revision currentRevision : currentList){
////                groupedRevisionsList.add(currentRevision);
////            }
//        }



}
