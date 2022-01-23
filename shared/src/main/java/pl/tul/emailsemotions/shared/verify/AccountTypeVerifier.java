package pl.tul.emailsemotions.shared.verify;

import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.shared.exceptions.UnauthorizedActionException;

import java.util.Arrays;

public class AccountTypeVerifier {

    public static void verifyUserRole(String userRole, AccountType requiredRole) {
        verifyUserRoleIsNotEmpty(userRole);
        AccountType userType = AccountType.valueOf(userRole);
        if (!userType.equals(requiredRole)) {
            throw new UnauthorizedActionException("bad role");
        }
    }

    public static void verifyUserRoleIsOnList(String userRole, AccountType... rolesWithPermission) {
        verifyUserRoleIsNotEmpty(userRole);
        AccountType userType = AccountType.valueOf(userRole);
        if (!Arrays.asList(rolesWithPermission).contains(userType)) {
            throw new UnauthorizedActionException("bad role");
        }
    }

    private static void verifyUserRoleIsNotEmpty(String userRole) {
        if (userRole == null || userRole.isEmpty()) {
            throw new UnauthorizedActionException("missing user's role");
        }
    }
}
