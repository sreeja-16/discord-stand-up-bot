package com.suvam.discord_Stand_up_bot.util;

import com.suvam.discord_Stand_up_bot.entity.StandUpQuestion;
import com.suvam.discord_Stand_up_bot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQuestions {
    private static QuestionService questionService;

    @Autowired
    public GetQuestions(QuestionService questionService) {
        this.questionService = questionService;
    }

    public static List<StandUpQuestion> loadQuestions() {
        return questionService.findAll();
    }

    public static StandUpQuestion getQuestionByQuestion(String question) {
        return questionService.getQuestionByQuestion(question);
    }

    public static int getTotalNumberOfQuestions() {
        return questionService.getTotalNUmberOfQuestion();
    }

    public static void deleteAll() {
        questionService.deleteAll();
    }
}
