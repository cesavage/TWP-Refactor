package edu.bsu.cs222.wikipediaRevisionsUI;

import java.io.InputStream;

public class MediaWikiConnection {
    private String streamSource = new String();

    public MediaWikiConnection(String streamSource){
        this.streamSource = streamSource;
    }


    public InputStream createInputStream() {
       InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream(streamSource);

       return jsonInputStream;
    }
}
