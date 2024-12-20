import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChessComController {

    @Autowired
    private ChessComService chessComService;

    @GetMapping("/api/chesscom/{username}/games")
    public String obtenerPartidas(@PathVariable String username) {
        return chessComService.obtenerPartidas(username);
    }
}
