package com.example.shami.moviedb.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Shami on 2/8/2017.
 */

public class MovieProvider extends ContentProvider
{

    private static final String Log_Tag="[DM]Shami "+MovieProvider.class.getSimpleName();

    movieDbHelper dbhelper;

    @Override
    public boolean onCreate() {

        dbhelper=new movieDbHelper(getContext());
        return true;
    }


    private static final int Movies=101;
    private static final int Movie_id=102;

    private static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static{

        sUriMatcher.addURI(MovieContract.Content_Authority,MovieContract.Path_Favourites,Movies);
        sUriMatcher.addURI(MovieContract.Content_Authority,MovieContract.Path_Favourites+"/#",Movie_id);

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db=dbhelper.getReadableDatabase();
        Cursor cursor=null;

        int match=sUriMatcher.match(uri);
        switch (match)
        {
            case Movies:
                cursor=db.query(MovieContract.MoviesData.Table_Name,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Movie_id:
                selection=MovieContract.MoviesData.mid+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=db.query(MovieContract.MoviesData.Table_Name,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Selection Error: The Uri is incorrect");
        }

       cursor.setNotificationUri(getContext().getContentResolver(),uri);
       return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {


        SQLiteDatabase db=dbhelper.getWritableDatabase();
        long id;
        id=db.insert(MovieContract.MoviesData.Table_Name,null,contentValues);
        Log.v(Log_Tag,"The Inserted Row ID is "+id);
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] projection) {

        int match = sUriMatcher.match(uri);
        int rowsDeleted=0;

        SQLiteDatabase db=dbhelper.getReadableDatabase();

        switch (match)
        {
            case Movies:
                // Delete all rows that match the selection and selection args
                rowsDeleted=db.delete(MovieContract.MoviesData.Table_Name,selection,projection);
                break;
            case Movie_id:
                /// Delete the specfic row will be use to un favourite movies
                selection=MovieContract.MoviesData.mid+"=?";
                projection=new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted=db.delete(MovieContract.MoviesData.Table_Name,selection,projection);
                break;
        }

        if(rowsDeleted>0)
        {
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int rowsUpdated=0;
        int match=sUriMatcher.match(uri);
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        switch (match)
        {
            case Movies:
                rowsUpdated=db.update(MovieContract.MoviesData.Table_Name,contentValues,selection,selectionArgs);
                break;
            case Movie_id:
                selection=MovieContract.MoviesData.mid+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
               rowsUpdated=db.update(MovieContract.MoviesData.Table_Name,contentValues,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Update Error: The Uri is not correct");
        }

        if(rowsUpdated>0)
        {

            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


}
