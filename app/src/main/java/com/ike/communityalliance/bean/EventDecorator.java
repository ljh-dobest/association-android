package com.ike.communityalliance.bean;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;
import java.util.List;

/**
 * Created by Min on 2017/4/18.
 * 对特定日期增加圆环修饰:
 */

public class EventDecorator implements DayViewDecorator {
    private List<Date> dates;

    public EventDecorator(List<Date> dates) {
        this.dates = dates;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day.getDate());
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new AnnulusSpan());
    }
}