package com.example.finalprojectpapb4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailReviewActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageView ivReviewImage;
    private ImageButton ibDownloadImage;
    private ImageView ivUserProfile;
    private TextView tvUsername;
    private TextView tvLocation;
    private TextView tvDate;
    private TextView tvReviewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        Intent homeIntent = getIntent();
        String location = homeIntent.getStringExtra("location");
        String date = homeIntent.getStringExtra("date");
        String userId = homeIntent.getStringExtra("userId");
        String userName = homeIntent.getStringExtra("userName");
        String review = homeIntent.getStringExtra("review");
        String imageUri = homeIntent.getStringExtra("imageUri");

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
//            Intent profileIntent = new Intent(DetailReviewActivity.this, HomeActivity.class);
//            startActivity(profileIntent);
            finish();
        });
    }
}
