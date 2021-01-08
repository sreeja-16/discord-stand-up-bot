package com.suvam.discord_Stand_up_bot.util;

import com.suvam.discord_Stand_up_bot.entity.GuildExecutionTime;
import com.suvam.discord_Stand_up_bot.entity.WorkingDays;
import com.suvam.discord_Stand_up_bot.service.GuildExecutionTimeService;
import com.suvam.discord_Stand_up_bot.service.WorkingDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InsertDays {

    private static WorkingDaysService workingDaysService;
    private static GuildExecutionTimeService guildExecutionTimeService;

    @Autowired
    public InsertDays(WorkingDaysService workingDaysService, GuildExecutionTimeService guildExecutionTimeService) {
        this.workingDaysService = workingDaysService;
        this.guildExecutionTimeService = guildExecutionTimeService;
    }

    public void deleteAllDaysFromDB() {
        workingDaysService.deleteAll();
    }

    public void addDaysToDB(List<Integer> days) {
        List<Integer> allowedDays = new ArrayList<>();
        for (int day : days) {
            WorkingDays workingDay = new WorkingDays(day);
            workingDaysService.save(workingDay);
        }
    }

    public static List<WorkingDays> findAllWorkingDays() {
        return workingDaysService.findAll();
    }

    public static List<WorkingDays> findGuildWorkingDays(String guildName) {
        return workingDaysService.findWorkingDayByGuild(guildName);
    }

    public static void insert(String guildName, CheckedWeekDays checkedWeekDays) {
        List<Integer> workingDays = checkedWeekDays.getWorkingWeekDays();

        WorkingDays sunday = new WorkingDays();
        sunday.setDayNumber(1);
        if (workingDays.contains(1)) {
            sunday.setIsChecked(1);
        } else {
            sunday.setIsChecked(0);
        }
        sunday.setGuildName(guildName);
        workingDaysService.save(sunday);

        WorkingDays monday = new WorkingDays();
        monday.setDayNumber(2);
        if (workingDays.contains(2)) {
            monday.setIsChecked(1);
        } else {
            monday.setIsChecked(0);
        }
        monday.setGuildName(guildName);
        workingDaysService.save(monday);

        WorkingDays tuesday = new WorkingDays();
        tuesday.setDayNumber(3);
        if (workingDays.contains(3)) {
            tuesday.setIsChecked(1);
        } else {
            tuesday.setIsChecked(0);
        }
        tuesday.setGuildName(guildName);
        workingDaysService.save(tuesday);

        WorkingDays wednesday = new WorkingDays();
        wednesday.setDayNumber(4);
        if (workingDays.contains(4)) {
            wednesday.setIsChecked(1);
        } else {
            wednesday.setIsChecked(0);
        }
        wednesday.setGuildName(guildName);
        workingDaysService.save(wednesday);

        WorkingDays thursday = new WorkingDays();
        thursday.setDayNumber(5);
        if (workingDays.contains(5)) {
            thursday.setIsChecked(1);
        } else {
            thursday.setIsChecked(0);
        }
        thursday.setGuildName(guildName);
        workingDaysService.save(thursday);

        WorkingDays friday = new WorkingDays();
        friday.setDayNumber(6);
        if (workingDays.contains(6)) {
            friday.setIsChecked(1);
        } else {
            friday.setIsChecked(0);
        }
        friday.setGuildName(guildName);
        workingDaysService.save(friday);

        WorkingDays saturday = new WorkingDays();
        saturday.setDayNumber(7);
        if (workingDays.contains(7)) {
            saturday.setIsChecked(1);
        } else {
            saturday.setIsChecked(0);
        }
        saturday.setGuildName(guildName);
        workingDaysService.save(saturday);

    }

    public static List<GuildExecutionTime> getGuildExecutionTime(String time) {
        return guildExecutionTimeService.findByTime(time);
    }
}