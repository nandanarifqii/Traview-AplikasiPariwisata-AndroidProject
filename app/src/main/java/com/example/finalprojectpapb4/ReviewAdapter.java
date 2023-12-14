package com.example.finalprojectpapb4;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.text.SimpleDateFormat;

public class ReviewAdapter extends FirebaseRecyclerAdapter<ReviewModel, ReviewAdapter.ReviewViewHolder> {

    public ReviewAdapter(@NonNull FirebaseRecyclerOptions<ReviewModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull ReviewModel model) {
        // Bind data to views
        Log.d("ReviewAdapter", "Binding data for position: " + position);
        holder.locationTextView.setText(model.getLocation());
        Log.d("ReviewAdapter", "Location: " + model.getLocation());

        if (model.getDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            holder.dateTextView.setText(dateFormat.format(model.getDate()));
            Log.d("ReviewAdapter", "Date: " + dateFormat.format(model.getDate()));
        } else {
            holder.dateTextView.setText("Unknown Date");
            Log.d("ReviewAdapter", "Date is null, using default value");
        }

        holder.namaTextView.setText(model.getUserId());
        Log.d("ReviewAdapter", "UserId: " + model.getUserId());

        holder.reviewTextView.setText(model.getReview());
        Log.d("ReviewAdapter", "Review: " + model.getReview());

        // Load image using Glide if available
        if (model.getImageUri() != null) {
            Log.d("ReviewAdapter", "ImageUri: " + model.getImageUri());
            Glide.with(holder.itemView.getContext())
                    .load(model.getImageUri())
                    .into(holder.imageView);
        } else {
            Log.d("ReviewAdapter", "No ImageUri provided");
            // Handle the case where there is no image
            // You can set a default image or hide the ImageView
        }
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list, parent, false);
        return new ReviewViewHolder(view);
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView locationTextView;
        TextView dateTextView;
        TextView namaTextView;
        TextView reviewTextView;
        ImageView imageView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.tv_lokasi);
            dateTextView = itemView.findViewById(R.id.tv_date);
            namaTextView = itemView.findViewById(R.id.tv_nama);
            reviewTextView = itemView.findViewById(R.id.tv_review);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }
}