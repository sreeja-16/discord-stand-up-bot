package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.GuildExecutionTime;
import com.suvam.discord_Stand_up_bot.repository.GuildExecutionTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuildExecutionServiceImpl implements GuildExecutionTimeService{

    private final GuildExecutionTimeRepository guildExecutionTimeRepository;

    @Autowired
    public GuildExecutionServiceImpl(GuildExecutionTimeRepository guildExecutionTimeRepository) {
        this.guildExecutionTimeRepository=guildExecutionTimeRepository;
    }

    @Override
    public void insert(GuildExecutionTime guildExecutionTime) {
        if(guildExecutionTimeRepository.findByGuildName(guildExecutionTime.getGuildName())==null) {
            guildExecutionTimeRepository.save(guildExecutionTime);
        }else{
            guildExecutionTimeRepository.update(guildExecutionTime.getExecutionTime(),guildExecutionTime.getGuildName());
        }
    }

    @Override
    public List<GuildExecutionTime> findByTime(String time) {
        return guildExecutionTimeRepository.findByTime(time);
    }


}
