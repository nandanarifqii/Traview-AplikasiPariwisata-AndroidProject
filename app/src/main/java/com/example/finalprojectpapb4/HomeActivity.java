package com.example.finalprojectpapb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class HomeActivity extends AppCompatActivity implements IOnItemClickListener {
    FloatingActionButton button;
    ImageButton searchButton;
    BottomNavigationView navButton;
    private DatabaseReference databaseReference;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button = findViewById(R.id.button_add);
        button.setOnClickListener(_view -> {
            Intent intent = new Intent(HomeActivity.this, AddReviewActivity.class);
            startActivity(intent);
        });

        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(_view -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });


        navButton = findViewById(R.id.bottom_navigation);

        navButton.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.beranda) {
//                    Intent homeIntent = new Intent(HomeActivity.this, HomeActivity.class);
//                    startActivity(homeIntent);
                    return true;

                } else if (item.getItemId() == R.id.menu_profile) {
                    Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                    return true;

                }
                return false;
                }


        }
        );


        RecyclerView recyclerView = findViewById(R.id.recyclerViewReview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance("https://final-project-papb-4-f076f-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("reviews");

        databaseReference.child("dummy125").setValue(new ReviewModel("Jakarta", new Date(), "Bambang", "Review dummy ea"));
        databaseReference.child("dummy126").setValue(new ReviewModel("Surabaya", new Date(), "Jaya Wijaya", "Review dummy ea"));


        FirebaseRecyclerOptions<ReviewModel> options =
                new FirebaseRecyclerOptions.Builder<ReviewModel>()
                        .setQuery(databaseReference, ReviewModel.class)
                        .build();

        reviewAdapter = new ReviewAdapter(options);
        recyclerView.setAdapter(reviewAdapter);
        reviewAdapter.setiOnItemClickListener(this);

//        List<ReviewModel> dummyReviewList = getDummyReviewList();
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerViewReview);
//        ReviewAdaptercopy reviewAdapter = new ReviewAdaptercopy(this, dummyReviewList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(reviewAdapter);

//This is to test the object stored inside reviews
//        databaseReference.child("reviews").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    // Print the raw data of the "reviews" node
//                    String rawData = dataSnapshot.getValue(String.class);
//                    Log.d("HomeActivity", "Raw Data from reviews node: " + rawData);
//
//                    // Alternatively, you can iterate through child nodes
//                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                        String childKey = childSnapshot.getKey();
//                        String childValue = childSnapshot.getValue(String.class);
//                        Log.d("HomeActivity", "Child Key: " + childKey + ", Child Value: " + childValue);
//                    }
//                } else {
//                    // The "reviews" node is empty
//                    Log.d("HomeActivity", "No reviews found in the database");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle errors
//                Log.e("HomeActivity", "Error checking for reviews: " + databaseError.getMessage());
//            }
//        });




    }

//    private List<ReviewModel> getDummyReviewList() {
//        List<ReviewModel> dummyList = new ArrayList<>();
//
//        dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
//        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));
//        dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
//        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));
//        dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
//        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));
//        dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
//        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));
//        dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
//        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));
//        dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
//        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));
//        dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
//        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));    dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
//        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));
//
//
//        // Add more dummy data as needed
//
//        return dummyList;
//    }


    @Override
    protected void onStart() {
        super.onStart();
        reviewAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        reviewAdapter.stopListening();
    }


    @Override
    public void onItemClick(int position) {
        ReviewModel clickedItem = reviewAdapter.getItem(position);
        Intent intent = new Intent(HomeActivity.this, DetailReviewActivity.class);
        intent.putExtra("location", clickedItem.getLocation());
        intent.putExtra("date", clickedItem.getDate());
        intent.putExtra("userId", clickedItem.getUserId());
        intent.putExtra("review", clickedItem.getReview());
        startActivity(intent);
    }
}