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
    private String userId;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef;

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

        storageRef = storage.getReferenceFromUrl(imageUri);

        tvUsername.setText(userName);
        tvLocation.setText(location);
        tvDate.setText(date);
        tvReviewContent.setText(review);
        Log.d("DetailReviewActivity", "Image URL: " + imageUri);

        if (imageUri != null) {
            Glide.with(this).load(imageUri).into(ivReviewImage);
            ibDownloadImage.setOnClickListener(view -> {
                downloadImage(imageUri);
            });
        } else {
            Toast.makeText(this, "Image URL is null", Toast.LENGTH_SHORT).show();

        }

        btnBack.setOnClickListener(_view -> finish());
    }

    private void downloadImage(String imageUri) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (isValidFirebaseStorageUri(imageUri)) {
                File directory = Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                // Generate a unique filename based on timestamp
                String timestamp = String.valueOf(System.currentTimeMillis());
                File localFile = new File(directory, "downloaded_image_" + timestamp + ".jpg");

                Log.d("DownloadImage", "Local file path: " + localFile.getAbsolutePath());

                storageRef.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(getApplicationContext(),
                            "Image Downloaded",
                            Toast.LENGTH_SHORT
                    ).show();
                }).addOnFailureListener(exception -> {
                    Toast.makeText(getApplicationContext(),
                            "Failed to download image: " + exception.getMessage(),
                            Toast.LENGTH_SHORT
                    ).show();
                    exception.printStackTrace(); // Log the exception details
                });
            } else {
                Toast.makeText(getApplicationContext(),
                        "Invalid Firebase Storage URL",
                        Toast.LENGTH_SHORT
                ).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "External storage not available",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private boolean isValidFirebaseStorageUri(String uri) {
        try {
            // Attempt to create a StorageReference, if successful, consider it a valid Firebase Storage URL
            FirebaseStorage.getInstance().getReferenceFromUrl(uri);
            return true;
        } catch (IllegalArgumentException e) {
            // If an exception is caught, it is not a valid Firebase Storage URL
            return false;
        }
    }

}
