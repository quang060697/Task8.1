package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayerView;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper db;
    Playlist playlist;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        EditText urlInput = findViewById(R.id.urlInput);
        Button playButton = findViewById(R.id.playButton);
        Button addToPlaylistButton = findViewById(R.id.addToPlaylistButton);
        Button myPlaylistButton = findViewById(R.id.myPlaylistButton);
        db = new DatabaseHelper(this);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = urlInput.getText().toString();
                Intent intent1 = new Intent(getApplicationContext(),YouTubeVideoActivity.class);
                intent1.putExtra("url",url);
                startActivity(intent1);
            }
        });
        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playlist = new Playlist(username,urlInput.getText().toString());
                db.insertToPlaylist(playlist);
                Toast.makeText(HomeActivity.this, "Add to playlist successfully", Toast.LENGTH_SHORT).show();

            }
        });
        myPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),MyPlaylistActivity.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
            }
        });
    }
}