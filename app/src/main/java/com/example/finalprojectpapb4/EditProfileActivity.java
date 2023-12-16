package com.example.finalprojectpapb4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView ivImageProfile;
    private Button btnUploadProfile;
    private EditText etEditUsername;
    private EditText etEditName;
    private Button btnSave;
    private Button btnCancel;
    private Uri imageUri;
    private UserModel currentUser;
    private ActivityResultLauncher<Intent> reviewImageSetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ivImageProfile = findViewById(R.id.iv_image_profile);
        btnUploadProfile = findViewById(R.id.btn_upload_profile_image);
        etEditUsername = findViewById(R.id.et_edit_username);
        etEditName = findViewById(R.id.et_edit_name);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        DatabaseReference userReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("user_profiles")
                .child(FirebaseAuth
                        .getInstance()
                        .getCurrentUser()
                        .getUid());

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentUser = snapshot.getValue(UserModel.class);
                etEditName.setText(currentUser.getName());
                etEditUsername.setText(currentUser.getUsername());
                if (currentUser.getImageProfileUri() != null) {
                    Glide.with(getApplicationContext())
                            .load(currentUser.getImageProfileUri())
                            .into(ivImageProfile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(
                        getApplicationContext(),
                        "Error occurred: " + error.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        reviewImageSetter = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imageUri = data.getData();
                        ivImageProfile.setImageURI(imageUri);
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                "No Image Selected",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        btnUploadProfile.setOnClickListener(_view -> pickImage());
        btnSave.setOnClickListener(_view -> updateProfile(
                etEditUsername.getText().toString(),
                etEditName.getText().toString()
        ));
        btnCancel.setOnClickListener(_view -> backToProfile());
    }

    private void updateProfile(String username, String name) {
        if (!validateForm(username, name)) {
            return;
        }
        DatabaseReference reviewReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("user_profiles")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        final StorageReference imageReference = FirebaseStorage
                .getInstance()
                .getReference()
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("profile_image");

        imageReference
                .putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    imageUri = taskSnapshot.getUploadSessionUri();
                })
                .addOnFailureListener(_exception -> {
                    Toast.makeText(
                            getApplicationContext(),
                            "Failed upon uploading image",
                            Toast.LENGTH_SHORT
                    ).show();
                });

        UserModel updatedProfile = new UserModel(username, name);
        updatedProfile.setImageProfileUri(imageUri == null ?
                currentUser.getImageProfileUri() :
                imageUri.toString());

        reviewReference.setValue(updatedProfile, (error, ref) -> {
            if (error != null) {
                Toast.makeText(
                        getApplicationContext(),
                        "Failed to update profile because " + error.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Successfully update profile",
                        Toast.LENGTH_SHORT
                ).show();
                backToProfile();
            }
        });
    }

    public boolean validateForm(String username, String name) {
        if (username.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Username can not be empty",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        if (name.equals("")) {
            Toast.makeText(
                    getApplicationContext(),
                    "Name can not be empty",
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

        if (username.equals(currentUser.getUsername()) &&
                name.equals(currentUser.getName()) &&
                (imageUri.toString().equals(currentUser.getImageProfileUri()) || imageUri == null)) {
            return false;
        }

        return true;
    }

    private void pickImage() {
        Intent photoPicker = new Intent();
        photoPicker.setAction(Intent.ACTION_GET_CONTENT);
        photoPicker.setType("image/*");
        this.reviewImageSetter.launch(photoPicker);
    }

    private void backToProfile() {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }
}