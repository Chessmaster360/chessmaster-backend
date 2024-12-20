import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChessComService {

    private static final String CHESS_COM_API_URL = "https://api.chess.com/pub/player/{username}/games";

    @Autowired
    private RestTemplate restTemplate;

    public String obtenerPartidas(String username) {
        String url = CHESS_COM_API_URL;
        return restTemplate.getForObject(url, String.class, username);
    }
}
