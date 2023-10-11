package com.ianyap.mobiusinfinite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private int mWidth, mHeight;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Add sprites to the game
    private void init() {

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
        /*if(mBubbles!=null)
            mBubbles.touch(event);*/
        return true;
    }
    @Override
    protected void onDraw(Canvas c) {
        /*if(mBubbles!=null)
            mBubbles.draw(c);*/
        // TODO: Move to a separate class for sprites
        Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 0,0, 255));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setAntiAlias(true);
        float sr=2f*10/3f;
        RectF mArcRect=new RectF(-sr,-sr,sr,sr);

        c.save();
        c.translate(mWidth/2-sr,mHeight/2-sr);
        c.drawCircle(0,0,10,mPaint);
        c.scale(2/100f,2/100f);
        //      c.drawText("Hello",-mR,mR+32,mPaint);
        c.drawArc(mArcRect,300,30,false, mPaint);
        c.restore();
    }
}
