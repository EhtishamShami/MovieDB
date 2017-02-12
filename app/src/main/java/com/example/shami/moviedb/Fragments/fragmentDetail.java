package com.example.shami.moviedb.Fragments;

/**
 * Created by Shami on 2/10/2017.
 */


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shami.moviedb.Adapters.ReviewsAdapter;
import com.example.shami.moviedb.Adapters.TrailersAdapter;
import com.example.shami.moviedb.Data.MovieContract;
import com.example.shami.moviedb.Data.ReviewsData;
import com.example.shami.moviedb.Data.TrailerData;
import com.example.shami.moviedb.Loaders.reviewsloader;
import com.example.shami.moviedb.Loaders.trailerLoader;
import com.example.shami.moviedb.R;
import com.example.shami.moviedb.Utilities.MovieUtil;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class fragmentDetail extends Fragment  {

    public static final String Log_Tag="[DM]Shami "+fragmentDetail.class.getSimpleName();

    TextView title;
    TextView year;
    TextView ratings;
    TextView overView;
    ImageView poster;

    Button markfavourite;

    ListView trailer;
    ListView reviews;
    TrailersAdapter T_adapter;
    ReviewsAdapter R_adapter;
//    public static String[] movieData;

    ArrayList<String> movieDataList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View DetialView=inflater.inflate(R.layout.fragmentdetail,container,false);

        movieDataList = getArguments().getStringArrayList("movieData");


        title=(TextView)DetialView.findViewById(R.id.txtTitle);
        year=(TextView)DetialView.findViewById(R.id.txtyear);
        ratings=(TextView)DetialView.findViewById(R.id.txtratings);
        overView=(TextView)DetialView.findViewById(R.id.txtoverview);
        poster=(ImageView)DetialView.findViewById(R.id.thumbnail);
        poster.setDrawingCacheEnabled(true);
        poster.buildDrawingCache();

        trailer=(ListView)DetialView.findViewById(R.id.trialerslist);
        reviews=(ListView)DetialView.findViewById(R.id.reviewlist);
        T_adapter=new TrailersAdapter(getActivity(),new ArrayList<TrailerData>());
        R_adapter=new ReviewsAdapter(getActivity(),new ArrayList<ReviewsData>());
        trailer.setAdapter(T_adapter);
        reviews.setAdapter(R_adapter);
        markfavourite=(Button)DetialView.findViewById(R.id.favouritebtn);

        title.setText(movieDataList.get(1));
        year.setText(movieDataList.get(3));
        overView.setText(movieDataList.get(4));
        ratings.setText(movieDataList.get(5));
        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w500/"+movieDataList.get(2)).into(poster);
        trailer.setAdapter(T_adapter);
        reviews.setAdapter(R_adapter);

        trailer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TrailerData currentTrailer=T_adapter.getItem(position);
                String url="https://www.youtube.com/watch?v="+currentTrailer.getTrailer_source();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        reviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ReviewsData currentreview=R_adapter.getItem(position);
                ///Will code it later
            }
        });

        markfavourite=(Button)DetialView.findViewById(R.id.favouritebtn);
        markfavourite.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {

                MarkFavourite();
                markfavourite.setText("Unmark");
            }
        });





        new loadTrailer().TrigerLoader();
        new LoadReviews().TrigerLoader();

        return DetialView;


    }



    public void MarkFavourite()
    {

        byte[] poster=ImagetoByte();
        ContentValues values=new ContentValues();
        values.put(MovieContract.MoviesData.mid,movieDataList.get(0));
        values.put(MovieContract.MoviesData.mtitle,movieDataList.get(1));
        values.put(MovieContract.MoviesData.mposter,poster);
        values.put(MovieContract.MoviesData.mreleaseDate,movieDataList.get(3));
        values.put(MovieContract.MoviesData.moverview,movieDataList.get(4));
        values.put(MovieContract.MoviesData.muser_ratings,movieDataList.get(5));

        Uri uri =getActivity().getContentResolver().insert(MovieContract.MoviesData.Content_Uri,values);

        Log.v(Log_Tag,"The Record with "+ ContentUris.parseId(uri)+" has been saved");

    }

    public byte[] ImagetoByte()
    {
        Bitmap b=poster.getDrawingCache() ;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] img = bos.toByteArray();
        return img;
    }





    public class loadTrailer implements LoaderManager.LoaderCallbacks<List<TrailerData>>
    {

        public void TrigerLoader()
        {
            getActivity().getSupportLoaderManager().initLoader(0,null,this);
        }
        @Override
        public Loader<List<TrailerData>> onCreateLoader(int id, Bundle args) {

            String api="ded925ce33137ca8ee5927b65bdb26db";
            URL uri= MovieUtil.crateUrl("http://api.themoviedb.org/3/movie/"+movieDataList.get(0)+"/trailers?",api);
            return new trailerLoader(getActivity(),uri);
        }

        @Override
        public void onLoadFinished(Loader<List<TrailerData>> loader, List<TrailerData> data) {
            T_adapter.clear();
            if (data != null && !data.isEmpty()) {
                T_adapter.addAll(data);
                Log.v(Log_Tag,"The size of Data is "+data.size());
            }
        }

        @Override
        public void onLoaderReset(Loader<List<TrailerData>> loader) {
            T_adapter.clear();
        }

    }



    public class LoadReviews implements LoaderManager.LoaderCallbacks<List<ReviewsData>>
    {
        public void TrigerLoader()
        {
            getActivity().getSupportLoaderManager().initLoader(1,null,this);
        }


        @Override
        public Loader<List<ReviewsData>> onCreateLoader(int id, Bundle args) {

            String api="ded925ce33137ca8ee5927b65bdb26db";
            URL uri=MovieUtil.crateUrl("http://api.themoviedb.org/3/movie/"+movieDataList.get(0)+"/reviews?",api);
            return new reviewsloader(getActivity(),uri);
        }

        @Override
        public void onLoadFinished(Loader<List<ReviewsData>> loader, List<ReviewsData> data) {
            R_adapter.clear();
            if(data!=null&&!data.isEmpty())
            {
                R_adapter.addAll(data);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<ReviewsData>> loader) {
            R_adapter.clear();
        }
    }




}
