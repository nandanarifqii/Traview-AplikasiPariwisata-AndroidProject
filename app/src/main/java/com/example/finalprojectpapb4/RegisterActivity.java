package com.example.finalprojectpapb4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        // Inisialisasi Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // Ambil dari google-services.json
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Set onClickListener untuk tombol daftar
        Button registerButton = findViewById(R.id.btn_login);
        registerButton.setOnClickListener(_view -> registerUser());

        // Set onClickListener untuk tombol Google Sign-In
        Button googleSignInButton = findViewById(R.id.btn_google);
        googleSignInButton.setOnClickListener(_view -> signInWithGoogle());

        // Set onClickListener untuk teks "Masuk"
        TextView masukTextView = findViewById(R.id.subtitle3);
        masukTextView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        TextInputEditText emailEditText = findViewById(R.id.et_email);
        TextInputEditText passwordEditText = findViewById(R.id.et_password);
        TextInputEditText confirmPasswordEditText = findViewById(R.id.et_password2);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (!validateForm(password, confirmPassword)) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        createUsernameAndName();
                        finish();
                    } else {
                        // Registrasi gagal, tampilkan pesan kesalahan
                        Toast.makeText(getApplicationContext(),
                                "Registrasi gagal: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }

    private boolean validateForm(String password, String confirmPassword) {
        // Implementasikan validasi formulir Anda di sini

        if (password.isEmpty()) {
            // Handle empty password
            Toast.makeText(
                    getApplicationContext(),
                    "Password cannot be empty",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        if (password.length() < 8) {
            // Handle password tidak valid
            Toast.makeText(getApplicationContext(),
                    "Password minimum 8 character",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            // Handle konfirmasi password tidak sesuai
            Toast.makeText(getApplicationContext(),
                    "Password confirmation does not match",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        return true;
    }


    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In berhasil, autentikasi ke Firebase
                GoogleSignInAccount account = (GoogleSignInAccount) ((Task<?>) task)
                        .getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In gagal, tampilkan pesan kesalahan
                Toast.makeText(getApplicationContext(),
                        String.format(
                                "Google Sign In failed: %s",
                                GoogleSignInStatusCodes.getStatusCodeString(e.getStatusCode())),
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Autentikasi berhasil, alihkan atau lakukan operasi lainnya
                        FirebaseUser user = mAuth.getCurrentUser();
                        createUsernameAndName();
                        Toast.makeText(getApplicationContext(),
                                "Authentication successful.",
                                Toast.LENGTH_SHORT
                        ).show();
                        finish();
                    } else {
                        // Autentikasi gagal, tampilkan pesan kesalahan
                        Toast.makeText(getApplicationContext(),
                                "Authentication failed: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }

    private void createUsernameAndName() {
        DatabaseReference userReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("user_profiles");

        String defaultValue = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        defaultValue = defaultValue.substring(0, defaultValue.indexOf("@"));

        userReference
                .child(FirebaseAuth
                        .getInstance()
                        .getCurrentUser()
                        .getUid()
                ).setValue(new UserModel(defaultValue, defaultValue));
    }
}