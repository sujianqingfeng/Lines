package com.sujian.lines.data.event;

/**
 * Created by sujian on 2016/9/14.
 * Mail:121116111@qq.com
 */
public class TimeEvent {
    private int year;
    private int month;
    private int day;


    public TimeEvent(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "TimeEvent{" +
                "day=" + day +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
