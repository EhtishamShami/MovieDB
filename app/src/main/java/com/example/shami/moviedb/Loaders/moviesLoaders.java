package com.example.shami.moviedb.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.shami.moviedb.Data.Moviedata;
import com.example.shami.moviedb.Utilities.MovieUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Shami on 1/24/2017.
 */

public class moviesLoaders extends AsyncTaskLoader<List<Moviedata>> {

    URL mUrl;
    private  String Log_Tag=moviesLoaders.class.getSimpleName();
    public moviesLoaders(Context context,URL url) {
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
    public List<Moviedata> loadInBackground() {
        if(mUrl==null)
        {
            return null;
        }

        String jsonResponse="";
        List<Moviedata> moviedatas=null;
        try{
            jsonResponse= MovieUtil.makeHttpRequest(mUrl);
            moviedatas=MovieUtil.getjsonData(jsonResponse);

        }
        catch(IOException ex)
        {
            Log.e(Log_Tag,"Error Loading data in background"+ex);
        }

        return moviedatas;
    }
}
