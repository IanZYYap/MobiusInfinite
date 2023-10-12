package com.ianyap.mobiusinfinite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class GameActivity extends AppCompatActivity {
    private final String TAG = "GameActivity";
    private boolean mPaused;
    private GameView mGameView;
    private Thread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mPaused = false;
        setContentView(R.layout.activity_game);
        // Get the custom game view containing sprites
        mGameView = findViewById(R.id.gameview);
        init();
    }

    void init() {
        // Make a background thread to handle the game logic
        mThread = new Thread(() -> {
            long currentTime = System.currentTimeMillis() - 10;
            while (!mPaused) {
                Log.i(TAG, "Thread Running");
                long dt = System.currentTimeMillis() - currentTime;
                currentTime = System.currentTimeMillis();
                // Update and refresh canvas
                mGameView.update(dt / 1000f);
                mGameView.postInvalidate();
            }
        });
        mThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPaused = true;
        // Stops the thread from running in the background
        try {
            mThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mThread = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPaused) {
            mPaused = false;
            init();
        }
    }
}