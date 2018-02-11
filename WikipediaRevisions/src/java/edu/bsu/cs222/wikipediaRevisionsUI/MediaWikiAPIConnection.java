package edu.bsu.cs222.wikipediaRevisionsUI;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MediaWikiAPIConnection {

    public MediaWikiAPIConnection() {
    }

    public InputStreamReader connect() throws IOException {
        URL mediaWikiAPIConnection = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=soup&rvlimit=30");
        URLConnection connection = mediaWikiAPIConnection.openConnection();

        return new InputStreamReader(connection.getInputStream());
    }


}
