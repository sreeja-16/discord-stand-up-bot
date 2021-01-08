package com.suvam.discord_Stand_up_bot.repository;

import com.suvam.discord_Stand_up_bot.entity.StandUpQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<StandUpQuestion, Integer> {

    @Query(value = "SELECT * FROM questions WHERE question=?1 LIMIT 1", nativeQuery = true)
    public StandUpQuestion getStandUpQuestionByquestion(String question);
}
