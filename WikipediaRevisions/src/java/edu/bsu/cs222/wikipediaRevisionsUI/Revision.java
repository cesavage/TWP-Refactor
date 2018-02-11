package edu.bsu.cs222.wikipediaRevisionsUI;

public class Revision {
    public String username;
    private String timestamp;

    Revision(String username, String timestamp){
        this.username = username;
        this.timestamp = timestamp;
    }
}


