package me.felipecarrera.tmdb.model;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import me.felipecarrera.tmdb.R;

/**
 * Created by FelipeCarrera on 5/04/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{

    private static final String DATABASE_NAME =  "tmdb.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<TVSerie, Integer> tvDAO;
    private RuntimeExceptionDao<TVSerie, Integer> tvRuntimeDAO = null;


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);

    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
    {
        try {
            TableUtils.createTable(connectionSource, TVSerie.class);
        } catch (SQLException e) {
            e.printStackTrace();
           throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try {
            TableUtils.dropTable(connectionSource, TVSerie.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Dao<TVSerie, Integer> getTvDAO() throws SQLException {
      if (tvDAO == null)
        tvDAO = getDao(TVSerie.class);

        return tvDAO;
    }


    public RuntimeExceptionDao<TVSerie, Integer> getTvRuntimeDAO() {

        if (tvDAO == null)
            tvRuntimeDAO = getRuntimeExceptionDao(TVSerie.class);
        return tvRuntimeDAO;
    }


    @Override
    public void close() {
        super.close();
        tvDAO = null;
        tvRuntimeDAO = null;
    }
}
