package com.example.finalprojectpapb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AddReviewActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageView reviewImageView;
    private Button uploadImageButton;
    private EditText locationEditText;
    private EditText reviewEditText;
    private Button postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        backButton = findViewById(R.id.icon_back);
        reviewImageView = findViewById(R.id.review_photo);
        uploadImageButton = findViewById(R.id.upload_img_button);
        locationEditText = findViewById(R.id.lokasi_inputfield);
        reviewEditText = findViewById(R.id.ulasan_inputfield);
        postButton = findViewById(R.id.btn_submit);

        backButton.setOnClickListener(_view -> {
            Intent intent = new Intent(AddReviewActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}