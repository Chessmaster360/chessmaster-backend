package com.chessmaster.backend.controller;

import com.chessmaster.backend.service.ChessComService;
import com.chessmaster.backend.service.ChessGameService;
import com.chessmaster.backend.model.ChessGame;
import com.chessmaster.backend.model.Move;
import com.chessmaster.backend.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChessGameController {

    private final ChessComService chessComService;
    private final ChessGameService chessGameService;

    @Autowired
    public ChessGameController(ChessComService chessComService, ChessGameService chessGameService) {
        this.chessComService = chessComService;
        this.chessGameService = chessGameService;
    }

    // Endpoint para obtener partidas recientes desde una plataforma soportada
    @GetMapping("/games")
    public ResponseEntity<Map<String, Object>> getGames(
            @RequestParam String platform,
            @RequestParam String username) {

        if (!platform.equalsIgnoreCase("Chess.com")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Solo se admite Chess.com en este endpoint"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("games", chessComService.obtenerPartidas(username));

        return ResponseEntity.ok(response);
    }

    // Endpoint para crear un nuevo juego en la base de datos
    @PostMapping("/games")
    public ResponseEntity<ChessGame> createGame(@RequestBody ChessGame game) {
        ChessGame createdGame = chessGameService.createGame(game);
        return ResponseEntity.ok(createdGame);
    }

    // Endpoint para avanzar al siguiente movimiento de un juego existente
    @PostMapping("/games/{id}/next-move")
    public ResponseEntity<Move> advanceToNextMove(@PathVariable String id) {
        ChessGame game = chessGameService.getGameById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + id));

        // Lógica placeholder para obtener el siguiente movimiento
        Move nextMove = chessGameService.calculateNextMove(game);

        // Actualizar el juego con el movimiento calculado
        chessGameService.advanceMove(game, nextMove);
        return ResponseEntity.ok(nextMove);
    }

    // Endpoint para analizar un juego basado en su PGN
    @PostMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeGame(@RequestBody Map<String, String> requestBody) {
        String pgn = requestBody.get("pgn");

        if (pgn == null || pgn.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "El PGN es obligatorio para analizar"));
        }

        // Simulación del análisis (puedes integrar Stockfish aquí)
        Map<String, Object> analysisResult = new HashMap<>();
        analysisResult.put("analysis", "Análisis simulado del PGN");

        return ResponseEntity.ok(analysisResult);
    }

    // Endpoint para obtener el estado actual del tablero de un juego
    @GetMapping("/games/{id}/board")
    public ResponseEntity<ChessGame> getBoardState(@PathVariable String id) {
        ChessGame game = chessGameService.getGameById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + id));
        return ResponseEntity.ok(game);
    }

    // Endpoint para evaluar un movimiento en un juego específico
    @PostMapping("/games/{id}/evaluate-move")
    public ResponseEntity<Map<String, String>> evaluateMove(@PathVariable String id, @RequestBody Move move) {
        ChessGame game = chessGameService.getGameById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found with id " + id));

        // Lógica placeholder para evaluar el movimiento
        String evaluation = chessGameService.evaluateMove(game, move);

        return ResponseEntity.ok(Map.of("evaluation", evaluation));
    }
}