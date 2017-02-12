package com.example.shami.moviedb.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shami.moviedb.Data.ReviewsData;
import com.example.shami.moviedb.R;

import java.util.List;

/**
 * Created by Shami on 2/8/2017.
 */

public class ReviewsAdapter extends ArrayAdapter<ReviewsData> {
    Context mContext;

    public ReviewsAdapter(Context context, List<ReviewsData> reviewsData) {
        super(context,0, reviewsData);
        mContext=context;
    }

    @Nullable
    @Override
    public ReviewsData getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View ReviewView=convertView;

        if(ReviewView==null)
        {
            ReviewView= LayoutInflater.from(getContext()).inflate(R.layout.reviews_list_item,parent,false);
        }

        ReviewsData currentReview=getItem(position);

        TextView author=(TextView)ReviewView.findViewById(R.id.authortxtview);
    //    TextView review=(TextView)ReviewView.findViewById(R.id.reviewtxtview);

        author.setText(currentReview.getReview_Author());
    //    review.setText(currentReview.getReview_content());

        return ReviewView;
    }



}
