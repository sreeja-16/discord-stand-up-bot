package com.suvam.discord_Stand_up_bot.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckedWeekDays {
    private List<Integer> workingWeekDays;
    private String guildName;
    private String time;

    public List<Integer> getWorkingWeekDays() {
        return workingWeekDays;
    }

    public void setWorkingWeekDays(List<Integer> workingWeekDays) {
        this.workingWeekDays = workingWeekDays;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
