package com.example.finalprojectpapb4;

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
import java.util.HashMap;
import java.util.Map;

public class ReviewAdapter extends FirebaseRecyclerAdapter<ReviewModel, ReviewAdapter.ReviewViewHolder> {
    private IOnItemClickListener iOnItemClickListener;
    private Map<String, String> userIdToNameMap; // Map to store user names

    public ReviewAdapter(@NonNull FirebaseRecyclerOptions<ReviewModel> options) {
        super(options);
        userIdToNameMap = new HashMap<>();
    }

    @Override
    protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull ReviewModel model) {

        holder.locationTextView.setText(model.getLocation());
        holder.reviewTextView.setText(model.getReview());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        holder.dateTextView.setText(dateFormat.format(model.getDate()));
        holder.namaTextView.setText(userIdToNameMap.get(model.getUserId()));
        Glide.with(holder.itemView.getContext())
                .load(model.getImageUri())
                .into(holder.imageView);
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list, parent, false);

        final ReviewViewHolder viewHolder = new ReviewViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && iOnItemClickListener != null) {
                    iOnItemClickListener.onItemClick(position);
                }
            }
        });

        return viewHolder;
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

    public void setOnItemClickListener(IOnItemClickListener listener) {
        this.iOnItemClickListener = listener;
    }
    public void setUserIdToNameMap(Map<String, String> userIdToNameMap) {
        this.userIdToNameMap = userIdToNameMap;
    }

}