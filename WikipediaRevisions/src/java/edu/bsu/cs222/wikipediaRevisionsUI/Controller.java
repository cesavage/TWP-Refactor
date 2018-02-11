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

        BufferedReader response = new BufferedReader(testConnection);

        String inputLine;
        while ((inputLine = response.readLine()) != null)
            outputField.appendText(inputLine);
        response.close();

    }
}
