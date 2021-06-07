package pl.tul.emailsemotions.userservice;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.userservice.model.AccountType;
import pl.tul.emailsemotions.userservice.model.User;
import pl.tul.emailsemotions.userservice.model.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User add(User user) {
        log.info("Adding user to database: " + user.toString());
        return userRepository.save(user);
    }

    public List<User> getAll() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    public User get(Long id) throws EntityNotFoundException {
        if (userRepository.findById(id).isPresent()) {
            log.info("User with id: " + id + " found");
            return userRepository.findById(id).get();
        }
        log.error("User with id: " + id + " not found");
        throw new EntityNotFoundException();
    }

    public User findByUsername(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            log.info("User with username: " + username + " found");
            return userRepository.findByUsername(username).get();
        }
        log.error("User with username: " + username + " not found");
        throw new EntityNotFoundException();
    }

    public Boolean changeActiveStatus(Long id, Boolean status) {
        try {
            User user = this.get(id);
            user.setActive(status);
            userRepository.save(user);
            log.info("Changed active status for userid: "+id.toString()+" to: "+ status.toString());
            return true;
        } catch (EntityNotFoundException ex) {
            log.error("User with id: " + id + " not found. Cannot change active status");
            throw new EntityNotFoundException();
        }
    }

    public Boolean changeConfirmedStatus(Long id, Boolean status) {
        try {
            User user = this.get(id);
            user.setConfirmed(status);
            userRepository.save(user);
            log.info("Changed confirmed status for userid: "+id.toString()+" to: "+ status.toString());
            return true;
        } catch (EntityNotFoundException ex) {
            log.error("User with id: " + id + " not found. Cannot change confirmed status");
            throw new EntityNotFoundException();
        }
    }

    public Boolean changeAccountType(Long id, AccountType accountType) {
        try {
            User user = this.get(id);
            user.setAccountType(accountType);
            userRepository.save(user);
            log.info("Changed account type for userid: "+id.toString()+" to: "+ accountType.toString());
            return true;
        } catch (EntityNotFoundException ex) {
            log.error("User with id: " + id + " not found. Cannot change account type");
            throw new EntityNotFoundException();
        }
    }

}
