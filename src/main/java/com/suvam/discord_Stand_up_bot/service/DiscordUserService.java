package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.DiscordUser;

import java.util.List;

public interface DiscordUserService {

    public List<DiscordUser> findAll();

    public DiscordUser findById(long id);

    public void save(DiscordUser post);

    public void deleteById(long id);

    public DiscordUser findByName(String name);
}
