package pl.tul.emailsemotions.apigateway.auth.interfaces;

import pl.tul.emailsemotions.shared.api.User;
import pl.tul.emailsemotions.apigateway.auth.models.UserData;

public interface BasicUserService {
    User findUserByUsername(String username);

    UserData findUserDataByUsername(String username);
}
