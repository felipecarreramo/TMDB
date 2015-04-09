package me.felipecarrera.tmdb.model;

import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.felipecarrera.tmdb.tools.DAOAsyncResponse;
import me.felipecarrera.tmdb.tools.RESTClient;
import me.felipecarrera.tmdb.tools.getResponseDelegate;
import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;


public enum DAOTMDB implements getResponseDelegate
{
    INSTANCE {
        @Override
        public void onGetResponse(String module , JSONObject res) throws JSONException, SQLException {


            if (module.equals("fetchMostPopularSeries"))
            {
                JSONArray results =  res.getJSONArray("results");
                for (int i = 0 ; i < results.length(); i++ )
                {
                    JSONObject jTVObject = results.getJSONObject(i);
                    TVSerie tvSerie = new TVSerie(jTVObject.getInt("id"),
                                                jTVObject.getString("name"),
                                                DAOTMDB.INSTANCE.imagesPath+jTVObject.getString("poster_path"),
                                                DAOTMDB.INSTANCE.imagesPath+jTVObject.getString("backdrop_path"),
                                                null, 0);
                    DAOTMDB.INSTANCE.daoTV.delete(tvSerie);
                    DAOTMDB.INSTANCE.daoTV.create(tvSerie);
                }
            }else if (module.equals("searchSeries"))
            {
                JSONArray results =  res.getJSONArray("results");
                DAOTMDB.INSTANCE.daoTV.delete( DAOTMDB.INSTANCE.listSeries());
                for (int i = 0 ; i < results.length(); i++ )
                {
                    JSONObject jTVObject = results.getJSONObject(i);
                    TVSerie tvSerie = new TVSerie(jTVObject.getInt("id"),
                            jTVObject.getString("name"),
                            DAOTMDB.INSTANCE.imagesPath+jTVObject.getString("poster_path"),
                            DAOTMDB.INSTANCE.imagesPath+jTVObject.getString("backdrop_path"),
                            null, 0);
                    DAOTMDB.INSTANCE.daoTV.create(tvSerie);
                }

            }else if(module.equals("fetchMoreInfoOfSerie"))
            {
                Log.i("aquiii","si");

                TVSerie serieToUpdate  = DAOTMDB.INSTANCE.findSerieById(res.getInt("id"));
                serieToUpdate.setNumberSeasons(res.getInt("number_of_seasons"));
                serieToUpdate.setOverview(res.getString("overview"));
                Log.i("serie", res.getString("overview"));
                DAOTMDB.INSTANCE.daoTV.update(serieToUpdate);
            }
            delegate.onAsyncResponse(module);
        }
    };
    private String baseURL = "http://api.themoviedb.org/3/";
    private String APIKey = "809c5260c7c9b1e5d8c5ca2e31671051";
    private String imagesPath = "https://image.tmdb.org/t/p/w500";
    private DatabaseHelper dHelper = null;
    private Context context;
    private DatabaseHelper helper;
    private RuntimeExceptionDao<TVSerie, Integer> daoTV;
    public DAOAsyncResponse delegate;

    DAOTMDB()
    {
        this.helper = this.getHelper();
        this.daoTV = helper.getTvRuntimeDAO();
        RESTClient.INSTANCE.delegate = this;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DatabaseHelper getHelper()
    {
        if (dHelper == null) {
            dHelper = (DatabaseHelper)OpenHelperManager.getHelper(this.context, DatabaseHelper.class);
        }
        return dHelper;
    }

    public ArrayList listSeries()
    {
        return  (ArrayList)this.daoTV.queryForAll();
    }

    public TVSerie findSerieById(int id)
    {
        return this.daoTV.queryForId(id);
    }

    public void fetchMostPopularSeries()
    {
        RESTClient.INSTANCE.GETRequest("fetchMostPopularSeries", this.baseURL+"tv/popular?api_key="+this.APIKey);
    }


    public void fetchMoreInfoOfSerie(int id)
    {
        RESTClient.INSTANCE.GETRequest("fetchMoreInfoOfSerie", this.baseURL+"tv/"+id+"?api_key="+this.APIKey);
    }


    public void searchSeries(String search)
    {
        RESTClient.INSTANCE.GETRequest("searchSeries", this.baseURL+"search/tv"+"?api_key="+this.APIKey+"&query="+search);
    }

}