package personal.felipecarrera.themovie.models;


import java.util.ArrayList;

public enum App
{
    INSTANCE;

    private ArrayList<Serie> series;


    public ArrayList<Serie> listSeries()
    {
        return series;
    }

}