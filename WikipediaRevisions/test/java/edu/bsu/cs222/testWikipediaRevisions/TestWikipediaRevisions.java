package edu.bsu.cs222.testWikipediaRevisions;

import edu.bsu.cs222.wikipediaRevisionsUI.WikiPageJsonParser;
import org.junit.Assert;
import org.junit.Test;

public class TestWikipediaRevisions {

    @Test
    public void testGetFirstEditorUsername() {

        WikiPageJsonParser testParser = new WikiPageJsonParser();
        Assert.assertEquals("Samf4u", testParser.getFirstRevisionEditorUsername());


    }


}

