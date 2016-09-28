package com.sujian.lines.data.event;

/**
 * 日夜间事件
 */

public class NightModeEvent {
    private boolean isNight;

    public NightModeEvent(boolean isNight) {
        this.isNight = isNight;
    }

    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean night) {
        isNight = night;
    }
}
