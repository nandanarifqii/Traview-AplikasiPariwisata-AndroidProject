package com.example.finalprojectpapb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton button;
    ImageButton searchButton;
    BottomNavigationView navButton;

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

        Query query = FirebaseDatabase.getInstance().getReference().child("Reviews");
        FirebaseRecyclerOptions<ReviewModel> options =
                new FirebaseRecyclerOptions.Builder<ReviewModel>()
                        .setQuery(query, ReviewModel.class)
                        .build();

        ReviewAdapter adapter = new ReviewAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.RecyclerViewReview);
        recyclerView.setAdapter(adapter);

        adapter.startListening();


    }

}