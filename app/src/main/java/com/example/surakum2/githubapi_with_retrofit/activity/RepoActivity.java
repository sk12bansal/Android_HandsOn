package com.example.surakum2.githubapi_with_retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.surakum2.githubapi_with_retrofit.R;
import com.example.surakum2.githubapi_with_retrofit.adapter.ReposAdapter;
import com.example.surakum2.githubapi_with_retrofit.model.GitHubRepo;
import com.example.surakum2.githubapi_with_retrofit.rest.APIClient;
import com.example.surakum2.githubapi_with_retrofit.rest.GitHubEndPoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoActivity extends AppCompatActivity {

    TextView repoOwner;
    RecyclerView mRecyclerView;
    List<GitHubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;
    Bundle extras;
    String username;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repositories_info);

        repoOwner = findViewById(R.id.repoOwner);

        extras = getIntent().getExtras();
        username =  extras.getString("USERNAME");
        repoOwner.setText(username);

        mRecyclerView = findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(myDataSource,R.layout.repo_list_item,getApplicationContext());
        mRecyclerView.setAdapter(myAdapter);
        loadRepositories();
    }

    public void loadRepositories(){

        GitHubEndPoints apiService = APIClient.getClient().create(GitHubEndPoints.class);
        Call<List<GitHubRepo>> call = apiService.getRepo(username);

        call.enqueue(new Callback<List<GitHubRepo>>(){

            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Repos", t.toString());
            }
        });

    }
}
