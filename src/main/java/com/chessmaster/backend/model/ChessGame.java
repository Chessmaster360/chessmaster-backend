package com.chessmaster.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
public class ChessGame {

    @Id
    private String id;

    private String pgn;
    private List<Move> moves;

    // Constructor vacío
    public ChessGame() {}

    // Constructor con parámetros
    public ChessGame(String pgn, List<Move> moves) {
        this.pgn = pgn;
        this.moves = moves;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPgn() {
        return pgn;
    }

    public void setPgn(String pgn) {
        this.pgn = pgn;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
}
