package com.chessmaster.backend.controller;

import com.chessmaster.backend.model.ChessGame;
import com.chessmaster.backend.repository.ChessGameRepository;
import com.chessmaster.backend.service.ChessGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*; 

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
}

