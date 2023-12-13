package com.example.finalprojectpapb4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailReviewActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageView reviewImageView;
    private ImageButton downloadImageButton;
    private ImageView userProfileImageView;
    private TextView usernameTextView;
    private TextView locationTextView;
    private TextView dateTextView;
    private TextView userReviewTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        backButton = findViewById(R.id.ib_back);
        reviewImageView = findViewById(R.id.iv_review_photo);
        downloadImageButton = findViewById(R.id.download_fab);
        userProfileImageView = findViewById(R.id.user_profile_pic);
        usernameTextView = findViewById(R.id.username);
        locationTextView = findViewById(R.id.location);
        dateTextView = findViewById(R.id.date);
        userReviewTextView = findViewById(R.id.user_review);

        backButton.setOnClickListener(_view -> {
            Intent intent = new Intent(DetailReviewActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
