package com.suvam.discord_Stand_up_bot.event;

import com.suvam.discord_Stand_up_bot.entity.DiscordUser;
import com.suvam.discord_Stand_up_bot.entity.StandUpQuestion;
import com.suvam.discord_Stand_up_bot.entity.UserResponse;
import com.suvam.discord_Stand_up_bot.task.GuildMember;
import com.suvam.discord_Stand_up_bot.task.StandUpScheduler;
import com.suvam.discord_Stand_up_bot.util.GetQuestions;
import com.suvam.discord_Stand_up_bot.util.Response;
import com.suvam.discord_Stand_up_bot.util.SaveDiscordUser;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserRepliedEvent extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String userName = event.getAuthor().getName();
            String response = event.getMessage().getContentRaw();

            GuildMember respondedGuildMember = StandUpScheduler.membersMap.get(userName);
            DiscordUser respondedUser = SaveDiscordUser.getDiscordUserByName(userName);

            int totalQuestionAnswered = respondedGuildMember.getQuestionsAnswered();
            respondedGuildMember.setQuestionsAnswered(++totalQuestionAnswered);

            int lastAskedQuestionNumber = respondedGuildMember.getQuestionCountPerUser() - 1;
            String lastRespondedQuestion = respondedGuildMember.getQuestions().get(lastAskedQuestionNumber);
            StandUpQuestion question = GetQuestions.getQuestionByQuestion(lastRespondedQuestion);

            UserResponse userResponse = new UserResponse(response, respondedUser);
            userResponse.setQuestion(question);
            Response.saveResponse(userResponse);

            respondedUser.getResponses().add(userResponse);

            respondedGuildMember.startPrivateChat();
        }
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
       List<Member> guildMemberList= event.getGuild().getMembers();
    }
}
