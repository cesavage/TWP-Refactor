package edu.bsu.cs222.wikipediaRevisionsUI;

import java.sql.Timestamp;

public class Revision {
    public String user;
    public Timestamp timestamp;

    Revision(String user, Timestamp timestamp){
        this.user = user;
        this.timestamp = timestamp;
    }

    public String getUser(){ return this.user;
    }
}


