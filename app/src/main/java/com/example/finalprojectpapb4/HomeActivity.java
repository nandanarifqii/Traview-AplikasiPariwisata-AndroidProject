package com.example.finalprojectpapb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton button;
    ImageButton searchButton;

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
    }
}