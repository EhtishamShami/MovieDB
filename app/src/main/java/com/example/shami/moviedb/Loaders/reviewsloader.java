package com.example.shami.moviedb.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.shami.moviedb.Data.ReviewsData;
import com.example.shami.moviedb.Utilities.MovieUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Shami on 2/8/2017.
 */

public class reviewsloader extends AsyncTaskLoader<List<ReviewsData>> {

    private static final String Log_tag="[DM]Shami "+reviewsloader.class.getSimpleName();

    URL mUrl;

    public reviewsloader(Context context, URL url) {
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
    public List<ReviewsData> loadInBackground() {
        if(mUrl==null)
        {
            return null;
        }

        String jsonResponse="";
        List<ReviewsData> reviewdataDatas=null;
        try{
            jsonResponse= MovieUtil.makeHttpRequest(mUrl);
            reviewdataDatas=MovieUtil.getjsonReviewsData(jsonResponse);

        }
        catch(IOException ex)
        {
            Log.e(Log_tag,"Error Loading data in background"+ex);
        }

        return reviewdataDatas;

    }


}
