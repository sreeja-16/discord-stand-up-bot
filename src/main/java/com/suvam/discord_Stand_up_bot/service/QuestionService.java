package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.StandUpQuestion;

import java.util.List;

public interface QuestionService {

    public List<StandUpQuestion> findAll();

    public StandUpQuestion findById(int id);

    public void save(StandUpQuestion post);

    public void deleteById(int id);

    public StandUpQuestion getQuestionByQuestion(String question);

    public int getTotalNUmberOfQuestion();

    public void saveAll(List<StandUpQuestion> questions);

    public void deleteAll();
}
