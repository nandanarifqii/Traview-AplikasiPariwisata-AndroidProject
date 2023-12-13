package com.example.finalprojectpapb4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AddReviewActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageView ivReviewImage;
    private Button btnUploadImage;
    private EditText etLocation;
    private EditText etReview;
    private Button btnSubmit;

    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        btnBack = findViewById(R.id.ib_back);
        ivReviewImage = findViewById(R.id.iv_review_photo);
        btnUploadImage = findViewById(R.id.bt_upload_img);
        etLocation = findViewById(R.id.et_location);
        etReview = findViewById(R.id.et_review);
        btnSubmit = findViewById(R.id.btn_submit);

        btnBack.setOnClickListener(_view -> backToHome());
        btnUploadImage.setOnClickListener(_view -> uploadImage());
        btnSubmit.setOnClickListener(_view -> uploadReview(
                this.etLocation.getText().toString(),
                this.etReview.getText().toString()
        ));
    }

    private void uploadImage() {
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            imageUri = data.getData();
                            ivReviewImage.setImageURI(imageUri);
                        } else {
                            Toast.makeText(getApplicationContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        Intent photoPicker = new Intent();
        photoPicker.setAction(Intent.ACTION_GET_CONTENT);
        photoPicker.setType("image/*");
        activityResultLauncher.launch(photoPicker);
    }

    public void uploadReview(String location, String review) {
        if (!validateReview(location, review)) {
            return;
        }

        // TODO: finish upload logic
        // NOTE: need model

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    public boolean validateReview(String location, String review) {
        if (location.equals("")) {
            Toast.makeText(getApplicationContext(), "Location can not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (review.equals("")) {
            Toast.makeText(getApplicationContext(), "Review can not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imageUri == null) {
            Toast.makeText(getApplicationContext(), "Image can not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void backToHome() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}