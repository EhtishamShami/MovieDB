package com.example.shami.moviedb.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.shami.moviedb.Data.Moviedata;
import com.example.shami.moviedb.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Shami on 1/20/2017.
 */

public class PosterAdapter  extends ArrayAdapter<Moviedata>{

    private String Log_Tag="DM SHAMI : "+PosterAdapter.class.getSimpleName();

    Context mcontext;
    public PosterAdapter(Context context, List<Moviedata> data)
    {
        super(context,0,data);

        mcontext=context;
    }

    @Nullable
    @Override
    public Moviedata getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridview=convertView;

        if(gridview==null)
        {
            gridview= LayoutInflater.from(getContext()).inflate(R.layout.poster,parent,false);
        }
        Moviedata currentmovie=getItem(position);
        String path=currentmovie.getMposterPath();

        ImageView posterView=(ImageView)gridview.findViewById(R.id.posterImgview);
        posterView.setImageResource(R.drawable.zemaposter);
        Log.e(Log_Tag,"http://image.tmdb.org/t/p/w500/"+path);
        Picasso.with(mcontext)
                .load("http://image.tmdb.org/t/p/w500/"+path)
                .into(posterView);

        return gridview;
    }
}
