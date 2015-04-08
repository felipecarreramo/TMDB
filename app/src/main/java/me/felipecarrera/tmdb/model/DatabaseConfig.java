package me.felipecarrera.tmdb.model;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by diseno_01 on 8/04/15.
 */
public class DatabaseConfig extends OrmLiteConfigUtil
{

    public static void main(String[] args ) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt");
    }

}
