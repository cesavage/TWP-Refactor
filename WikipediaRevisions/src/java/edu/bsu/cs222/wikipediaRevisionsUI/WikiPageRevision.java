package edu.bsu.cs222.wikipediaRevisionsUI;



import java.sql.Timestamp;

public class WikiPageRevision {

    private String username;
    private Timestamp localTimeStamp;

    WikiPageRevision(String username, Timestamp localTimeStamp){

        this.username=username;
        this.localTimeStamp = localTimeStamp;
    }




}
