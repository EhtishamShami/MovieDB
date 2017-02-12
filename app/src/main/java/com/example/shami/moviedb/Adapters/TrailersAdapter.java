package com.example.shami.moviedb.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shami.moviedb.Data.TrailerData;
import com.example.shami.moviedb.R;

import java.util.List;

/**
 * Created by Shami on 2/1/2017.
 */

public class TrailersAdapter  extends ArrayAdapter<TrailerData>{

    private String Log_Tag="DM SHAMI : "+TrailersAdapter.class.getSimpleName();

    public TrailersAdapter(Context context,List<TrailerData> data) {
        super(context,0, data);
    }


    @Nullable
    @Override
    public TrailerData getItem(int position) {
        return super.getItem(position);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View TrailerView=convertView;
        if(TrailerView==null)
        {
            TrailerView= LayoutInflater.from(getContext()).inflate(R.layout.trailers_list_item,parent,false);
        }

        TrailerData currentTrialer=getItem(position);

        TextView trailerTitle=(TextView)TrailerView.findViewById(R.id.trailertxtView);
        trailerTitle.setText(currentTrialer.getTrialer_name());

        return TrailerView;
    }
}
