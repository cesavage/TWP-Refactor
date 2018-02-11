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

    private Timestamp getTimestampInLocalTime(String timestamp) throws ParseException {
        SimpleDateFormat wikiDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        wikiDateTimeFormat.setTimeZone(TimeZone.getTimeZone("Z"));
        java.util.Date parsedTimestamp = wikiDateTimeFormat.parse(timestamp);
        Timestamp localTimestamp = new Timestamp(parsedTimestamp.getTime());

        return localTimestamp;
    }

    //TODO -- Public method and public fields creating a hybrid structure.
    public String getRevisionUserName(){
        this.username = username;

        return this.username;
    }
}
