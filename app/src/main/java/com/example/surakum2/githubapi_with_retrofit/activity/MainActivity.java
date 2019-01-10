package com.example.surakum2.githubapi_with_retrofit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.surakum2.githubapi_with_retrofit.R;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button login;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        login = (Button) findViewById(R.id.loginButton);
    }

    public void getUser(View view) {
        i = new Intent(MainActivity.this, UserActivity.class);
        i.putExtra("USERNAME", username.getText().toString());
        startActivity(i);

    }
}
