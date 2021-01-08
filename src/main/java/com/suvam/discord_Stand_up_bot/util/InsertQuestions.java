package com.suvam.discord_Stand_up_bot.util;

import com.suvam.discord_Stand_up_bot.entity.StandUpQuestion;
import com.suvam.discord_Stand_up_bot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InsertQuestions {
    private static String yesterdayTask = "What have you done yesterday";
    private static String todayTask = "What will you do today";
    private static String doubt = "if any query feel free to ask";
    private static String bye = "thank you and have a nice day";

    private static List<StandUpQuestion> questions = new ArrayList<>();

    private static QuestionService questionService;

    @Autowired
    public InsertQuestions(QuestionService questionService) {
        this.questionService = questionService;
    }

    public static void insert() {
       /*
        questions.add(new StandUpQuestion(yesterdayTask));
        questions.add(new StandUpQuestion(todayTask));
        questions.add(new StandUpQuestion(doubt));
        questions.add(new StandUpQuestion(bye));

        questionService.saveAll(questions); */

        if (questionService.getQuestionByQuestion(InsertQuestions.yesterdayTask) == null) {
            questionService.save(new StandUpQuestion(yesterdayTask));
        }
        if (questionService.getQuestionByQuestion(InsertQuestions.todayTask) == null) {
            questionService.save(new StandUpQuestion(todayTask));
        }
        if (questionService.getQuestionByQuestion(InsertQuestions.doubt) == null) {
            questionService.save(new StandUpQuestion(doubt));
        }
        if (questionService.getQuestionByQuestion(InsertQuestions.bye) == null) {
            questionService.save(new StandUpQuestion(bye));
        }
    }

}
