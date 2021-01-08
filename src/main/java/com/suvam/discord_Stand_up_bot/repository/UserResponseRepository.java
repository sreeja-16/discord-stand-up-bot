package com.suvam.discord_Stand_up_bot.repository;

import com.suvam.discord_Stand_up_bot.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserResponseRepository extends JpaRepository<UserResponse, Integer> {

    @Query(value = "select * from user_response  where date(published_at) = ?1", nativeQuery = true)
    public List<UserResponse> loadResponsesByDate (Date selectedDate);
}
