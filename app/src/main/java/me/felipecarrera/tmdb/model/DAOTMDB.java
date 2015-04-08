package me.felipecarrera.tmdb.model;

import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.json.JSONObject;

import me.felipecarrera.tmdb.tools.RESTClient;
import me.felipecarrera.tmdb.tools.getResponseDelegate;
import android.content.Context;


public enum DAOTMDB implements getResponseDelegate
{
    INSTANCE {
        @Override
        public void onGetResponse(String module , JSONObject res) {
            if (module.equals("listMostPopularSeries"))
            {

            }
        }
    };
    private String baseURL = "http://api.themoviedb.org/3/";
    private String APIKey = "809c5260c7c9b1e5d8c5ca2e31671051";
    private DatabaseHelper dHelper = null;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    private DatabaseHelper getHelper()
    {
        if (dHelper == null) {
            dHelper = (DatabaseHelper)OpenHelperManager.getHelper(this.context, DatabaseHelper.class);
        }
        return dbHelper;
    }

    public void listMostPopularSeries()
    {
        RESTClient.INSTANCE.delegate = this;
        RESTClient.INSTANCE.GETRequest("listMostPopularSeries", this.baseURL+"tv/popular?api_key="+this.APIKey);
    }


    public void searchSeries(String search)
    {
        RESTClient.INSTANCE.GETRequest("searchSeries", this.baseURL+"search/tv/"+"?api_key="+this.APIKey+"&query="+search);
    }

}