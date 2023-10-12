package com.ianyap.mobiusinfinite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class PlayerSprite {
    private final String TAG = "PlayerSprite";
    protected float mX,mY,mR, mVx, mVy, mWidth, mHeight;
    protected Paint mPaint;
    PlayerSprite(int x, int y, float vx, float vy, float r, int maxx, int maxy) {
        mX = x;
        mY = y;
        mR = r;
        mVx = vx;
        mVy = vy;
        mWidth = maxx;
        mHeight = maxy;
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 0,0, 255));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setAntiAlias(true);
    }

    public void draw(Canvas c) {
        c.save();
        c.translate(mX,mY);
        c.drawCircle(0,0,mR,mPaint);
        c.restore();
    }

    public void move(float dt) {
        Log.i(TAG, "" + mVx);
        Log.i(TAG, "" + mVy);
        //mX += mVx * dt * 5;
        //mY += mVy * dt * 5;
        if (mX > mWidth - mR) {
            mX = mWidth - mR;
            mVx = 0;
        } else if (mX < 0 + mR) {
            mX = 0 + mR;
            mVx = 0;
        }
        if (mY > mHeight - mR) {
            mY = mHeight - mR;
            mVy = 0;
        } else if (mY < 0 + mR) {
            mY = 0 + mR;
            mVy = 0;
        }
    }

    public void setVelocity(float vx, float vy) {
        mVx = vx;
        mVy = vy;
    }

    public float getY() { return mY; }
    public float getX() { return mX; }

    public float getR() { return mR; }
}
