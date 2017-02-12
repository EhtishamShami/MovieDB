package com.example.shami.moviedb.Fragments;

import android.app.Fragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.shami.moviedb.Adapters.PosterAdapter;
import com.example.shami.moviedb.Data.Moviedata;
import com.example.shami.moviedb.Loaders.moviesLoaders;
import com.example.shami.moviedb.R;
import com.example.shami.moviedb.Utilities.MovieUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shami on 1/20/2017.
 */

public class fragmentmain extends Fragment  implements LoaderCallbacks<List<Moviedata>> {

    private static String Log_Tag="DM SHAMI : "+fragmentmain.class.getSimpleName();
    private PosterAdapter Padapter;
    private String url;
    private String apikey;
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private ProgressBar progressBar;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callback {

        public void onItemSelected(Moviedata data);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragmentmain, container, false);

        url=getString(R.string.movieapi);
        apikey=getString(R.string.apikey);

        Padapter=new PosterAdapter(getActivity(),new ArrayList<Moviedata>());
        progressBar=(ProgressBar)view.findViewById(R.id.loadingspinner);
        GridView gridview = (GridView) view.findViewById(R.id.postergrid);
        gridview.setAdapter(Padapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Moviedata currentMovie=Padapter.getItem(i);
                ((Callback) getActivity()).onItemSelected(currentMovie);
            }
        });

        //progressBar=(ProgressBar)view.findViewById(R.id.loadingspinner);

        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this).forceLoad();
        }

        return view ;
    }

    @Override
    public Loader<List<Moviedata>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );
        url=url+orderBy+"?";
        URL myurl= MovieUtil.crateUrl(url,apikey);
        return new moviesLoaders(getActivity(),myurl);

    }

    @Override
    public void onLoadFinished(Loader<List<Moviedata>> loader, List<Moviedata> MoviesData) {

        Padapter.clear();
        progressBar.setVisibility(View.GONE);
        if (MoviesData != null && !MoviesData.isEmpty()) {
            Padapter.addAll(MoviesData);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Moviedata>> loader) {
        Padapter.clear();
    }






}
