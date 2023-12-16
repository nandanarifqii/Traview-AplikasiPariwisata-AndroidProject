package com.example.finalprojectpapb4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;

public class AddReviewActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private ImageView ivReviewImage;
    private Button btnPickImage;
    private EditText etLocation;
    private EditText etReviewContent;
    private Button btnSubmit;

    private Uri imageUri;

    private ActivityResultLauncher<Intent> reviewImageSetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        btnBack = findViewById(R.id.ib_back);
        ivReviewImage = findViewById(R.id.iv_review_photo);
        btnPickImage = findViewById(R.id.bt_upload_img);
        etLocation = findViewById(R.id.et_location);
        etReviewContent = findViewById(R.id.et_review);
        btnSubmit = findViewById(R.id.btn_submit);

        reviewImageSetter = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imageUri = data.getData();
                        ivReviewImage.setImageURI(imageUri);
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                "No Image Selected",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        btnBack.setOnClickListener(_view -> finish());
        btnPickImage.setOnClickListener(_view -> pickImage());
        btnSubmit.setOnClickListener(_view -> uploadReview(
                this.etLocation.getText().toString(),
                this.etReviewContent.getText().toString()
        ));
    }

    private void pickImage() {
        Intent photoPicker = new Intent();
        photoPicker.setAction(Intent.ACTION_GET_CONTENT);
        photoPicker.setType("image/*");
        this.reviewImageSetter.launch(photoPicker);
    }

    public void uploadReview(String location, String reviewContent) {
        if (!validateReview(location, reviewContent)) {
            return;
        }

        DatabaseReference reviewReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("reviews");

        String key = reviewReference.push().getKey();

        final StorageReference imageReference = FirebaseStorage
                .getInstance()
                .getReference()
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(key);

        imageReference
                .putFile(imageUri)
                .addOnSuccessListener(_taskSnapshot -> {
                    imageReference
                            .getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                ReviewModel review = new ReviewModel(
                                        location,
                                        new Date(),
                                        FirebaseAuth
                                                .getInstance()
                                                .getCurrentUser()
                                                .getUid()
                                                .toString(),
                                        reviewContent,
                                        uri.toString()
                                );

                                reviewReference.child(key).setValue(review, (error, ref) -> {
                                    if (error != null) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "Failed to upload review because " + error.getMessage(),
                                                Toast.LENGTH_SHORT
                                        ).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "Successfully upload review",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                        finish();
                                    }
                                });
                            });
                })
                .addOnFailureListener(_exception -> {
                    Toast.makeText(
                            getApplicationContext(),
                            "Failed upon uploading image",
                            Toast.LENGTH_SHORT
                    ).show();
                });
    }

    public boolean validateReview(String location, String review) {
        if (location.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Location can not be empty",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        if (review.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Review can not be empty",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        if (imageUri == null) {
            Toast.makeText(
                    getApplicationContext(),
                    "Image can not be empty",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        return true;
    }
}