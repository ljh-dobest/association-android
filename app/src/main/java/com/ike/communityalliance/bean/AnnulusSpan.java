package com.ike.communityalliance.bean;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/**
 * Created by Min on 2017/4/18.
 * 对特定日期增加圆环修饰
 */

public class AnnulusSpan implements LineBackgroundSpan {
    @Override
    public void drawBackground(Canvas c, Paint p, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
        Paint paint = new Paint();
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.STROKE);//绘制空心圆或 空心矩形
        int ringWidth = 2;//圆环宽度
        //绘制圆环
        paint.setColor(Color.parseColor("#00bcbe"));
        paint.setStrokeWidth(ringWidth);
        c.drawCircle((right - left) / 2, (bottom - top) / 2, 36, paint);
    }
}
