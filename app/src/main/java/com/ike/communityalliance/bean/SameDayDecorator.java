package com.ike.communityalliance.bean;


import com.ike.communityalliance.utils.DateUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;

/**
 * Created by Min on 2017/4/18.
 * 给日历当天进行圆背景修饰：
 */

public class SameDayDecorator implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        String dateStr = DateUtils.currentTime();
        Date parse = DateUtils.parseDate(dateStr,"yyyy-MM-dd");
        if (day.getDate().equals(parse)) {
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new CircleBackGroundSpan());
    }
}
