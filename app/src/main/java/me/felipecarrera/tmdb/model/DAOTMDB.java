package me.felipecarrera.tmdb.model;

import android.util.Log;

import org.json.JSONObject;

import me.felipecarrera.tmdb.tools.RESTClient;
import me.felipecarrera.tmdb.tools.getResponseDelegate;


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

    DAOTMDB()
    {

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