package com.ike.communityalliance.wedget.Wheel;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Min on 2017/2/25.
 */

public class MarqueeText extends TextView {
    public MarqueeText(Context context) {
        this(context,null);
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean isFocused() {
        return true;
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
    }
}
