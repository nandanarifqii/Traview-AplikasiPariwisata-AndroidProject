package com.example.finalprojectpapb4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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

        backButton = findViewById(R.id.icon_back);
        reviewImageView = findViewById(R.id.review_photo);
        downloadImageButton = findViewById(R.id.download_fab);
        userProfileImageView = findViewById(R.id.user_profile_pic);
        usernameTextView = findViewById(R.id.username);
        locationTextView = findViewById(R.id.location);
        dateTextView = findViewById(R.id.date);
        userReviewTextView = findViewById(R.id.user_review);
    }
}
