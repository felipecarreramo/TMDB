package me.felipecarrera.tmdb.tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

/**
 * Created by FelipeCarrera on 8/04/15.
 */
public interface getResponseDelegate
{
    public void onGetResponse(String module , JSONObject res) throws JSONException, SQLException;
}
