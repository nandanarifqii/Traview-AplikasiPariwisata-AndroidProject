package com.example.finalprojectpapb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth auth;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }

        // Inisialisasi Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // Ambil dari google-services.json
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Set onClickListener untuk tombol login
        Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(_view -> loginUser());

        // Set onClickListener untuk tombol Google Sign-In
        Button googleSignInButton = findViewById(R.id.btn_google);
        googleSignInButton.setOnClickListener(_view -> signInWithGoogle());

        // Set onClickListener untuk teks "Daftar"
        TextView daftarTextView = findViewById(R.id.subtitle3);
        daftarTextView.setOnClickListener(_view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        TextInputEditText emailEditText = findViewById(R.id.et_email);
        TextInputEditText passwordEditText = findViewById(R.id.et_password);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!validateForm(email, password)) {
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Login Successful",
                                Toast.LENGTH_SHORT
                        ).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Login Failed: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }

    private boolean validateForm(String email, String password) {
        // Implement your form validation logic here
        // For example, you can check if the email is valid and if the password meets certain criteria
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Handle invalid email
            Toast.makeText(getApplicationContext(),
                    "Invalid Email Address",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        if (password.isEmpty()) {
            // Handle empty password
            Toast.makeText(getApplicationContext(),
                    "Password cannot be empty",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        if (password.length() < 8) {
            // Handle password tidak valid
            Toast.makeText(
                    getApplicationContext(),
                    "Password minimum 8 character",
                    Toast.LENGTH_SHORT
            ).show();
            return false;
        }

        return true;
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In berhasil, autentikasi ke Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In gagal, tampilkan pesan kesalahan
                Toast.makeText(getApplicationContext(),
                        String.format("Google Sign In failed: %s",
                                GoogleSignInStatusCodes.getStatusCodeString(e.getStatusCode())
                        ),
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(getApplicationContext(),
                                "Authentication successful.",
                                Toast.LENGTH_SHORT
                        ).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        // Redirect to your desired activity or do other operations
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Authentication failed: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}
