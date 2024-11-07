package demo.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.util.Map;
/**
 * Imports the ConcurrentHashMap class from the java.util.concurrent package.
 * ConcurrentHashMap is a thread-safe implementation of the Map interface, which allows for concurrent access and modification of the map.
 * This import is likely used in the SessionManager class to store session information in a thread-safe manner.
 */
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final Map<String, String> sessionStore = new ConcurrentHashMap<>();

    public void addSession(String username, String token) {
        sessionStore.put(username, token);
    }

    public boolean isSessionValid(String username, String token) {
        return token.equals(sessionStore.get(username));
    }

    public void removeSession(String username) {
        sessionStore.remove(username);
    }
}

