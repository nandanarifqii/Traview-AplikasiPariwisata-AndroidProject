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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivProfileImage = findViewById(R.id.iv_image_profile);
        tvName = findViewById(R.id.tv_name);
        tvUsername = findViewById(R.id.tv_profil_username);
        btnEdit = findViewById(R.id.btn_edit);
        menuNav = findViewById(R.id.menu_nav);
        btnSignOut = findViewById(R.id.btn_sign_out);

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
                UserModel currentUser = snapshot.getValue(UserModel.class);
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

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(_view ->

        {
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