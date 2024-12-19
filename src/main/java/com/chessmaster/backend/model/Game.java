package com.chessmaster.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {

    @Id
    private String id;
    private String playerOne;
    private String playerTwo;
    private String status; // e.g., "IN_PROGRESS", "FINISHED"

    // Getters y setters
}
