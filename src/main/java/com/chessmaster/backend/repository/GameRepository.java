package com.chessmaster.repository;

import com.chessmaster.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
    // Métodos personalizados (si es necesario)
}
