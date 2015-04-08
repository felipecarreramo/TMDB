package me.felipecarrera.tmdb.tools;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


public enum RESTClient implements getResponseDelegate
{

    INSTANCE {
        @Override
        public void onGetResponse(String module, JSONObject res) {
           delegate.onGetResponse(module, res);
        }
    };

    public getResponseDelegate delegate;

    public void GETRequest(String module, String url)
    {
        GetAsyncTask getTask =  new GetAsyncTask(module, url);
        getTask.delegate = this;
        getTask.execute();
    }


    public void POSTRequest(String url)
    {

    }


    public class GetAsyncTask extends AsyncTask<String, Void, JSONObject>
    {

        private String url;
        private String module;
        public getResponseDelegate delegate = null;
        private  DefaultHttpClient httpClient;

        public GetAsyncTask(String module, String url)
        {
            this.url = url;
            this.module = module;
            this.httpClient = new DefaultHttpClient();
        }

        protected JSONObject doInBackground(String ... params)
        {
            return getRequest(this.httpClient, this.url);
        }

        protected void onPostExecute(JSONObject result)
        {
            delegate.onGetResponse(this.module, result);
        }
    }


    private static JSONObject getRequest(HttpClient client, String url)
    {
        // Prepare a request object

        try {
            HttpGet httpget = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = client.execute(httpget, responseHandler);
            return new JSONObject(response.toString());

        } catch (Exception e) {return null;}
    }
}

