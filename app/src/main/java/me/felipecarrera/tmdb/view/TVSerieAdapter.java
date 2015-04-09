package me.felipecarrera.tmdb.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import com.etsy.android.grid.util.DynamicHeightImageView;
import com.etsy.android.grid.util.DynamicHeightTextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import me.felipecarrera.tmdb.DetailActivity;
import me.felipecarrera.tmdb.R;
import me.felipecarrera.tmdb.model.TVSerie;


/**
 * Created by FelipeCarrera on 8/04/15.
 */
public class TVSerieAdapter extends ArrayAdapter<TVSerie>
{

    private LayoutInflater inflater;
    private ArrayList<TVSerie> data;
    private int layoutResourceId;
    private Context context;


    public TVSerieAdapter(Context context, int resource, ArrayList<TVSerie> data) {
        super(context, resource, data);
        this.data = data;
        this.layoutResourceId = resource;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View item = convertView;
        TVSerieHolder holder = null;
        if (item == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            item = inflater.inflate(layoutResourceId, parent, false);
            holder = new TVSerieHolder();
            holder.posterImage = (DynamicHeightImageView) item.findViewById(R.id.posterItem);
            holder.nameTitle = (DynamicHeightTextView) item.findViewById(R.id.nameItem);
            item.setTag(holder);

        }else
        {
            holder = (TVSerieHolder) item.getTag();

        }

        final TVSerie serie = data.get(position);
        holder.nameTitle.setText(serie.getName());
        Picasso.with(context).load(serie.getCover()).into(holder.posterImage);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("idSerie", serie.getId());
                context.startActivity(i);
            }
        });

        return item;

    }

    static class TVSerieHolder
    {
        DynamicHeightImageView posterImage;
        DynamicHeightTextView nameTitle;
    }


}
