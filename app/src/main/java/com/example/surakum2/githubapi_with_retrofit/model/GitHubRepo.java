package com.example.surakum2.githubapi_with_retrofit.model;

import com.google.gson.annotations.SerializedName;

public class GitHubRepo {

    @SerializedName("name")
    private String name;

    @SerializedName("language")
    private String language;

    @SerializedName("description")
    private String description;

    public GitHubRepo(String name, String language, String description) {
        this.name = name;
        this.language = language;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
