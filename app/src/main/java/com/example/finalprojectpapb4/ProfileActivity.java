package com.example.finalprojectpapb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    ImageView ivProfileImage;
    TextView tvName;
    TextView tvUsername;
    Button btnEdit;
    Button btnSignOut;
    BottomNavigationView menuNav;

    UserModel currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivProfileImage = findViewById(R.id.iv_image_profile);
        tvName = findViewById(R.id.tv_name);
        tvUsername = findViewById(R.id.tv_profil_username);
        btnEdit = findViewById(R.id.btn_save);
        menuNav = findViewById(R.id.menu_nav);
        btnSignOut = findViewById(R.id.btn_cancel);

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
                tvName.setText(currentUser.getName());
                tvUsername.setText(currentUser.getUsername());
                if (currentUser.getImageProfileUri() != null) {
                    Glide.with(getApplicationContext())
                            .load(currentUser.getImageProfileUri())
                            .into(ivProfileImage);
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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("name", currentUser.getName());
                intent.putExtra("username", currentUser.getUsername());
                intent.putExtra("imageUri", currentUser.getImageProfileUri() == null ?
                        "" :
                        currentUser.getImageProfileUri());
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(_view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });

        menuNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.beranda) {
                    Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(homeIntent);
                    return true;
                }
                return false;
            }
        });
    }
}