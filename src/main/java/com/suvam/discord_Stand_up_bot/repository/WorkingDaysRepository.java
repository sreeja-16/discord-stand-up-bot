package com.suvam.discord_Stand_up_bot.repository;

import com.suvam.discord_Stand_up_bot.entity.WorkingDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WorkingDaysRepository extends JpaRepository<WorkingDays, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM working_days", nativeQuery = true)
    public void deleteAllRows();

    @Transactional
    @Modifying
    @Query(value = "UPDATE working_days SET ischecked=?2 WHERE id=?1", nativeQuery = true)
    public void UpdateWorkingDay(int id, int isChecked);

    @Query(value = "SELECT * FROM working_days WHERE id=?1 ", nativeQuery = true)
    public WorkingDays findWorkingDayById(int id);

    @Query(value = "SELECT * FROM working_days WHERE guild_name=?1 ", nativeQuery = true)
    public List<WorkingDays> findWorkingDayByGuild(String guildName);


}