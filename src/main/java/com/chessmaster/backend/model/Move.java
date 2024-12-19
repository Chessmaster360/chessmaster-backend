package com.chessmaster.model;

public class Move {
    private String move;
    private String evaluation;

    // Constructor vacío
    public Move() {}

    // Constructor con parámetros
    public Move(String move, String evaluation) {
        this.move = move;
        this.evaluation = evaluation;
    }

    // Getters y setters
    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
