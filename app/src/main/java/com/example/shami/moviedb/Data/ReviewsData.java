package com.example.shami.moviedb.Data;

/**
 * Created by Shami on 2/8/2017.
 */

public class ReviewsData {

    private String Review_id;
    private String Review_Author;
    private String Review_content;
    private String Review_url;

    public ReviewsData(String id,String author,String content,String url)
    {
        Review_id=id;
        Review_Author=author;
        Review_content=content;
        Review_url=url;

    }

    public String getReview_id()
    {
        return Review_id;
    }

    public String getReview_Author()
    {
        return Review_Author;
    }

    public String getReview_content()
    {
        return Review_content;
    }

    public String getReview_url()
    {
        return Review_url;
    }

}
