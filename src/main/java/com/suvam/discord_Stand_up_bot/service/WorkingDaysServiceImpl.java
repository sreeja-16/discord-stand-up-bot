package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.WorkingDays;
import com.suvam.discord_Stand_up_bot.repository.WorkingDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingDaysServiceImpl implements WorkingDaysService {

    private WorkingDaysRepository workingDaysRepository;

    @Autowired
    public WorkingDaysServiceImpl(WorkingDaysRepository workingDaysRepository) {
        this.workingDaysRepository = workingDaysRepository;
    }


    @Override
    public void save(WorkingDays workingDay) {
        workingDaysRepository.save(workingDay);
    }

    @Override
    public void deleteAll() {
        workingDaysRepository.deleteAllRows();
    }

    public List<WorkingDays> findAll() {
        return workingDaysRepository.findAll();
    }

    @Override
    public List<WorkingDays> findWorkingDayByGuild(String guildName) {
        return workingDaysRepository.findWorkingDayByGuild(guildName);
    }

    public void UpdateWorkingDays(int id, int isChecked) {
        workingDaysRepository.UpdateWorkingDay(id, isChecked);
    }

    public WorkingDays findWorkingDayById(int id){
        return workingDaysRepository.findWorkingDayById(id);
    }

    @Override
    public void insert(int id, int dayNumber, int isChecked) {

    }
}