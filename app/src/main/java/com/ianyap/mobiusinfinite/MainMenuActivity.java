package com.ianyap.mobiusinfinite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ianyap.mobiusinfinite.databinding.ActivityMainMenuBinding;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainMenuBinding activityMainMenu = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(activityMainMenu.getRoot());

        Button startButton = activityMainMenu.startButton;
        Intent startGame = new Intent(this, GameActivity.class);
        startButton.setOnClickListener(view -> this.startActivity(startGame));
    }
}