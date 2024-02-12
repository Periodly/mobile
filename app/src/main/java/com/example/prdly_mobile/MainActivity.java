package com.example.prdly_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("loginState", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // User is logged in, redirect to the next activity
        }


            Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener((view -> {
            login();
        }));

    }

    private void login() {
        EditText usernameEditText = findViewById(R.id.login);
        EditText passwordEditText = findViewById(R.id.password);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.equals("admin") && password.equals("admin")) {
            // set persistent state to true
            SharedPreferences sharedPreferences = getSharedPreferences("loginState", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", true);
            editor.apply();

            redirect();

        } else {
            TextView errorTextView = findViewById(R.id.loginError);
            errorTextView.setText("Invalid username or password");
        }
    }

    private void redirect() {
        Intent intent = new Intent(MainActivity.this, AppHomeScreen.class);
        startActivity(intent);
        finish();
    }
}