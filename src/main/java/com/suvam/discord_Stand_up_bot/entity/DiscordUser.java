package com.suvam.discord_Stand_up_bot.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class DiscordUser {
    @Id
    @Column(name = "id")
    private long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "has_answered")
    private boolean hasAnswered;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<UserResponse> responses;

    public DiscordUser() {
    }

    public DiscordUser(long id, String name, boolean hasAnswered) {
        this.userId = id;
        this.name = name;
        this.hasAnswered = hasAnswered;
        this.responses = new ArrayList<>();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasAnswered() {
        return hasAnswered;
    }

    public void setHasAnswered(boolean hasAnswered) {
        this.hasAnswered = hasAnswered;
    }

    public List<UserResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<UserResponse> responses) {
        this.responses = responses;
    }
}
