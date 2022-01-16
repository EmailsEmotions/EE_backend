package pl.tul.emailsemotions.apigateway.auth.converters;

import pl.tul.emailsemotions.api.User;
import pl.tul.emailsemotions.apigateway.auth.impl.UserDetailsImpl;

public class UserDetailsImplToUserConverter {

    public static User convert(UserDetailsImpl userDetails) {
        return User.builder()
            .id(userDetails.getId())
            .username(userDetails.getUsername())
            .email(userDetails.getEmail())
            .active(userDetails.isEnabled())
            .confirmed(userDetails.getConfirmed())
            .accountType(userDetails.getAccountType())
            .build();
    }
}
