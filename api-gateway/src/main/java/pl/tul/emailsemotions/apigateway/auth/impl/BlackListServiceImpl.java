package pl.tul.emailsemotions.apigateway.auth.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.apigateway.auth.interfaces.BlackListService;
import pl.tul.emailsemotions.apigateway.auth.interfaces.TokenExpirationService;
import pl.tul.emailsemotions.apigateway.auth.models.BlackListedTokenData;
import pl.tul.emailsemotions.apigateway.auth.repos.BlackListedTokenRepository;
import pl.tul.emailsemotions.apigateway.auth.rest.response.BlackListedToken;
import pl.tul.emailsemotions.shared.exceptions.IllegalActionException;
import pl.tul.emailsemotions.shared.exceptions.WrongArgumentException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service("blackListService")
public class BlackListServiceImpl implements BlackListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlackListServiceImpl.class);

    private final BlackListedTokenRepository blackListedTokenRepository;
    private final TokenExpirationService tokenExpirationService;

    @Autowired
    public BlackListServiceImpl(BlackListedTokenRepository blackListedTokenRepository,
                                TokenExpirationService tokenExpirationService) {
        this.blackListedTokenRepository = blackListedTokenRepository;
        this.tokenExpirationService = tokenExpirationService;
    }

    @Override
    public void addTokenFromHeader(String token) {
        if (token == null) {
            throw new WrongArgumentException("Bad token format!");
        }
        if (checkIfAlreadyExists(token)) {
            throw new IllegalActionException("Token has been already blacklisted");
        }
        BlackListedTokenData tokenToSave = BlackListedTokenData.builder()
                .token(token)
                .expirationTime(tokenExpirationService.getTokenExpirationTime(token))
                .build();
        blackListedTokenRepository.save(tokenToSave);
    }

    @Override
    @Cacheable("blackListedTokens")
    public List<BlackListedToken> findAll() {
        LOGGER.info("Reading blacklisted tokens from database");
        return blackListedTokenRepository.findAll()
                .stream()
                .map(BlackListedTokenData::toBlackListedToken)
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(cron = "${jwt.deleteExpiredTokens.cron}")
    public void deleteExpiredTokens() {
        List<BlackListedTokenData> expiredTokens = blackListedTokenRepository.findAll()
                .stream()
                .filter(t -> !t.getExpirationTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
        LOGGER.info("Deleting expired blacklisted tokens, amount: " + expiredTokens.size());
        blackListedTokenRepository.deleteAll(expiredTokens);
    }

    @Override
    public boolean checkIfAlreadyExists(String token) {
        return findAll().stream().anyMatch(t -> t.getToken().equals(token));
    }
}
