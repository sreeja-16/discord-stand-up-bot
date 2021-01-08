package com.suvam.discord_Stand_up_bot.task;

import com.suvam.discord_Stand_up_bot.entity.DiscordUser;
import com.suvam.discord_Stand_up_bot.entity.StandUpQuestion;
import com.suvam.discord_Stand_up_bot.entity.UserResponse;
import com.suvam.discord_Stand_up_bot.util.GetQuestions;
import com.suvam.discord_Stand_up_bot.util.SaveDiscordUser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuildMember {
    private final User user;
    private String guildName;
    private int questionCountPerUser;
    public int questionsAnswered;
    private final List<String> questions;

   // private final String GUILD_NAME = "standup-bot-server";

    public GuildMember(User user, String guildName) {
        this.user = user;
        this.guildName = guildName;
        this.questions = new ArrayList<>();
        saveUser(user);
        loadQuestions();
        startPrivateChat();
    }

    private void saveUser(User user) {
        long discordUserId = Long.parseLong(this.user.getId());
        DiscordUser discordUser = new DiscordUser(discordUserId, user.getName(), false);
        SaveDiscordUser.saveUser(discordUser);
    }

    private void loadQuestions() {
        List<StandUpQuestion> questionsFromDB = GetQuestions.loadQuestions();
        for (StandUpQuestion question : questionsFromDB) {
            questions.add(question.getQuestion());
        }
    }

    public void startPrivateChat() {

        int questionNumber = questionCountPerUser++;
        int totalNumberOfQuestions = GetQuestions.getTotalNumberOfQuestions();
        while (questionNumber < totalNumberOfQuestions) {
            user.openPrivateChannel().queue(channel -> channel.sendMessage(questions.get(questionNumber)).queue());
            break;
        }
        if (questionNumber == totalNumberOfQuestions - 1) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Todays stand up for - " + user.getName());

            DiscordUser userAfterStandUp = SaveDiscordUser.getDiscordUserByName(user.getName());
            int memberResponseListSize = userAfterStandUp.getResponses().size();
            TextChannel channel = user.getJDA().getGuildsByName(guildName, true).get(0).getTextChannelsByName("general", true).get(0);
            for (int i = totalNumberOfQuestions - 1; i >= 1; i--) {
                int responseNumber = memberResponseListSize - i;
                UserResponse response = userAfterStandUp.getResponses().get(responseNumber);
                String todaysUserResponse = response.getResponse();

                StandUpQuestion question = response.getQuestion();

                embedBuilder.addField(question.getQuestion(), todaysUserResponse, false);
                embedBuilder.setColor(Color.blue);
            }
            channel.sendMessage(embedBuilder.build()).queue();
        }
    }


    public int getQuestionCountPerUser() {
        return questionCountPerUser;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public int getQuestionsAnswered() {
        return questionsAnswered;
    }

    public void setQuestionsAnswered(int questionsAnswered) {
        this.questionsAnswered = questionsAnswered;
    }
}
