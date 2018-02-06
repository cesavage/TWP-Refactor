package edu.bsu.cs222.testWikipediaRevisions;

import com.google.gson.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class TestWikipediaRevisions {

    @Test
    public void testCountRevisions() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);

        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject revisions = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
       // JsonObject name = revisions.getAsJsonObject("revisions").getAsJsonObject("user");
        JsonArray array = null;
        JsonArray names = null;



        for (Map.Entry<String, JsonElement> entry : revisions.entrySet()){
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");




        }

        JsonElement firstUser = array.get(3);
        JsonObject firstElement = firstUser.getAsJsonObject();
        String  theName = firstElement.getAsJsonPrimitive("user").toString();




      /*  for (Map.Entry<String, JsonElement> UserInput : name.entrySet()){
            JsonObject nameObject = UserInput.getValue().getAsJsonObject();
            names = nameObject.getAsJsonArray("user");
        }

*/




        //System.out.print(array);
        //System.out.print(firstUser);
        System.out.print(theName);
        //System.out.print(names);

        Assert.assertEquals("\"Samf4u\"", theName);

    }


}

