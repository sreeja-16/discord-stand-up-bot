package com.suvam.discord_Stand_up_bot.entity;

import javax.persistence.*;

@Entity
public class GuildExecutionTime {
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qid;
    private String guildName;
    private String executionTime;

    public GuildExecutionTime(String guildName, String executionTime) {
        this.guildName = guildName;
        this.executionTime = executionTime;
    }
    public GuildExecutionTime(){

    }


    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }
}
