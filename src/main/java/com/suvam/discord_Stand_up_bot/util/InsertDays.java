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

  /*  public static void insert(){
        if(workingDaysService.findWorkingDayById(0)==null){
            WorkingDays sunday=new WorkingDays();
         //   sunday.setId(0);
            sunday.setDayNumber(1);
            sunday.setIsChecked(0);
            sunday.setGuildName("defaultGuild");
            workingDaysService.save(sunday);
        }
        if(workingDaysService.findWorkingDayById(1)==null){
            WorkingDays monday=new WorkingDays();
         //   monday.setId(1);
            monday.setDayNumber(2);
            monday.setIsChecked(1);
            monday.setGuildName("defaultGuild");
            workingDaysService.save(monday);
        }
        if(workingDaysService.findWorkingDayById(2)==null){
            WorkingDays tuesday=new WorkingDays();
        //    tuesday.setId(2);
            tuesday.setDayNumber(3);
            tuesday.setIsChecked(1);
            tuesday.setGuildName("defaultGuild");
            workingDaysService.save(tuesday);
        }
        if(workingDaysService.findWorkingDayById(3)==null){
            WorkingDays wednesday=new WorkingDays();
        //    wednesday.setId(3);
            wednesday.setDayNumber(4);
            wednesday.setIsChecked(1);
            wednesday.setGuildName("defaultGuild");
            workingDaysService.save(wednesday);
        }
        if(workingDaysService.findWorkingDayById(4)==null){
            WorkingDays thursday=new WorkingDays();
         //   thursday.setId(4);
            thursday.setDayNumber(5);
            thursday.setIsChecked(1);
            thursday.setGuildName("defaultGuild");
            workingDaysService.save(thursday);
        }
        if(workingDaysService.findWorkingDayById(5)==null){
            WorkingDays friday=new WorkingDays();
           // friday.setId(5);
            friday.setDayNumber(6);
            friday.setIsChecked(1);
            friday.setGuildName("defaultGuild");
            workingDaysService.save(friday);
        }
        if(workingDaysService.findWorkingDayById(6)==null){
            WorkingDays saturday=new WorkingDays();
         //   saturday.setId(6);
            saturday.setDayNumber(7);
            saturday.setIsChecked(1);
            saturday.setGuildName("defaultGuild");
            workingDaysService.save(saturday);
        }
    }*/

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

    public static GuildExecutionTime getGuildExecutionTime(String time) {
        return guildExecutionTimeService.findByTime(time);
    }
}