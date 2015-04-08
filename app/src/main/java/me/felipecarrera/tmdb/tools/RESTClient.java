package me.felipecarrera.tmdb.tools;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public enum RESTClient
{
    INSTANCE;

    private DefaultHttpClient httpClient;

    private RESTClient()
    {
        this.httpClient = new DefaultHttpClient();
    }
    public void GETRequest(String url)
    {
        new GetAsyncTask(url).execute();
    }


    public void POSTRequest(String url)
    {

    }


    public class GetAsyncTask extends AsyncTask<String, Void, ArrayList>
    {

        private String url;

        public GetAsyncTask(String url)
        {
            this.url = url;
        }

        protected ArrayList doInBackground(String ... params)
        {
            JSONArray o = getRequest(this.url);
            Log.i("response object", o.toString());
            return null;
        }

        protected void onPostExecute(ArrayList result)
        {

        }
    }


    private JSONArray getRequest(String url)
    {
        // Prepare a request object
        HttpGet httpget = new HttpGet(url);
        try {

            return JSONParser.INSTANCE.parseToJSON(this.httpClient.execute(httpget));

        } catch (Exception e) {return null;}
    }

    private Object post(String url)
    {
        // Prepare a request object
        HttpPost httppost = new HttpPost(url);
        try {
            return this.httpClient.execute(httppost);
        } catch (Exception e) { return null; }
    }
}