package com.ike.communityalliance.bean;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/**
 * Created by Min on 2017/4/18.
 * 给日历当天进行圆背景修饰：
 */

public class CircleBackGroundSpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#10DB9F"));
        c.drawCircle((right - left) / 2, (bottom - top) / 2, 36, paint);
    }
}
