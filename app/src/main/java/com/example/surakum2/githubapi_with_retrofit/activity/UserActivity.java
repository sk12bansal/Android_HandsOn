package com.example.surakum2.githubapi_with_retrofit.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.surakum2.githubapi_with_retrofit.R;
import com.example.surakum2.githubapi_with_retrofit.model.GitHubUser;
import com.example.surakum2.githubapi_with_retrofit.rest.APIClient;
import com.example.surakum2.githubapi_with_retrofit.rest.GitHubEndPoints;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    ImageView avatarImg;
    TextView userName;
    TextView login;
    TextView follower;
    TextView following;
    TextView email;
    Button ownRepos;

    Bundle extras;
    String owner;

    Bitmap userImage;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        avatarImg = findViewById(R.id.avatar);
        userName = findViewById(R.id.username);
        login = findViewById(R.id.login);
        follower = findViewById(R.id.follower);
        following = findViewById(R.id.following);
        email = findViewById(R.id.email);
        ownRepos = findViewById(R.id.repo);

        extras = getIntent().getExtras();
        owner = extras.getString("USERNAME");

        System.out.println(owner);

        loadUserData();

    }

    public void loadOwnRepos(View view) {
        i = new Intent(UserActivity.this, RepoActivity.class);
        i.putExtra("USERNAME", owner);
        startActivity(i);
    }


    public static class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void loadUserData() {

        final GitHubEndPoints apiService = APIClient.getClient().create(GitHubEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(owner);

        call.enqueue(new Callback<GitHubUser>() {

            @Override
            public void onResponse(retrofit2.Call<GitHubUser> call, Response<GitHubUser> response) {

                if (response.body() != null) {
                    ImageDownloader task = new ImageDownloader();

                    try {
                        userImage = task.execute(response.body().getAvatar()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    avatarImg.setImageBitmap(userImage);
                    avatarImg.getLayoutParams().height = 220;
                    avatarImg.getLayoutParams().width = 220;

                    if (response.body().getUsername() == null) {
                        userName.setText("No name provided");
                    } else {
                        userName.setText("Username: " + response.body().getUsername());
                    }

                    follower.setText("Followers: " + response.body().getFollower());
                    following.setText("Following: " + response.body().getFollowing());
                    login.setText("LogIn: " + response.body().getLogin());

                    if (response.body().getEmail() == null) {
                        email.setText("No email provided");
                    } else {
                        email.setText("Email: " + response.body().getEmail());
                    }

                }
            }

            @Override
            public void onFailure(retrofit2.Call<GitHubUser> call, Throwable t) {
                System.out.println("Failed!" + t.toString());
            }
        });

    }
}
