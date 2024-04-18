package com.myfirstweb.myfirstweb.dto;

public class GithubUser {
    private String name;
    private String id;

    public String getId() {
        return id;
    }

    private String bio;
    private String login;

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", bio='" + bio + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
