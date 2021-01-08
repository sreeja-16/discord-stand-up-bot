package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.GuildExecutionTime;
import org.springframework.stereotype.Service;

@Service
public interface GuildExecutionTimeService {

    public void insert(GuildExecutionTime guildExecutionTime);

    public GuildExecutionTime findByTime(String time);
}
