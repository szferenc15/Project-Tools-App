package app.sport_mates.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import app.sport_mates.repository.UserRepository;
import app.sport_mates.class_interface.AuthUser;
import app.sport_mates.class_interface.NewUser;
import app.sport_mates.model.User;

import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> all() {
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> register(NewUser newUser){
        Optional<User> optionalUser = userRepository.findByUsername(newUser.getUsername());

        if(!optionalUser.isPresent()){
            User user = new User();

            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setCity(newUser.getCity());
            user.setBirthDate(newUser.getBirthDate());
            user.setIsMale(newUser.isMale());
            
            userRepository.save(user);

            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<User> login(AuthUser authUser){
        Optional<User> optionalUser = userRepository.findByUsername(authUser.getIdentifier());
        if (!optionalUser.isPresent()){
            optionalUser = userRepository.findByEmail(authUser.getIdentifier());
        }
        return optionalUser.filter(user -> user.getPassword().equals(authUser.getPassword()));
    }
   
    public Long delete(String username) {
        return userRepository.deleteByUsername(username);
    }
}