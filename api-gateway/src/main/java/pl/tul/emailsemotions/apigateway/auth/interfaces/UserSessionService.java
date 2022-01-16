package pl.tul.emailsemotions.apigateway.auth.interfaces;

import pl.tul.emailsemotions.api.AccountType;
import pl.tul.emailsemotions.api.User;

public interface UserSessionService {

    User getAuthenticatedUser();

    boolean hasRole(AccountType accountType);
}
