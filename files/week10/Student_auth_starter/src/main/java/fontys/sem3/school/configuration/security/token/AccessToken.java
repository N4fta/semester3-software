package fontys.sem3.school.configuration.security.token;

import java.util.Set;

// TODO: Discuss if we want to keep this or just have the the actual implementation here
public interface AccessToken {
    String getSubject();
    Set<String> getRoles();
    Long getUserId();
}
