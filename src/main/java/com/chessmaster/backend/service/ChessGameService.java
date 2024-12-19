package com.chessmaster.backend.service;

import com.chessmaster.backend.model.ChessGame;
import com.chessmaster.backend.repository.ChessGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
