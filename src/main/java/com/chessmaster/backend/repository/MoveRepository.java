package com.chessmaster.backend.repository;

import com.chessmaster.backend.model.Move;  
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoveRepository extends MongoRepository<Move, String> {
    // Métodos adicionales personalizados si es necesario

}
