package edu.bsu.cs222.wikipediaRevisionsUI;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Revision {

    public String username;
    public Timestamp localTimeStamp;

    Revision(String username, String timeStamp) throws ParseException {
        this.username=username;
        this.localTimeStamp = this.getTimestampInLocalTime(timeStamp);
    }

    public String getRevisionUserName(){
        this.username = username;

        return this.username;
    }

    private Timestamp getTimestampInLocalTime(String timestamp) throws ParseException {
        SimpleDateFormat wikiTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        wikiTimeFormat.setTimeZone(TimeZone.getTimeZone("Z"));
        java.util.Date parsedDate = wikiTimeFormat.parse(timestamp);
        Timestamp localTimestamp = new Timestamp(parsedDate.getTime());

        return localTimestamp;
    }
}
