import lombok.Data;
import lombok.ToString;

@Data
public class SignUpRequest {
    private String email;
    private @ToString.Exclude String password;
    private Address address;
}
