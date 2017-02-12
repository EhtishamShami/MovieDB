package com.example.shami.moviedb.Data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Shami on 2/8/2017.
 */

public final class MovieContract {

    ////I made this contrctur private so this class cant be initialized with instance
    MovieContract(){}


    public static final String Content_Authority="com.example.shami.moviedb";
    public static final String Path_Favourites="Favourites";

    public static final Uri Base_Content_Uri= Uri.parse("content://"+Content_Authority);


    public static final class MoviesData implements BaseColumns
    {

    public static final Uri Content_Uri=Uri.withAppendedPath(Base_Content_Uri,Path_Favourites);

        public static final String Table_Name="Favourites";

        public final static String mid=BaseColumns._ID;
        public final static String mtitle="mtitle";
        public final static String mposter="mposter";
        public final static String mreleaseDate="mreleaseDate";
        public final static String moverview="moverview";
        public final static String muser_ratings="mratings";



    }





}
