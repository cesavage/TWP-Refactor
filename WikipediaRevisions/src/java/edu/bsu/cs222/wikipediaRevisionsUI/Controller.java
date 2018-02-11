package edu.bsu.cs222.wikipediaRevisionsUI;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Controller {

    public TextArea outputField;
    @FXML
    private TextField searchTerm;

    private final Executor executor = Executors.newSingleThreadExecutor();


    @SuppressWarnings("unused")
    @FXML
    public void getRevisions() throws IOException {
        MediaWikiAPIConnection mediaWikiAPIConnection = new MediaWikiAPIConnection(searchTerm.getText());
        InputStreamReader testConnection = mediaWikiAPIConnection.connect();

        RevisionParser revisionParser = new RevisionParser(testConnection);
        List<Revision> revisionList = revisionParser.createRevisionsListFromJson();

        for(Revision revision : revisionList){
            outputField.appendText(revision.timestamp + "    by    " + revision.user + "\n");

        }

    }
}
