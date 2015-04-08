package me.felipecarrera.tmdb.tools;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Created by FelipeCarrera on 7/04/15.
 */
public enum JSONParser
{
    INSTANCE;

    public JSONArray parseToJSON(HttpResponse response)
    {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            StringBuilder builder = new StringBuilder();
            for (String line = null; (line = reader.readLine()) != null;) {
                builder.append(line).append("\n");
            }
            JSONTokener tokenerr = new JSONTokener(builder.toString());
            return new JSONArray(tokenerr);

        }catch (Exception e)
        {
            return null;
        }

    }
}
