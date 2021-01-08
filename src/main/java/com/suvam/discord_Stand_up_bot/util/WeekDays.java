package com.suvam.discord_Stand_up_bot.util;

public class WeekDays {
    private String weekDay;
    private int weekDayValue;

    public WeekDays(String weekDay, int weekDayValue) {
        this.weekDay = weekDay;
        this.weekDayValue = weekDayValue;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public int getWeekDayValue() {
        return weekDayValue;
    }

    public void setWeekDayValue(int weekDayValue) {
        this.weekDayValue = weekDayValue;
    }
}
