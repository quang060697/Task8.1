package com.example.itubeapp;

public class Playlist {
    private int playlistid;
    private String username,url;

    public Playlist(String username, String url) {
        this.username = username;
        this.url = url;
    }

    public Playlist(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrderid() {
        return playlistid;
    }
}
