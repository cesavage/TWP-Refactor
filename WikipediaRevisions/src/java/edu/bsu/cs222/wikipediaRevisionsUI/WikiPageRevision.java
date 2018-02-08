package edu.bsu.cs222.wikipediaRevisionsUI;



import java.sql.Timestamp;

public class WikiPageRevision {

    public String username;
    public Timestamp localTimeStamp;

    WikiPageRevision(String username, Timestamp localTimeStamp){

        this.username=username;
        this.localTimeStamp = localTimeStamp;
    }




}
