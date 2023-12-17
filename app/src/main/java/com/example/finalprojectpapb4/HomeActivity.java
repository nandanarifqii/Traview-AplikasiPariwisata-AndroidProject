package com.example.finalprojectpapb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements IOnItemClickListener {
    FloatingActionButton btnAddReview;
    ImageButton btnSearch;
    BottomNavigationView menuNav;
    private DatabaseReference databaseReference;
    private DatabaseReference userReference;
    private ReviewAdapter reviewAdapter;
    private Map<String, String> userIdToNameMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAddReview = findViewById(R.id.button_add);
        btnSearch = findViewById(R.id.search_button);
        menuNav = findViewById(R.id.menu_nav);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewReview);

        btnAddReview.setOnClickListener(_view -> {
            Intent intent = new Intent(getApplicationContext(), AddReviewActivity.class);
            startActivity(intent);
        });

        btnSearch.setOnClickListener(_view -> {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        });

        menuNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.beranda) {
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(profileIntent);
                    return true;
                }

                return false;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Review item
        databaseReference = FirebaseDatabase.getInstance().getReference().child("reviews");

        userReference = FirebaseDatabase.getInstance().getReference().child("user_profiles");
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 userIdToNameMap = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String userId = snapshot.getKey();
                    String userName = snapshot.child("name").getValue(String.class);
                    userIdToNameMap.put(userId, userName);
                }
                reviewAdapter.setUserIdToNameMap(userIdToNameMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });


        FirebaseRecyclerOptions<ReviewModel> options =
                new FirebaseRecyclerOptions.Builder<ReviewModel>()
                        .setQuery(databaseReference, ReviewModel.class)
                        .build();

        reviewAdapter = new ReviewAdapter(options);
        recyclerView.setAdapter(reviewAdapter);
        reviewAdapter.setOnItemClickListener(this);


    }

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
        String userId = clickedItem.getUserId();
        String userName = userIdToNameMap.get(userId);
        Intent intent = new Intent(HomeActivity.this, DetailReviewActivity.class);
        intent.putExtra("location", clickedItem.getLocation());
        intent.putExtra("date", clickedItem.getDate());
        intent.putExtra("userId", clickedItem.getUserId());
        intent.putExtra("userName", userName);
        intent.putExtra("review", clickedItem.getReview());
        intent.putExtra("imageUri", clickedItem.getImageUri());
        startActivity(intent);
    }
}