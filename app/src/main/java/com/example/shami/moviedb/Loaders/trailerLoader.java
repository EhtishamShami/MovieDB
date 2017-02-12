package com.example.shami.moviedb.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.shami.moviedb.Data.TrailerData;
import com.example.shami.moviedb.Utilities.MovieUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Shami on 2/8/2017.
 */

public class trailerLoader extends AsyncTaskLoader<List<TrailerData>> {


    private static final String Log_tag="[DM]Shami "+trailerLoader.class.getSimpleName();

    URL mUrl;

    public trailerLoader(Context context, URL url) {
        super(context);
        mUrl=url;
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }


    @Override
    public List<TrailerData> loadInBackground() {
        if(mUrl==null)
        {
            return null;
        }

        String jsonResponse="";
        List<TrailerData> trailerDatas=null;
        try{
            jsonResponse= MovieUtil.makeHttpRequest(mUrl);
            trailerDatas=MovieUtil.getjsonTrialerData(jsonResponse);

        }
        catch(IOException ex)
        {
            Log.e(Log_tag,"Error Loading data in background"+ex);
        }

        return trailerDatas;

    }
}
