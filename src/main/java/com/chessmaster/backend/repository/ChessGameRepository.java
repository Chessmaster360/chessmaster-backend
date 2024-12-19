package com.chessmaster.repository;

import com.chessmaster.model.ChessGame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChessGameRepository extends MongoRepository<ChessGame, String> {
    // Métodos adicionales personalizados si es necesario
}
// Compare this snippet from chessmaster-backend/src/main/java/com/chessmaster/backend/service/ChessGameService.java:
// package com.chessmaster.service;
// 
// import com.chessmaster.model.ChessGame;
// import com.chessmaster.repository.ChessGameRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import java.util.List;
//
// @Service
// public class ChessGameService {
//
//     @Autowired
//     private ChessGameRepository chessGameRepository;
//
//     public List<ChessGame> getAllChessGames() {
//         return chessGameRepository.findAll();
//     }
//
//     public ChessGame createChessGame(ChessGame chessGame) {
//         return chessGameRepository.save(chessGame);
//     }
// }
