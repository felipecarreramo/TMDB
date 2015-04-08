package me.felipecarrera.tmdb.model;

import java.util.ArrayList;

public enum App
{
    INSTANCE;

    private ArrayList<TVSerie> series;


    public ArrayList<TVSerie> listSeries()
    {
        return series;
    }

}