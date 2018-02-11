package edu.bsu.cs222.wikipediaRevisionsUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Controller {

    public TextArea outputField;
    @FXML
    private TextField urlField;

    private final Executor executor = Executors.newSingleThreadExecutor();


    @SuppressWarnings("unused")
    @FXML
    public void getRevisions() throws IOException {
        MediaWikiAPIConnection mediaWikiAPIConnection = new MediaWikiAPIConnection("soup");
        InputStreamReader testConnection = mediaWikiAPIConnection.connect();

        RevisionParser revisionParser = new RevisionParser();
        List<Revision> revisionList = revisionParser.parse(testConnection);

        for(Revision revision : revisionList){
            outputField.appendText(revision.timestamp + "    by    " + revision.user + "\n");

        }

    }
}
