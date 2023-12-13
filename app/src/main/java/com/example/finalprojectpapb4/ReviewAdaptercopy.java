package com.example.finalprojectpapb4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdaptercopy extends RecyclerView.Adapter<ReviewAdaptercopy.ReviewViewHolder> {


    private Context context;
    private List<ReviewModel> reviewList;

    public ReviewAdaptercopy(Context context, List<ReviewModel> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }
//


    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_list, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewModel reviewModel = reviewList.get(position);

        // Set data to views
        holder.locationTextView.setText(reviewModel.getLocation());
        holder.dateTextView.setText(reviewModel.getDate());
        holder.nameTextView.setText(reviewModel.getUserId());
        holder.reviewTextView.setText(reviewModel.getReview());
    }


    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView locationTextView, dateTextView, nameTextView, reviewTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.tv_lokasi);
            dateTextView = itemView.findViewById(R.id.tv_date);
            nameTextView = itemView.findViewById(R.id.tv_nama);
            reviewTextView = itemView.findViewById(R.id.tv_review);
        }
    }
}
