package edu.bsu.cs222.wikipediaRevisionsUI;

import java.sql.Timestamp;

public class Revision {

    public String username;
    public Timestamp localTimeStamp;

    Revision(String username, Timestamp localTimeStamp){
        this.username=username;
        this.localTimeStamp = localTimeStamp;
    }

}
