package com.example.googleauthenticate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    public static final String TAG = "GoogleSignIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvUserName = findViewById(R.id.userName);
        TextView tvUserEmail = findViewById(R.id.userEmail);
        ImageView userImageView = findViewById(R.id.userImage);
        Button btnLogOut = findViewById(R.id.btnLogOut);

        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = preferences.getString("userName", "-");
        String userEmail = preferences.getString("userEmail", "-");
        String userImageUrl = preferences.getString("userPhoto", "-");

        tvUserName.setText(userName);
        tvUserEmail.setText(userEmail);
        Glide.with(this).load(userImageUrl).into(userImageView);

        btnLogOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        });
    }

}