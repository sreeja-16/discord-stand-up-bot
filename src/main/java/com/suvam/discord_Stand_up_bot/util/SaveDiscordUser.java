package com.suvam.discord_Stand_up_bot.util;

import com.suvam.discord_Stand_up_bot.entity.DiscordUser;
import com.suvam.discord_Stand_up_bot.service.DiscordUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveDiscordUser {
    private static DiscordUserService userService;

    @Autowired
    public SaveDiscordUser(DiscordUserService userService) {
        SaveDiscordUser.userService = userService;
    }

    public static void saveUser(DiscordUser user) {
        userService.save(user);
    }

    public static DiscordUser getDiscordUserByName(String name) {
        return userService.findByName(name);
    }

    public static void deleteUserById(long id) {
        userService.deleteById(id);
    }

}
