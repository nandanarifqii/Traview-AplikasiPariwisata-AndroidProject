package com.example.finalprojectpapb4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        btnBack = findViewById(R.id.ib_back);
        ivReviewImage = findViewById(R.id.iv_review_photo);
        ibDownloadImage = findViewById(R.id.ib_download_image);
        ivUserProfile = findViewById(R.id.iv_user_profile_pic);
        tvUsername = findViewById(R.id.tv_username);
        tvLocation = findViewById(R.id.tv_location);
        tvDate = findViewById(R.id.tv_detail_review_date);
        tvReviewContent = findViewById(R.id.tv_review_content);

        btnBack.setOnClickListener(_view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });
    }
}
