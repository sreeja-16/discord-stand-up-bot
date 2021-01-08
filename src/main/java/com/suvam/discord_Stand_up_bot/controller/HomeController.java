package com.suvam.discord_Stand_up_bot.controller;

import com.suvam.discord_Stand_up_bot.DiscordStandUpBotApplication;
import com.suvam.discord_Stand_up_bot.entity.GuildExecutionTime;
import com.suvam.discord_Stand_up_bot.entity.UserResponse;
import com.suvam.discord_Stand_up_bot.entity.WorkingDays;
import com.suvam.discord_Stand_up_bot.service.GuildExecutionTimeService;
import com.suvam.discord_Stand_up_bot.service.ResponseService;
import com.suvam.discord_Stand_up_bot.service.WorkingDaysService;
import com.suvam.discord_Stand_up_bot.util.CheckedWeekDays;
import com.suvam.discord_Stand_up_bot.util.InsertDays;
import com.suvam.discord_Stand_up_bot.util.WeekDays;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class HomeController {
    private final CheckedWeekDays checkedWeekDays;
    List<WeekDays> weekDaysList;
    WorkingDaysService workingDaysService;
    GuildExecutionTimeService guildExecutionTimeService;
    ResponseService responseService;

    @Autowired
    public HomeController(CheckedWeekDays checkedWeekDays, ResponseService responseService,
                          WorkingDaysService workingDaysService,
                          GuildExecutionTimeService guildExecutionTimeService) {
        this.checkedWeekDays = checkedWeekDays;
        this.responseService = responseService;
        weekDaysList = new ArrayList<>();

        this.guildExecutionTimeService = guildExecutionTimeService;
        this.workingDaysService = workingDaysService;
        weekDaysList.add(new WeekDays("Sunday", 1));
        weekDaysList.add(new WeekDays("Monday", 2));
        weekDaysList.add(new WeekDays("Tuesday", 3));
        weekDaysList.add(new WeekDays("Wednesday", 4));
        weekDaysList.add(new WeekDays("Thursday", 5));
        weekDaysList.add(new WeekDays("Friday", 6));
        weekDaysList.add(new WeekDays("Saturday", 7));
    }

    @RequestMapping("/")
    public String home(Model homeModel) {
        return "index";
    }

    @RequestMapping("/schedule")
    public String schedule(Model scheduleModel) {
        scheduleModel.addAttribute("scheduledDays", checkedWeekDays);
        scheduleModel.addAttribute("allWeekDays", weekDaysList);

        List<String> allGuildName = new ArrayList<>();
        List<Guild> allGuilds = DiscordStandUpBotApplication.globalJda.getGuilds();
        for (Guild guild : allGuilds) {
            allGuildName.add(guild.getName());
        }
        scheduleModel.addAttribute("allGuilds", allGuildName);
        return "schedule";
    }

    @PostMapping("/processCheckBox")
    public String processSelectedDays(@ModelAttribute("scheduledDays") CheckedWeekDays checkedWeekDays) {
        String guildName = checkedWeekDays.getGuildName();
        // List<WorkingDays> workingDaysInDb = workingDaysService.findAll();
        List<WorkingDays> guildWorkingDays = workingDaysService.findWorkingDayByGuild(guildName);
        List<Integer> checkDays = checkedWeekDays.getWorkingWeekDays();
        if (guildWorkingDays.size()!=0) {
          //  System.out.println("inside IF part of working day controller ############################## size list is "+guildWorkingDays.size());
            for (WorkingDays workingDay : guildWorkingDays) {
                if (checkDays.contains(workingDay.getDayNumber())) {
                    workingDay.setIsChecked(1);
                } else {
                    workingDay.setIsChecked(0);
                }
                workingDaysService.UpdateWorkingDays(workingDay.getId(), workingDay.getIsChecked());
            }
        }else{
            System.out.println("inside ELSE part of working day controller ##############################");
            InsertDays.insert(guildName,checkedWeekDays);
        }

        String time = checkedWeekDays.getTime();
        this.guildExecutionTimeService.insert(new GuildExecutionTime(guildName, time));
        return "success";
    }

    @GetMapping("/extra-features")
    public String extraFeatures(){
        return "extra-features";
    }

    @GetMapping("/download-responses")
    public ResponseEntity<Resource> getFile() {
        String filename = "responses.csv";
        InputStreamResource file = new InputStreamResource(responseService.loadResponses());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/select-date")
    public String selectDate(Model model){
        return "select-date-responses";
    }

    @GetMapping("/list-responses")
    public String responsesData(@Param("selectedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date selectedDate, Model model) {
        List<UserResponse> responses = responseService.loadResponsesByDate(selectedDate);
        model.addAttribute("responses", responses);
        return "list-responses";
    }
}
