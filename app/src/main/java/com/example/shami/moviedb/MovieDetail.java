package com.example.shami.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shami.moviedb.Fragments.fragmentDetail;

import java.util.ArrayList;

public class MovieDetail extends AppCompatActivity  {
    private String Log_Tag="DM SHAMI : "+MovieDetail.class.getSimpleName();

    ArrayList<String> movieData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent getExtra=getIntent();
        movieData=getExtra.getStringArrayListExtra("moviedata");

        if(savedInstanceState==null) {
            Bundle arguments=new Bundle();
            arguments.putStringArrayList("movieData",movieData);
            fragmentDetail fragment = new fragmentDetail();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movies_detail_container, fragment)
                    .commit();
        }


/*

        title=(TextView)findViewById(R.id.txtTitle);
        year=(TextView)findViewById(R.id.txtyear);
        ratings=(TextView)findViewById(R.id.txtratings);
        overView=(TextView)findViewById(R.id.txtoverview);
        poster=(ImageView)findViewById(R.id.thumbnail);
        poster.setDrawingCacheEnabled(true);
        poster.buildDrawingCache();

        title.setText(movieData[1]);
       year.setText(movieData[3]);
       overView.setText(movieData[4]);
     ratings.setText(movieData[5]);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"+movieData[2]).into(poster);

        trailer=(ListView)findViewById(R.id.trialerslist);
        reviews=(ListView)findViewById(R.id.reviewlist);
        T_adapter=new TrailersAdapter(this,new ArrayList<TrailerData>());
        R_adapter=new ReviewsAdapter(this,new ArrayList<ReviewsData>());
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
    //    new loadTrailer().TrigerLoader();
    //    new LoadReviews().TrigerLoader();

        markfavourite=(Button)findViewById(R.id.favouritebtn);
        markfavourite.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {

        MarkFavourite();
        markfavourite.setText("Unmark");
            }
        });
*/
    }


/*
    public void MarkFavourite()
    {

        byte[] poster=ImagetoByte();
        ContentValues values=new ContentValues();
        //values.put(MovieContract.MoviesData.mid,movieData[0]);
        //values.put(MovieContract.MoviesData.mtitle,movieData[1]);
        //values.put(MovieContract.MoviesData.mposter,poster);
        //values.put(MovieContract.MoviesData.mreleaseDate,movieData[3]);
        //values.put(MovieContract.MoviesData.moverview,movieData[4]);
        //values.put(MovieContract.MoviesData.muser_ratings,movieData[5]);

        Uri uri =getContentResolver().insert(MovieContract.MoviesData.Content_Uri,values);

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
            getSupportLoaderManager().initLoader(0,null,this);
        }
        @Override
        public Loader<List<TrailerData>> onCreateLoader(int id, Bundle args) {

            String api="ded925ce33137ca8ee5927b65bdb26db";URL uri=MovieUtil.crateUrl("http://api.themoviedb.org/3/movie/"+movieData[0]+"/trailers?",api);
            return new trailerLoader(getApplicationContext(),uri);
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
            getSupportLoaderManager().initLoader(1,null,this);
        }


        @Override
        public Loader<List<ReviewsData>> onCreateLoader(int id, Bundle args) {

            String api="ded925ce33137ca8ee5927b65bdb26db";
          //  URL uri=MovieUtil.crateUrl("http://api.themoviedb.org/3/movie/"+movieData[0]+"/reviews?",api);
            //return new reviewsloader(getApplicationContext(),uri);

        return null;
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


*/

}
