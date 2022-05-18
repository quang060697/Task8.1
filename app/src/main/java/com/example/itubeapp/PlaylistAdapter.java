package com.example.itubeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>{
    private Context context;
    private List<Playlist> playlists;

    public PlaylistAdapter(Context context, List<Playlist> playlists) {
        this.context = context;
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public PlaylistAdapter.PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.playlist_row,parent,false);
        return new PlaylistAdapter.PlaylistViewHolder(itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.PlaylistViewHolder holder, int position) {
        holder.videoTextView.setText(playlists.get(position).getUrl());

    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {
        public TextView videoTextView;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTextView = itemView.findViewById(R.id.videoTextView);

        }
    }
}
