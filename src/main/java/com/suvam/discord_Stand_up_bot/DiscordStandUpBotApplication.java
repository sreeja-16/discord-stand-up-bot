package com.suvam.discord_Stand_up_bot;

import com.suvam.discord_Stand_up_bot.event.UserRepliedEvent;
import com.suvam.discord_Stand_up_bot.task.StandUpScheduler;
import com.suvam.discord_Stand_up_bot.util.InsertDays;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.LoginException;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class DiscordStandUpBotApplication {
    public static JDA globalJda;
    static {
        try {
            globalJda = JDABuilder.createDefault("Nzk1OTIzNTUyODU4ODAwMTk4.X_Qbkg.iEnx-SNaK85LUswgW1NyWdB4UCQ").setChunkingFilter(ChunkingFilter.ALL)
                    .setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .setActivity(Activity.watching("morning standup"))
                    .addEventListeners(new UserRepliedEvent())
                    .build().awaitReady();
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws LoginException, InterruptedException {
        SpringApplication.run(DiscordStandUpBotApplication.class, args);
/*
        String token = "Nzk1OTIzNTUyODU4ODAwMTk4.X_Qbkg.iEnx-SNaK85LUswgW1NyWdB4UCQ";
        JDA jda = JDABuilder.createDefault(token).setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.watching("morning standup"))
                .addEventListeners(new UserRepliedEvent())
                .build().awaitReady();
        DiscordStandUpBotApplication.globalJda=jda;*/

      //  InsertDays.insert();
        Timer timer = new Timer();
        TimerTask standUp = new StandUpScheduler(globalJda, timer);
        timer.schedule(standUp, 0, 1000*60);
    }
    //mvn spring-boot:run
}
