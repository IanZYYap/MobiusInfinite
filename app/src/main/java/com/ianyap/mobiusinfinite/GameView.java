package com.ianyap.mobiusinfinite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private int mWidth, mHeight;
    private float mX, mY;
    private long mTouchTime;
    private PlayerSprite mPlayerSprite;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // Add sprites to the game
    private void init() {
        mPlayerSprite = new PlayerSprite(mWidth/2, mHeight/2, 0, 0, 50, mWidth, mHeight);
    }

    protected void update(float dt) {
        mPlayerSprite.move(dt);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed) {
            mWidth = right - left;
            mHeight = bottom - top;
            init();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // When dragging anywhere on the screen, move the Player
        switch (event.getAction()){
            // User presses down, set the original point they pressed
            case MotionEvent.ACTION_DOWN:
                Log.i("Test", "Touch Detected");
                mX = event.getX();
                mY = event.getY();
                /*Simple left-right movement that works
                if (mX > mWidth / 2) {
                    mPlayerSprite.setVelocity(500f * (mX - mWidth/2)/mWidth, 0);
                } else {
                    mPlayerSprite.setVelocity(-500f * (mWidth/2-mX)/mWidth, 0);
                }*/
                mTouchTime = event.getEventTime();
                return true;
            // User lets go
            case MotionEvent.ACTION_UP:
                // Stop the sprite from moving
                mPlayerSprite.setVelocity(0, 0);
                return true;
            // User drags
            case MotionEvent.ACTION_MOVE:
                Log.i("Test", "Drag Detected");
                /* Simple left-right movement that works
                mX = event.getX();
                mY = event.getY();
                if (mX > mWidth / 2) {
                    mPlayerSprite.setVelocity(2500f * (mX - mWidth/2)/mWidth, 0);
                } else {
                    mPlayerSprite.setVelocity(-2500f * (mWidth/2-mX)/mWidth, 0);
                }*/
                long time = event.getEventTime();
                if (time == mTouchTime)
                    time = mTouchTime + 1;
                float vX = (event.getX() - mX) / (time-mTouchTime);
                float vY = (event.getY() - mY) / (time-mTouchTime);
                // Tell the player sprite how fast to move
                mPlayerSprite.setVelocity(vX, vY);

                // To prevent values getting too big
                mX = event.getX();
                mY = event.getY();
                mTouchTime = event.getEventTime();

                return true;
        }
        return false;
    }
    @Override
    protected void onDraw(Canvas c) {
        /*if(mBubbles!=null)
            mBubbles.draw(c);*/
        // TODO: Move to a separate class for sprites
        mPlayerSprite.draw(c);
    }
}
