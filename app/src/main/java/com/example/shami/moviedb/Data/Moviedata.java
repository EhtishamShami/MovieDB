package com.example.shami.moviedb.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shami on 1/24/2017.
 */

public class Moviedata implements Parcelable {
    private String mid;
    private String mtitle;
    private String mposterPath;
    private String mreleaseDate;
    private String moverview;
    private String muser_ratings;

    public Moviedata(String id,String title,String posterPath,String releaseDate,String overview,String user_ratings)
    {
        mid=id;
        mtitle=title;
        mposterPath=posterPath;
        mreleaseDate=releaseDate;

        moverview=overview;
        muser_ratings=user_ratings;
    }

    private Moviedata(Parcel in)
    {
       mid=in.readString();
       mtitle=in.readString();
       mposterPath=in.readString();
       mreleaseDate=in.readString();
       moverview=in.readString();
       muser_ratings=in.readString();
    }

    public String getMid()    {return mid;}
    public String getMtitle()
    {
        return mtitle;
    }

    public String getMposterPath()
    {
        return mposterPath;
    }

    public String getMreleaseDate()
    {
        return mreleaseDate;
    }

    public String getMoverview()
    {
        return moverview;
    }

    public String getMuser_ratings()
    {
        return muser_ratings;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
     parcel.writeString(mid);
     parcel.writeString(mtitle);
     parcel.writeString(mposterPath);
     parcel.writeString(mreleaseDate);
     parcel.writeString(moverview);
     parcel.writeString(muser_ratings);
}

    public final Parcelable.Creator<Moviedata> CREATOR=new Parcelable.Creator<Moviedata>()
    {
        @Override
        public Moviedata createFromParcel(Parcel parcel) {
            return new Moviedata(parcel);
        }

        @Override
        public Moviedata[] newArray(int i) {
            return new Moviedata[i];
        }
    };
}
