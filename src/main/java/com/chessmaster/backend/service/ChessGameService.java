package com.chessmaster.backend.service;

import com.chessmaster.backend.model.ChessGame;
import com.chessmaster.backend.repository.ChessGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chessmaster.backend.model.Move;


import java.util.List;
import java.util.Optional;

@Service
public class ChessGameService {

    @Autowired
    private ChessGameRepository chessGameRepository;

    public ChessGame saveGame(ChessGame game) {
        return chessGameRepository.save(game);
    }

    public Optional<ChessGame> getGameById(String id) {
        return chessGameRepository.findById(id);
    }

    public List<ChessGame> getAllGames() {
        return chessGameRepository.findAll();
    }
    public ChessGame createGame(ChessGame game) {
        return chessGameRepository.save(game);
    }
    
    public Move calculateNextMove(ChessGame game) {
        // Implementación lógica para calcular el siguiente movimiento (simulada aquí)
        Move nextMove = new Move();
        nextMove.setFrom("e2");
        nextMove.setTo("e4");
        return nextMove;
    }
    
    public void advanceMove(ChessGame game, Move move) {
        // Actualiza el juego con el movimiento
        game.getMoves().add(move);
        chessGameRepository.save(game);
    }
    
    public String evaluateMove(ChessGame game, Move move) {
        // Implementa una lógica de evaluación simulada para un movimiento
        return "Move is valid and strategic";
    }
    
}
