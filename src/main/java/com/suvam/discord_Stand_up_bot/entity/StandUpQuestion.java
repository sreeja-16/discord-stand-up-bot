package com.suvam.discord_Stand_up_bot.entity;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class StandUpQuestion {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qid;

    @Column(name = "question")
    private String question;

    public StandUpQuestion() {

    }

    public StandUpQuestion(String question) {
        this.question = question;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

  /*  public List<UserResponse> getResponses () {
        return responses;
    }

    public void setResponses (List<UserResponse> responses) {
        this.responses = responses;
    }*/

}
