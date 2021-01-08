package com.suvam.discord_Stand_up_bot.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_response")
public class UserResponse {

    @Id
    @Column(name = "ans_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "response")
    private String response;

    @Column(name = "published_at")
    private Timestamp answeredAt;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "uid")
    private DiscordUser user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}) //,CascadeType.REMOVE
    @JoinColumn(name = "qid")
    private StandUpQuestion question;

    public UserResponse() {
    }

    public UserResponse(String response, DiscordUser user) {
        this.response = response;
        this.user = user;
        this.answeredAt = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Timestamp getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(Timestamp answeredAt) {
        this.answeredAt = answeredAt;
    }

    public DiscordUser getUser() {
        return user;
    }

    public void setUser(DiscordUser user) {
        this.user = user;
    }

   /* public List<StandUpQuestion> getQuestions () {
        return questions;
    }

    public void setQuestions (List<StandUpQuestion> questions) {
        this.questions = questions;
    }*/

    public StandUpQuestion getQuestion() {
        return question;
    }

    public void setQuestion(StandUpQuestion question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "response='" + response + '\'' +
                '}';
    }

}
