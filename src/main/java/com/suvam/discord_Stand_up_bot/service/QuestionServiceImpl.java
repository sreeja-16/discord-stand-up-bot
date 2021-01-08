package com.suvam.discord_Stand_up_bot.service;

import com.suvam.discord_Stand_up_bot.entity.StandUpQuestion;
import com.suvam.discord_Stand_up_bot.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<StandUpQuestion> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public StandUpQuestion findById(int id) {
        Optional<StandUpQuestion> result = questionRepository.findById(id);
        StandUpQuestion question = null;
        if (result.isPresent()) {
            question = result.get();
        }
        return question;
    }

    @Override
    public void save(StandUpQuestion question) {
        questionRepository.save(question);
    }

    @Override
    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }

    @Override
    public StandUpQuestion getQuestionByQuestion(String question) {
        return questionRepository.getStandUpQuestionByquestion(question);
    }

    @Override
    public int getTotalNUmberOfQuestion() {
        return (int) questionRepository.count();
    }

    @Override
    public void saveAll(List<StandUpQuestion> questions) {
        questionRepository.saveAll(questions);
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
    }

}
