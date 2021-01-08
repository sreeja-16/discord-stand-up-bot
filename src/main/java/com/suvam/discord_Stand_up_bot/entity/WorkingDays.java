package com.suvam.discord_Stand_up_bot.entity;

import javax.persistence.*;

@Entity
@Table(name = "working_days")
public class WorkingDays {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "day")
    private int dayNumber;

    @Column(name = "ischecked")
    private int isChecked;

    @Column(name = "guildName")
    private String guildName;

    public WorkingDays() {
    }

    public WorkingDays(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int day) {
        this.dayNumber = day;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }
}