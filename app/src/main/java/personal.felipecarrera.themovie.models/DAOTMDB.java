package personal.felipecarrera.themovie.models;
import personal.felipecarrera.themovie.models.RESTClient;

public enum DAOTMDB
{
    INSTANCE;
    private String baseURL = "http://api.themoviedb.org/3/";
    private String APIKey = "809c5260c7c9b1e5d8c5ca2e31671051";

    public void listMostPopularSeries()
    {
        RESTClient.INSTANCE.GETRequest(this.baseURL+"tv/popular/?api_key="+this.APIKey);
    }


    public void searchSeries(String search)
    {
        RESTClient.INSTANCE.GETRequest(this.baseURL+"search/tv/"+"?api_key="+this.APIKey+"&query="+search);
    }




}