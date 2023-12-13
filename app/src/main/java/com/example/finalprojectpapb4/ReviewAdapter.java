package com.example.finalprojectpapb4;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

//public class ReviewAdapter extends AppCompatActivity {
//    RecyclerView rv;
//protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_home);
//
//    Query query = FirebaseDatabase.getInstance()
//            .getReference()
//            .child("posts")
//            .limitToLast(50);
//    FirebaseRecyclerOptions<ReviewModel> options =
//            new FirebaseRecyclerOptions.Builder<ReviewModel>()
//                    .setQuery(query, ReviewModel.class)
//                    .build();
//
//
//
//
//
//    FirebaseRecyclerAdapter<ReviewModel, ReviewViewHolder> adapter = new FirebaseRecyclerAdapter<ReviewModel, ReviewViewHolder>(options) {
//        @Override
//        protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull ReviewModel model) {
//            Log.d("LIST", "Review " + position);
//            holder.setReview(model);
//        }
//
//        @NonNull
//        @Override
//        public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            Log.d("LIST", "onCreateViewHolder");
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list, parent, false);
//            return new ReviewViewHolder(view);
//        }
//    };
//adapter.startListening();
//rv.setLayoutManager(new
//
//    LinearLayoutManager(this));
//rv.setAdapter(adapter);
//}
//
//   public static class ReviewViewHolder extends RecyclerView.ViewHolder {
//        TextView locationTextView, dateTextView, nameTextView, reviewTextView;
//
//        public ReviewViewHolder(@NonNull View itemView) {
//            super(itemView);
//            locationTextView = itemView.findViewById(R.id.tv_lokasi);
//            dateTextView = itemView.findViewById(R.id.tv_date);
//            nameTextView = itemView.findViewById(R.id.tv_nama);
//            reviewTextView = itemView.findViewById(R.id.tv_review);
//        }
//        public void setReview(ReviewModel model) {
//            locationTextView = itemView.findViewById(R.id.tv_lokasi);
//            dateTextView = itemView.findViewById(R.id.tv_date);
//            nameTextView = itemView.findViewById(R.id.tv_nama);
//            reviewTextView = itemView.findViewById(R.id.tv_review);
//
//            locationTextView.setText(model.getLocation());
//            dateTextView.setText(model.getDate());
//            nameTextView.setText(model.getName());
//            reviewTextView.setText(model.getReview());
//        }
//    }
//    //
////
////    public ReviewAdapter(@NonNull FirebaseRecyclerOptions<ReviewModel> options) {
////        super(options);
////    }
////
////    @NonNull
////    @Override
////    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list, parent, false);
////        return new ReviewViewHolder(view);
////    }
////
////    @Override
////    protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull ReviewModel model) {
////        // Set data to views
////        holder.locationTextView.setText(model.getLocation());
////        holder.dateTextView.setText(model.getDate());
////        holder.nameTextView.setText(model.getName());
////        holder.reviewTextView.setText(model.getReview());
////    }
////
////    static class ReviewViewHolder extends RecyclerView.ViewHolder {
////        TextView locationTextView, dateTextView, nameTextView, reviewTextView;
////
////        public ReviewViewHolder(@NonNull View itemView) {
////            super(itemView);
////            locationTextView = itemView.findViewById(R.id.tv_lokasi);
////            dateTextView = itemView.findViewById(R.id.tv_date);
////            nameTextView = itemView.findViewById(R.id.tv_nama);
////            reviewTextView = itemView.findViewById(R.id.tv_review);
////        }
////    }
//}













public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private FirebaseRecyclerAdapter<ReviewModel, ReviewViewHolder> adapter;

    public ReviewAdapter() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("posts")
                .limitToLast(50);
        FirebaseRecyclerOptions<ReviewModel> options =
                new FirebaseRecyclerOptions.Builder<ReviewModel>()
                        .setQuery(query, ReviewModel.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<ReviewModel, ReviewViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull ReviewModel model) {
                Log.d("LIST", "Review " + position);
                holder.setReview(model);
            }

            @NonNull
            @Override
            public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Log.d("LIST", "onCreateViewHolder");
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list, parent, false);
                return new ReviewViewHolder(view);
            }
        };
        adapter.startListening();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Implement this method if you have a header or footer
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        // Implement this method if you have a header or footer
    }

    @Override
    public int getItemCount() {
        return 0; // Implement this method if you have a header or footer
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView locationTextView, dateTextView, nameTextView, reviewTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.tv_lokasi);
            dateTextView = itemView.findViewById(R.id.tv_date);
            nameTextView = itemView.findViewById(R.id.tv_nama);
            reviewTextView = itemView.findViewById(R.id.tv_review);
        }

        public void setReview(ReviewModel model) {
            locationTextView.setText(model.getLocation());
            dateTextView.setText(model.getDate());
            nameTextView.setText(model.getName());
            reviewTextView.setText(model.getReview());
        }
    }
}
