package com.example.shami.moviedb.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shami on 2/8/2017.
 */

public class movieDbHelper extends SQLiteOpenHelper {

    public static final String Log_Tag="[DM]Shami "+movieDbHelper.class.getSimpleName();

    private static final String Database_Name="MoviesDb";
    private static final int Dbversion=1;

    public movieDbHelper(Context context) {
        super(context, Database_Name, null, Dbversion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

     String Create_Movies_Table="CREATE TABLE "+MovieContract.MoviesData.Table_Name+" ("+
                                MovieContract.MoviesData.mid+" INTEGER PRIMARY KEY, "+
                                MovieContract.MoviesData.mtitle+" TEXT NOT NULL,"+
                                MovieContract.MoviesData.mposter+" BLOB,"+
                                MovieContract.MoviesData.mreleaseDate+" TEXT ,"+
                                MovieContract.MoviesData.moverview+" TEXT ,"+
                                MovieContract.MoviesData.muser_ratings+" TEXT "
                                +");";

     db.execSQL(Create_Movies_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXIST "+MovieContract.MoviesData.Table_Name);
        onCreate(db);
    }
}
