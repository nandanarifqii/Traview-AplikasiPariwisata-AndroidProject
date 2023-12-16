package com.example.finalprojectpapb4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {
    Button btnEdit;
    Button btnBack;

    BottomNavigationView menuNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(intent);

            }
        });

        btnBack = findViewById(R.id.btn_keluar);
        btnBack.setOnClickListener(_view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });


        menuNav = findViewById(R.id.bottom_navigation);

        menuNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.beranda) {
                    Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(homeIntent);
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
//                Intent profileIntent = new Intent(ProfileActivity.this, ProfileActivity.class);
//                startActivity(profileIntent);
                    return true;
                }
                return false;
            }
        });


    }

}