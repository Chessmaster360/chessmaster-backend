package com.chessmaster.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
    private int eloRating;
    private List<String> games;
    private List<String> friends;
    private List<String> friendRequests;
    private List<String> sentFriendRequests;
    private List<String> notifications;
    private List<String> achievements;

    public void updateEloRating(int newEloRating) {
        this.eloRating = newEloRating;
    }

    public void addAchievement(String achievement) {
        this.achievements.add(achievement);
    }

    public void setUsername(String admin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPassword(String encode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRoles(List<String> singletonList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}