package com.example.shami.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.shami.moviedb.Data.Moviedata;
import com.example.shami.moviedb.Fragments.fragmentDetail;
import com.example.shami.moviedb.Fragments.fragmentmain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements fragmentmain.Callback {

    private String Log_Tag="DM SHAMI : "+MainActivity.class.getSimpleName();

    private boolean mTwoPane;

    private static final String DetailFragment_Tag="DFTG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.movies_detail_container)!=null)
        {
            mTwoPane=true;

            if(savedInstanceState==null)
            {
              //  getSupportFragmentManager().beginTransaction().replace(R.id.movies_detail_container,new fragmentDetail(),DetailFragment_Tag).commit();
            }

        }
        else
        {
            mTwoPane=false;
        }


        /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentmain fragment = new fragmentmain();
        fragmentTransaction.add(R.id.activity_main, fragment);
        fragmentTransaction.commit();
        */

    }

    @Override
    public void onItemSelected(Moviedata data) {
        ArrayList<String> movieData=new ArrayList<String>();
        movieData.add(data.getMid());
        movieData.add(data.getMtitle());
        movieData.add(data.getMposterPath());
        movieData.add(data.getMreleaseDate());
        movieData.add(data.getMoverview());
        movieData.add(data.getMuser_ratings());

        if(mTwoPane)
        {
            Bundle arguments=new Bundle();
            arguments.putStringArrayList("movieData",movieData);
            fragmentDetail fragment = new fragmentDetail();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movies_detail_container, fragment)
                    .commit();

        }
        else{

            Intent intent=new Intent(this,MovieDetail.class);
            intent.putStringArrayListExtra("moviedata",movieData);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
