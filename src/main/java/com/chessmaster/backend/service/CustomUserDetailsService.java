import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, String> users = new HashMap<>();

    public CustomUserDetailsService() {
        // Crear usuarios en memoria
        users.put("admin", "$2a$10$7Qpe9t0nIy2IjAaX.XZ1jO2Jc7hxokL4MI2aOQYPVYByXkKxL.GI2"); // Contraseña: admin123
        users.put("user", "$2a$10$PucEeM9P/AgmEr5lIHDQfujmYCeF8wxZ7MJfOIMlweIM8Q/4rP8XS");  // Contraseña: user123
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return User.withUsername(username)
                .password(users.get(username))
                .roles("USER")
                .build();
    }
}
