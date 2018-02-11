package edu.bsu.cs222.wikipediaRevisionsUI;

import java.sql.Timestamp;

public class Revision {
    public String user;
    public String timestamp;

    Revision(String user, String timestamp){
        this.user = user;
        this.timestamp = timestamp;
    }

    public String getUser(){
        return this.user;
    }
}


