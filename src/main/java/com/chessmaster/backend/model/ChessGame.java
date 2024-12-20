package com.chessmaster.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.chessmaster.backend.model.Move;  // Asegúrate de que este import esté correcto
import java.util.Date;
import java.util.List;

@Document(collection = "games")
public class ChessGame {

    @Id
    private String id;
    private String playerOne;
    private String playerTwo;
    private String status; // e.g., "IN_PROGRESS", "FINISHED"
    private String gameResult;
    private Date date;
    private String opening;
    private String pgn;
    private List<Move> moves;


    // Constructor con parámetros
    public ChessGame(String pgn, List<Move> moves) {
        this.pgn = pgn;
        this.moves = moves;
    }

    void updateResult(String result) {
        this.gameResult = result;
    }

}
