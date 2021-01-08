package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.WorkingDays;

import java.util.List;

public interface WorkingDaysService {

    public void save(WorkingDays workingDay);

    public void deleteAll();

    public List<WorkingDays> findAll();

    public List<WorkingDays> findWorkingDayByGuild(String guildName);

    public void UpdateWorkingDays(int id, int isChecked);

    public void insert(int id,int dayNumber,int isChecked);

    public WorkingDays findWorkingDayById(int id);
}