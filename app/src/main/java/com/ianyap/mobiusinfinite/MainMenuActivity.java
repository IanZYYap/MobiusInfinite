package com.ianyap.mobiusinfinite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import com.ianyap.mobiusinfinite.databinding.ActivityMainMenuBinding;

public class MainMenuActivity extends AppCompatActivity {
    MediaPlayer mainMenuPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        // Using view binding for cleaner access to views
        ActivityMainMenuBinding activityMainMenu = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(activityMainMenu.getRoot());

        // Basic implementation of a main menu music player
        // TODO: Make the music loop and consider screen rotation
        mainMenuPlayer = MediaPlayer.create(this, R.raw.mainmenu);

        // Initialise the start button
        Button startButton = activityMainMenu.startButton;
        Intent startGame = new Intent(this, GameActivity.class);
        startButton.setOnClickListener(view -> this.startActivity(startGame));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainMenuPlayer.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mainMenuPlayer.start();
    }
}