package me.felipecarrera.tmdb.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "TVSeries")
public class TVSerie
{
    @DatabaseField(id = true)
    private int id;

    @DatabaseField
    private String name;
    @DatabaseField
    private String poster;
    @DatabaseField
    private String cover;



    public TVSerie()
    {

    }

    public TVSerie(int id, String name, String poster, String cover)
    {
        this.id = id;
        this.name = name;
        this.poster = poster;
        this.cover = cover;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }
}