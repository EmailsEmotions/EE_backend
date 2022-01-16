package pl.tul.emailsemotions.apigateway.auth.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.apigateway.auth.repos.BasicUserRepository;
import pl.tul.emailsemotions.exceptions.ObjectNotFoundException;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BasicUserRepository userRepository;

    public UserDetailsServiceImpl(BasicUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) {
        return new UserDetailsImpl(userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found")));
    }

}
