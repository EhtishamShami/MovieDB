package com.example.shami.moviedb.Data;

/**
 * Created by Shami on 2/8/2017.
 */

public class TrailerData {

    private String Trialer_name;
    private String Trialer_size;
    private String Trailer_source;
    private String Trailer_type;

    public TrailerData(String name,String size,String source,String type)
    {
        Trialer_name=name;
        Trialer_size=size;
        Trailer_source=source;
        Trailer_type=type;

    }

    public String getTrialer_name() {
        return Trialer_name;
    }

    public String getTrialer_size()
    {
        return Trialer_size;
    }

    public String getTrailer_source()
    {
        return Trailer_source;
    }

    public String getTrailer_type()
    {
        return Trailer_type;
    }

}
