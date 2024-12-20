import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class RegistrationController {

    private final Map<String, String> users = new HashMap<>();
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

        if (users.containsKey(username)) {
            return "El usuario ya existe";
        }

        users.put(username, passwordEncoder.encode(password));
        return "Usuario registrado exitosamente";
    }
}
