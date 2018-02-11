package edu.bsu.cs222.wikipediaRevisionsUI;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MediaWikiAPIConnection {
    @SuppressWarnings("UnnecessaryLocalVariable") //Allow for different InputStreamReaders to be created in the future using the same input.
    private String pageTitle;

    public MediaWikiAPIConnection(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public InputStreamReader connect() throws IOException {
        String baseApiCall = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&rvlimit=30&titles=";
        URL apiCall = new URL( baseApiCall + this.pageTitle);
        URLConnection apiConnection = apiCall.openConnection();

        return new InputStreamReader(apiConnection.getInputStream());
    }

}
