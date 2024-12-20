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
    List<String> games;
    List<String> friends;
    List<String> friendRequests;
    List<String> sentFriendRequests;
    List<String> notifications;
    List<String> achievements;

    void updateEloRating(int newEloRating) {
        this.eloRating = newEloRating;
    }

    void addAchievement(String achievement) {
        this.achievements.add(achievement);
    }


}
