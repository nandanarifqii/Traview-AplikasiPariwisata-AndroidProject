//package com.example.finalprojectpapb4;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import androidx.lifecycle.ViewModel;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//
//import java.text.SimpleDateFormat;
//public class ReviewAdapter extends FirebaseRecyclerAdapter<ReviewModel, ReviewAdapter.ReviewViewHolder> {
//
//    public ReviewAdapter(@NonNull FirebaseRecyclerOptions<ReviewModel> options) {
//        super(options);
//    }
//
//    // View holder, onCreateViewHolder(), and onBindViewHolder()
//
//    @Override
//    protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull ReviewModel model) {
//
//        // Format date
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//        String dateString = formatter.format(model.getDate());
//
//        holder.locationTextView.setText(model.getLocation());
//        holder.dateTextView.setText(dateString);
//        holder.nameTextView.setText(model.getUserId());
//        holder.reviewTextView.setText(model.getReview());
//
//        // Set image if available
//        if (model.getImageUri() != null) {
//            Glide.with(holder.itemView)
//                    .load(model.getImageUri())
//                    .into(holder.imageView);
//        }
//    }
//
//
//    @NonNull
//    @Override
//    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.review_list, parent, false);
//
//        return new ReviewViewHolder(view);
//    }
//
//    // View holder
//    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
//
//        TextView locationTextView, dateTextView, nameTextView, reviewTextView;
//        ImageView imageView;
//
//        public ReviewViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            // Find views
//            locationTextView = itemView.findViewById(R.id.tv_lokasi);
//            dateTextView = itemView.findViewById(R.id.tv_date);
//            nameTextView = itemView.findViewById(R.id.tv_nama);
//            reviewTextView = itemView.findViewById(R.id.tv_review);
//            imageView = itemView.findViewById(R.id.iv_image);
//        }
//    }
//}
//
