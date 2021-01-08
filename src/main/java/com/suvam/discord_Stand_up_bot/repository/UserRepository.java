package com.suvam.discord_Stand_up_bot.repository;

import com.suvam.discord_Stand_up_bot.entity.DiscordUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DiscordUser, Long> {

    public DiscordUser findByName(String name);
}
