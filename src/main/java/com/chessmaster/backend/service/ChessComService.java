package com.chessmaster.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChessComService {

    private static final String CHESS_COM_API_URL = "https://api.chess.com/pub/player/{username}/games/archives";

    @Autowired
    private RestTemplate restTemplate;

    public List<JSONObject> obtenerPartidas(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser vacío");
        }

        try {
            // Obtener lista de archivos mensuales
            String archivesResponse = restTemplate.getForObject(CHESS_COM_API_URL, String.class, username);
            JSONObject archivesJson = new JSONObject(archivesResponse);
            JSONArray archives = archivesJson.getJSONArray("archives");

            // Obtener las partidas del último mes
            String lastMonthUrl = archives.getString(archives.length() - 1);
            String gamesResponse = restTemplate.getForObject(lastMonthUrl, String.class);
            JSONObject gamesJson = new JSONObject(gamesResponse);
            JSONArray games = gamesJson.getJSONArray("games");

            // Filtrar y procesar partidas
            List<JSONObject> gameList = new ArrayList<>();
            for (int i = 0; i < games.length(); i++) {
                JSONObject game = games.getJSONObject(i);
                gameList.add(new JSONObject()
                        .put("white", game.getJSONObject("white"))
                        .put("black", game.getJSONObject("black"))
                        .put("url", game.getString("url"))
                        .put("pgn", game.optString("pgn", "")));
            }

            return gameList;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las partidas para el usuario: " + username, e);
        }
    }
}
