package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MyPlaylistActivity extends AppCompatActivity {
    DatabaseHelper db;
    List<Playlist> playlists = new ArrayList<>();
    List<Playlist> tempt;
    RecyclerView playlistRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlist);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        db = new DatabaseHelper(this);
        tempt = db.fetchAllPlaylist(username);
        for (int i = 0; i < tempt.size(); i++) {

            Playlist order = new Playlist(tempt.get(i).getUrl());
            playlists.add(order);
        }
        playlistRecycleView = findViewById(R.id.playlistRecycleView);
        PlaylistAdapter playlistAdapter = new PlaylistAdapter(MyPlaylistActivity.this, playlists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        playlistRecycleView.setLayoutManager(layoutManager);
        playlistRecycleView.setAdapter(playlistAdapter);
    }
}