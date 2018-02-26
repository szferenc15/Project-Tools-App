package app.sport_mates.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import app.sport_mates.repository.UserRepository;
import app.sport_mates.model.User;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired(required = true)
    private UserRepository userRepository;

    public Optional<User> login(String identifier, String password){
        Optional<User> optionalUser = userRepository.findByUsername(identifier);
        if (!optionalUser.isPresent()){
            optionalUser = userRepository.findByEmail(identifier);
        }
        return optionalUser.filter(user -> user.getPassword().equals(password));
    }

    public Optional<User> register(String firstName, 
                                   String lastName, 
                                   String username, 
                                   String password, 
                                   String email, 
                                   String phoneNumber,
                                   String city,
                                   Date birthDate,
                                   boolean isMale){
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(!optionalUser.isPresent()){
            User user = new User();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setCity(city);
            user.setBirthDate(birthDate);
            user.setIsMale(isMale);
            
            userRepository.save(user);

            return Optional.of(user);
        }
        return Optional.empty();
    }
}