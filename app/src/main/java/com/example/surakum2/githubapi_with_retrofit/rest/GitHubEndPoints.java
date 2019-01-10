package com.example.surakum2.githubapi_with_retrofit.rest;

import com.example.surakum2.githubapi_with_retrofit.model.GitHubRepo;
import com.example.surakum2.githubapi_with_retrofit.model.GitHubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubEndPoints {

    @GET("/users/{user}")
    Call<GitHubUser> getUser(@Path("user") String User);

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String user);
}
