package edu.bsu.cs222.wikipediaRevisionsUI;

import com.google.gson.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class MediaWikiJsonParser {
    private com.google.gson.JsonParser gsonJsonParser = new com.google.gson.JsonParser();
    private InputStreamReader inputStreamReader;
    public JsonObject wikiRootObject;
    public JsonObject wikiQueryObject;
    public JsonObject wikiPagesObject;
    public JsonObject wikiRevisionsObject;


    MediaWikiJsonParser(InputStreamReader passedStreamReader) throws IOException {
        this.inputStreamReader = passedStreamReader;
        wikiRootObject = gsonJsonParser.parse(inputStreamReader).getAsJsonObject();
        wikiQueryObject = wikiRootObject.getAsJsonObject("query");
        wikiPagesObject = wikiQueryObject.getAsJsonObject("pages");
        wikiRevisionsObject = wikiPagesObject.getAsJsonObject("revisions");
    }

}


