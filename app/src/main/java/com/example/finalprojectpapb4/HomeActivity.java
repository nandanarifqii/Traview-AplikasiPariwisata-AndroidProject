package com.example.finalprojectpapb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton button;
    ImageButton searchButton;
    BottomNavigationView navButton;
    private RecyclerView rv;

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


        // Get reference to RecyclerView
//        rv = findViewById(R.id.recyclerViewReview);
//        rv.setLayoutManager(new LinearLayoutManager(this));
//        ReviewAdapter adapter = new ReviewAdapter(getOptions());
//        rv.setAdapter(adapter);
//        List<ReviewModel> dummyReviewList = getDummyReviewList();
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerViewReview);
//        ReviewAdaptercopy reviewAdapter = new ReviewAdaptercopy(this, dummyReviewList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(reviewAdapter);


    }

    private List<ReviewModel> getDummyReviewList() {
        List<ReviewModel> dummyList = new ArrayList<>();

        dummyList.add(new ReviewModel("Bromo, Malang", new Date(),"Shani Indira", "Pemandangannya keren, tapi..."));
        dummyList.add(new ReviewModel("Another Location", new Date(), "John Doe", "Lorem ipsum dolor sit amet."));
        // Add more dummy data as needed

        return dummyList;
    }


//    private FirebaseRecyclerOptions<ReviewModel> getOptions() {
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("reviews");
//
//        return new FirebaseRecyclerOptions.Builder<ReviewModel>()
//                .setQuery(ref, ReviewModel.class)
//                .build();
//    }



}