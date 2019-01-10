package com.example.surakum2.githubapi_with_retrofit.model;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String username;

    @SerializedName("followers")
    private String follower;

    @SerializedName("following")
    private String following;

    @SerializedName("avatar_url")
    private String avatar;

    @SerializedName("email")
    private String email;


    public GitHubUser(String login, String username, String follower, String following, String avatar, String email) {
        this.login = login;
        this.username = username;
        this.follower = follower;
        this.following = following;
        this.avatar = avatar;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
