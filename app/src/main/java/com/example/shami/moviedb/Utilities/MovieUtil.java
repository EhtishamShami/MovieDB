package com.example.shami.moviedb.Utilities;

import android.net.Uri;
import android.util.Log;

import com.example.shami.moviedb.Data.Moviedata;
import com.example.shami.moviedb.Data.ReviewsData;
import com.example.shami.moviedb.Data.TrailerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Shami on 1/24/2017.
 */

public final class MovieUtil {

    private MovieUtil(){}


    private static String Log_Tag="DM SHAMI : "+MovieUtil.class.getSimpleName();

    public static URL crateUrl(String url,String apikey)
    {
        URL murl=null;
        try
        {
            Uri builtUri=Uri.parse(url)
                    .buildUpon().appendQueryParameter("api_key",apikey).build();
            murl=new URL(builtUri.toString());
        }
        catch(MalformedURLException ex)
        {
            Log.e(Log_Tag,"Error in building Url"+ex);
        }

        return murl;
    }

    public static String makeHttpRequest(URL url)throws IOException {

        String jsonResponse="";
        if (url == null)
        {
            return jsonResponse;
        }
        HttpURLConnection connection=null;
        InputStream in=null;
        try{
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET" );
            connection.setConnectTimeout(20000 /* milliseconds */);
            connection.setReadTimeout(20000 /* milliseconds */);
            connection.connect();
            if(connection.getResponseCode()==200)
            {
                in=connection.getInputStream();
               jsonResponse=readfromstream(in);
            }
        }
        catch (IOException ex)
        {
            Log.e(Log_Tag,"Error in Connection"+ex);

        }
        finally {
            if(connection!=null)
            {
                connection.disconnect();
            }
            if(in!=null)
            {
                in.close();
            }
        }
        Log.e(Log_Tag,jsonResponse);
       return jsonResponse;
    }

    public static String readfromstream(InputStream inputStream)throws IOException
    {
        StringBuilder builder=new StringBuilder();
        if(inputStream!=null)
        {
            InputStreamReader reader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=bufferedReader.readLine();
            while(line!=null)
            {
                builder.append(line);
                line=bufferedReader.readLine();
            }
        }

        return builder.toString();
    }

    public static ArrayList<Moviedata> getjsonData(String jsonString)
    {
        ArrayList<Moviedata> moviedatas=new ArrayList<Moviedata>();
        try
        {
            JSONObject object=new JSONObject(jsonString);
            JSONArray result=object.getJSONArray("results");
            for(int i=0;i<result.length();i++)
            {
                JSONObject data=result.getJSONObject(i);
                String id=data.getString("id");
                String title=data.getString("original_title");
                String posterpath=data.getString("poster_path");
                String overview=data.getString("overview");
                String release_date=data.getString("release_date");
                String ratings=data.getString("vote_average");
                moviedatas.add(new Moviedata(id,title,posterpath,release_date,overview,ratings));
            }
        }
        catch (JSONException ex)
        {
            Log.e(Log_Tag,"Error Parsing Json"+ex);
        }

        return  moviedatas;
    }


    public static ArrayList<TrailerData> getjsonTrialerData(String jsonString)
    {
        ArrayList<TrailerData> TrialersData=new ArrayList<TrailerData>();
        try
        {
            JSONObject object=new JSONObject(jsonString);
            JSONArray result=object.getJSONArray("youtube");
            for(int i=0;i<result.length();i++)
            {
                JSONObject data=result.getJSONObject(i);
                String name=data.getString("name");
                String size=data.getString("size");
                String source=data.getString("source");
                String type=data.getString("type");
                TrialersData.add(new TrailerData(name,size,source,type));
            }
        }
        catch (JSONException ex)
        {
            Log.e(Log_Tag,"Error Parsing Json"+ex);
        }
        return  TrialersData;
    }

    public static ArrayList<ReviewsData> getjsonReviewsData(String jsonString)
    {
        ArrayList<ReviewsData> reviewsDatas=new ArrayList<ReviewsData>();
        try
        {
            JSONObject object=new JSONObject(jsonString);
            JSONArray result=object.getJSONArray("results");
            for(int i=0;i<result.length();i++)
            {
                JSONObject data=result.getJSONObject(i);
                String id=data.getString("id");
                String author=data.getString("author");
                String content=data.getString("content");
                String url=data.getString("url");

                reviewsDatas.add(new ReviewsData(id,author,content,url));
            }
        }
        catch (JSONException ex)
        {
            Log.e(Log_Tag,"Error Parsing Json"+ex);
        }

        return  reviewsDatas;
    }




}
