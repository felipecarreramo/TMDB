package me.felipecarrera.tmdb;

import android.os.Bundle;
import android.util.Log;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.etsy.android.grid.StaggeredGridView;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.util.ArrayList;

import me.felipecarrera.tmdb.model.DAOTMDB;
import me.felipecarrera.tmdb.model.DatabaseHelper;
import me.felipecarrera.tmdb.model.TVSerie;
import me.felipecarrera.tmdb.tools.DAOAsyncResponse;
import me.felipecarrera.tmdb.view.TVSerieAdapter;

public class ListMSActivity extends OrmLiteBaseActivity<DatabaseHelper> implements DAOAsyncResponse
{

    private StaggeredGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ms);


        DAOTMDB.INSTANCE.setContext(this);
        DAOTMDB.INSTANCE.delegate = this;
        DAOTMDB.INSTANCE.fetchMostPopularSeries();
        gridView = (StaggeredGridView) findViewById(R.id.grid_view);
        LayoutInflater layoutInflater = getLayoutInflater();
        View header = layoutInflater.inflate(R.layout.search_bar, null);
        EditText search = (EditText) header.findViewById(R.id.etSearch);
        gridView.addHeaderView(header);
        search.setHint("Search series...");
        search.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (s.toString().length() > 3)
                {
                    String search = s.toString().replace(" ", "%20");
                    DAOTMDB.INSTANCE.searchSeries(search);
                }

                if (count == 0)
                {
                    DAOTMDB.INSTANCE.fetchMostPopularSeries();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public void onAsyncResponse(String module)
    {
        ArrayList<TVSerie> series = DAOTMDB.INSTANCE.listSeries();
        TVSerieAdapter adapter = new TVSerieAdapter(ListMSActivity.this, R.layout.master_item, series);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
