package com.chessmaster.backend.controller;

import com.chessmaster.backend.model.ChessGame;
import com.chessmaster.backend.repository.ChessGameRepository;
import com.chessmaster.backend.service.ChessGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*; 
import com.chessmaster.backend.model.Move;
import com.chessmaster.backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class ChessGameController {

    @Autowired
    private ChessGameService chessGameService;

    // Endpoint para crear un juego
    @PostMapping("/upload-pgn")
    public ChessGame uploadGame(@RequestBody ChessGame game) {
        return chessGameService.saveGame(game);
    }

    // Endpoint to get the current state of the board
    @GetMapping("/{id}/board")
    public ChessGame getBoardState(@PathVariable String id) {
        Optional<ChessGame> game = chessGameService.getGameById(id);
        return game.orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + id));
    }

    // Endpoint to advance to the next move
    @PostMapping("/{id}/next-move")
    public Move advanceToNextMove(@PathVariable String id) {
        Optional<ChessGame> game = chessGameService.getGameById(id);
        if (game.isPresent()) {
            // Logic to advance to the next move
            // This is a placeholder, you need to implement the actual logic
            Move nextMove = new Move();
            // Update the game state with the next move
            return nextMove;
        } else {
            throw new ResourceNotFoundException("Game not found with id " + id);
        }
    }
     // Endpoint to evaluate a move
     @PostMapping("/{id}/evaluate-move")
     public String evaluateMove(@PathVariable String id, @RequestBody Move move) {
         Optional<ChessGame> game = chessGameService.getGameById(id);
         if (game.isPresent()) {
             // Logic to evaluate the move
             // This is a placeholder, you need to implement the actual logic
             String evaluation = "Good move";
             return evaluation;
         } else {
             throw new ResourceNotFoundException("Game not found with id " + id);
         }
     }
}

