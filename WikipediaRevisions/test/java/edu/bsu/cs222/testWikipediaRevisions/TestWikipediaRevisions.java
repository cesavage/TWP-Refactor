package edu.bsu.cs222.testWikipediaRevisions;

import com.google.gson.*;
import edu.bsu.cs222.wikipediaRevisionsUI.RevisionParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class TestWikipediaRevisions {

    @Test
    public void getFirstAuthorTest() {

        RevisionParser Samf4u = new RevisionParser();
        Assert.assertEquals("\"Samf4u\"", Samf4u.getFirstAuthor());


    }


}

