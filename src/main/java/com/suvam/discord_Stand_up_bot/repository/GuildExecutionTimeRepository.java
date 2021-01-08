package com.suvam.discord_Stand_up_bot.repository;

import com.suvam.discord_Stand_up_bot.entity.GuildExecutionTime;
import com.suvam.discord_Stand_up_bot.entity.StandUpQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GuildExecutionTimeRepository extends JpaRepository<GuildExecutionTime, Integer> {

    @Query(value="SELECT * FROM guild_execution_time WHERE guild_name=?1",nativeQuery = true)
    public GuildExecutionTime findByGuildName(String guildName);


    @Modifying
    @Transactional
    @Query(value="UPDATE guild_execution_time SET execution_time=?1 WHERE guild_name=?2",nativeQuery = true)
    public void update(String time,String guildName);

    @Query(value="SELECT * FROM guild_execution_time WHERE execution_time=?1",nativeQuery = true)
    public List<GuildExecutionTime> findByTime(String time);

}
