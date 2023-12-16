package com.example.finalprojectpapb4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class DetailReviewActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageView ivReviewImage;
    private ImageButton ibDownloadImage;
    private ImageView ivUserProfile;
    private TextView tvUsername;
    private TextView tvLocation;
    private TextView tvDate;
    private TextView tvReviewContent;
    private StorageReference imageRef;

    private Intent homeIntent;
    private String location;
    private String date;
    private String userName;
    private String review;
    private String imageUri;
    private String userId;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);
    
        homeIntent = getIntent();
        location = homeIntent.getStringExtra("location");
        date = homeIntent.getStringExtra("date");
        userId = homeIntent.getStringExtra("userId");
        userName = homeIntent.getStringExtra("userName");
        review = homeIntent.getStringExtra("review");
        imageUri = homeIntent.getStringExtra("imageUri");

        btnBack = findViewById(R.id.ib_back);
        ivReviewImage = findViewById(R.id.iv_review_photo);
        ibDownloadImage = findViewById(R.id.ib_download_image);
        ivUserProfile = findViewById(R.id.iv_user_profile_pic);
        tvUsername = findViewById(R.id.et_edit_username);
        tvLocation = findViewById(R.id.tv_location);
        tvDate = findViewById(R.id.tv_detail_review_date);
        tvReviewContent = findViewById(R.id.tv_review_content);

        tvUsername.setText(userName);
        tvLocation.setText(location);
        tvDate.setText(date);
        tvReviewContent.setText(review);
        Glide.with(this).load(imageUri).into(ivReviewImage);

        btnBack.setOnClickListener(_view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });
    }
}
