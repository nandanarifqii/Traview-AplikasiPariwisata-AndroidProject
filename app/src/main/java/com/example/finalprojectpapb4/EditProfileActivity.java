package com.example.finalprojectpapb4;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class EditProfileActivity extends AppCompatActivity {
    ImageView imgProfil;
    Uri imagePath;
    Button btnGanti;
    Button btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
//        Intent intent = getIntent();
        imgProfil = findViewById(R.id.profil);
        btnGanti = findViewById(R.id.ganti);
        btnGanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                uploadImage();
            }
        });
        btnKeluar = findViewById(R.id.btn_keluar);
        btnKeluar.setOnClickListener(_view -> {
            Intent intent1 = new Intent(EditProfileActivity.this,ProfileActivity.class);
            startActivity(intent1);
        });
        imgProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent,1 );


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imagePath = data.getData(); // Inisialisasi imagePath dengan alamat gambar yang dipilih
            getImageInImageView();
        }
    }

    private void getImageInImageView() {
        if (imagePath != null) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                imgProfil.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    private void uploadImage() {
//        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
//        builder.setTitle("Uploading...");
//        builder.setCancelable(false);
//
//        final androidx.appcompat.app.AlertDialog progressDialog = builder.create();
//        progressDialog.show();
//
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference("image/" + UUID.randomUUID().toString());
//
//        storageReference.putFile(imagePath)
//                .addOnProgressListener(taskSnapshot -> {
//                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                    progressDialog.setMessage("Uploaded " + ((int) progress) + "%");
//                })
//                .addOnCompleteListener(task -> {
//                    progressDialog.dismiss();
//                    Toast.makeText(EditActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
//
//                    if (task.isSuccessful()) {
//                        // Dapatkan URL gambar setelah berhasil diunggah
//                        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
//                            String imageUrl = uri.toString();
//                            // Lanjutkan dengan menggunakan URL gambar (imageUrl) sesuai kebutuhan Anda
//                        });
//                    } else {
//                        Toast.makeText(EditActivity.this, "Upload failed: " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//}
}