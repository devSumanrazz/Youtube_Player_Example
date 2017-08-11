package com.example.root.youtubeexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by root on 8/11/17.
 */

public class PlayerActivity extends YouTubeBaseActivity {
    YouTubePlayerView playerView;
    String API_KEY="AIzaSyBE6MOf5lgU7wpMMCvmh9RLmHYxxS9Es68";
    String videoId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);

        playerView=findViewById(R.id.playerView);
        
        videoId=getIntent().getExtras().getString("videoID");


        playerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.loadVideo(videoId);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(PlayerActivity.this, "Failed to play the Video", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
