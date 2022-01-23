package pl.tul.emailsemotions.apigateway.auth.interfaces;

import org.apache.commons.lang3.tuple.Pair;
import pl.tul.emailsemotions.shared.api.User;

public interface AuthService {

    Pair<User, String> authenticate(String username, String password);

}
