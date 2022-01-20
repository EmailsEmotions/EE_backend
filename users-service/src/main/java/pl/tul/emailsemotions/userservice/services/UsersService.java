package pl.tul.emailsemotions.userservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.shared.api.AccountType;
import pl.tul.emailsemotions.userservice.clients.MailClient;
import pl.tul.emailsemotions.userservice.clients.models.MailObject;
import pl.tul.emailsemotions.userservice.dto.AddUserDTO;
import pl.tul.emailsemotions.userservice.model.User;
import pl.tul.emailsemotions.userservice.model.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UsersService {
    private final UserRepository userRepository;
    private final MailClient mailClient;

    @Autowired()
    private Environment env;

    /**
     * Method responsible for adding user into database
     * @param user User to add
     * @return User save into database
     */
    public User add(AddUserDTO user) {
        User userToSaveInDB = User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .email(user.getEmail())
            .accountType(AccountType.STANDARD)
            .active(true)
            .confirmed(false)
            .build();
        User userdb = userRepository.save(userToSaveInDB);
        log.info("Added user to database: " + userdb);
        String mailEnabled = env.getProperty("mail.enabled");
        log.info(mailEnabled);

        if (Boolean.getBoolean(mailEnabled)) {
            sendActivationMail(userdb);
        }
        return userdb;
    }

    private void sendActivationMail(User userdb) {
        HashMap<String,String> templateMap = new HashMap<>();
        templateMap.put("header","Rejestracja w systemie EE");
        templateMap.put("title","Witamy w systemie EmailsEmotions");
        templateMap.put("description",
            "Aby zarejestrować się w systemie EE użyj następującego linku " +
            "<a href='localhost:8080/api/user/"+ userdb.getId()+"/confirmAccount'>Link</a>"
        );
        MailObject mailObject = MailObject.builder().recipient(userdb.getEmail()).subject("Link aktywacyjny").templateMap(templateMap).build();
        mailClient.send(mailObject);
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

    //TODO: delete this
//    public Boolean login(LoginDTO loginDTO) {
//        User user;
//        String username = loginDTO.getUsername();
//        try {
//            user = this.findByUsername(username);
//        }   catch (EntityNotFoundException ex) {
//            log.error("User with username: " + username + " not found.");
//            throw new EntityNotFoundException();
//        }
//        if(user.getPassword().equals(loginDTO.getPassword())) {
//            log.info("User "+username+" logged in");
//            return true;
//        } else {
//            log.info("User "+username+": wrong password");
//            return false;
//        }
//    }


}
