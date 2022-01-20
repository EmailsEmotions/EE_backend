package pl.tul.emailsemotions.apigateway.auth.impl;

import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.shared.api.User;
import pl.tul.emailsemotions.apigateway.auth.interfaces.BasicUserService;
import pl.tul.emailsemotions.apigateway.auth.models.UserData;
import pl.tul.emailsemotions.apigateway.auth.repos.BasicUserRepository;
import pl.tul.emailsemotions.shared.exceptions.ObjectNotFoundException;

@Service
public class BasicUserServiceImpl implements BasicUserService {

    private final BasicUserRepository userRepository;

    public BasicUserServiceImpl(BasicUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUsername(String username) {
        return findUserDataByUsername(username).toUser();
    }

    @Override
    public UserData findUserDataByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }
}
