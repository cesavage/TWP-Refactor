package edu.bsu.cs222.wikipediaRevisionsUI;

import com.google.gson.JsonObject;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MediaWikiConnection {
    private String streamSource = new String();

    public MediaWikiConnection(String streamSource){
        this.streamSource = streamSource;
    }


    public InputStreamReader createInputStreamReader() throws IOException {
       InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream(streamSource);
       InputStreamReader jsonInputStreamReader = new InputStreamReader(jsonInputStream);

       return jsonInputStreamReader;
    }


}
