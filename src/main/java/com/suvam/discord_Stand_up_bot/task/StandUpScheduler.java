package com.suvam.discord_Stand_up_bot.task;


import com.suvam.discord_Stand_up_bot.entity.GuildExecutionTime;
import com.suvam.discord_Stand_up_bot.entity.WorkingDays;
import com.suvam.discord_Stand_up_bot.util.InsertDays;
import com.suvam.discord_Stand_up_bot.util.InsertQuestions;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.text.SimpleDateFormat;
import java.util.*;

public class StandUpScheduler extends TimerTask {
    private JDA jda;
    public static Map<String, GuildMember> membersMap;
    private List<Integer> allowedDayValues;
    private List<WorkingDays> workingDaysFromDb;
    private final Timer timer;
    String botName = "standup-bot";

    public StandUpScheduler(JDA jda, Timer timer) {
        this.jda = jda;
        this.timer = timer;
        workingDaysFromDb = null;
       // allowedDayValues = new ArrayList<>();
    }

    public void findWorkingDays(String guildName) {
        allowedDayValues = new ArrayList<>();
        workingDaysFromDb = InsertDays.findGuildWorkingDays(guildName);
        for (WorkingDays workingDay : workingDaysFromDb) {
            if (workingDay.getIsChecked() == 1) {
                System.out.println("allowed day number "+workingDay.getDayNumber());
                allowedDayValues.add(workingDay.getDayNumber());
            }
        }
    }

    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        Integer todaysDay = calendar.get(Calendar.DAY_OF_WEEK);

        String presentTime = new SimpleDateFormat("HH:mm").format(new Date());
        GuildExecutionTime guildTime = InsertDays.getGuildExecutionTime(presentTime); // same time for multiple server
        if (guildTime != null) {
            this.findWorkingDays(guildTime.getGuildName());
            if (allowedDayValues.contains(todaysDay)) {
                System.out.println("##############Entered checkbox selected part"+todaysDay);
                for(Integer allowedDayValue:allowedDayValues) {
                    System.out.println(allowedDayValue+"*********************");
                }
                TimerTask attendance = new com.suvam.discord_Stand_up_bot.task.Attendence(jda, guildTime.getGuildName());
                timer.schedule(attendance, 1000 * 60 * 4);
                InsertQuestions.insert();
                membersMap = new HashMap<>();
                List<Member> members = jda.getGuildsByName(guildTime.getGuildName(), true).get(0).getMembers();
                Iterator<Member> memberIterator = members.iterator();
                while (memberIterator.hasNext()) {
                    User guildUser = memberIterator.next().getUser();
                    String name = guildUser.getName();
                    if (!name.equals(botName)) {
                        membersMap.put(name, new GuildMember(guildUser, guildTime.getGuildName()));
                    }
                }
            }
        }
    }
}
