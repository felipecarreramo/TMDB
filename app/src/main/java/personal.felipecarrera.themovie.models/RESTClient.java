package personal.felipecarrera.themovie.models;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
            Log.i("response", ""+ (this.url));
            return null;
        }

        protected void onPostExecute(ArrayList result)
        {

        }
    }


    private Object getRequest(String url)
    {
        // Prepare a request object
        HttpGet httpget = new HttpGet(url);
        try {
            return this.httpClient.execute(httpget);
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