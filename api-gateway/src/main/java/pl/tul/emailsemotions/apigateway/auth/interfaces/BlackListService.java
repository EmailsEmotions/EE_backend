package pl.tul.emailsemotions.apigateway.auth.interfaces;

import pl.tul.emailsemotions.apigateway.auth.rest.response.BlackListedToken;

import java.util.List;

public interface BlackListService {

    void addTokenFromHeader(String header);

    List<BlackListedToken> findAll();

    void deleteExpiredTokens();

    boolean checkIfAlreadyExists(String token);
}
