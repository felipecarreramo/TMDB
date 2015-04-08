package me.felipecarrera.tmdb.tools;

import org.json.JSONObject;

/**
 * Created by FelipeCarrera on 8/04/15.
 */
public interface getResponseDelegate
{
    public void onGetResponse(String module , JSONObject res);
}
