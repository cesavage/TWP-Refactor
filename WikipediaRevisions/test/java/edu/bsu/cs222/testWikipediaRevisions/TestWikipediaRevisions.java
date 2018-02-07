package edu.bsu.cs222.testWikipediaRevisions;

import edu.bsu.cs222.wikipediaRevisionsUI.WikiPageJsonParser;
import org.junit.Assert;
import org.junit.Test;

public class TestWikipediaRevisions {

    @Test
    public void getFirstAuthorTest() {

        WikiPageJsonParser Samf4u = new WikiPageJsonParser();
        Assert.assertEquals("\"Samf4u\"", Samf4u.getFirstEditor());


    }


}

