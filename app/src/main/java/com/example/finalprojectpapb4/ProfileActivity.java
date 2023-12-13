package com.example.finalprojectpapb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    Button btnEdit;
    Button btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,EditProfileActivity.class);
                startActivity(intent);

            }
        });

        btnKeluar = findViewById(R.id.btn_keluar);
        btnKeluar.setOnClickListener(_view -> {
                Intent intent = new Intent(ProfileActivity.this,RegisterActivity.class);
                startActivity(intent);
        });
    }
}