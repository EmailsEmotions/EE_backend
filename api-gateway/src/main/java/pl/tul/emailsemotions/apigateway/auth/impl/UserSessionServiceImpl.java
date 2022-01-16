package pl.tul.emailsemotions.apigateway.auth.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.api.AccountType;
import pl.tul.emailsemotions.api.User;
import pl.tul.emailsemotions.apigateway.auth.converters.UserDetailsImplToUserConverter;
import pl.tul.emailsemotions.apigateway.auth.interfaces.UserSessionService;

@Service("userSessionService")
public class UserSessionServiceImpl implements UserSessionService {

    @Override
    public User getAuthenticatedUser() {
        return UserDetailsImplToUserConverter.convert((UserDetailsImpl)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public boolean hasRole(AccountType accountType) {
        User user = getAuthenticatedUser();
        return user.getAccountType().equals(accountType);
    }
}
