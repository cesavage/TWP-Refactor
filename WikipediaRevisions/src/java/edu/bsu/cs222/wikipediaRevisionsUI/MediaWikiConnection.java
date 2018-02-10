package edu.bsu.cs222.wikipediaRevisionsUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MediaWikiConnection {
    private String streamSource;

    public MediaWikiConnection(String streamSource){
        this.streamSource = streamSource;
    }

    public InputStreamReader createInputStreamReader() {
       InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream(streamSource);
       InputStreamReader jsonInputStreamReader = new InputStreamReader(jsonInputStream);

       return jsonInputStreamReader;
    }

}
