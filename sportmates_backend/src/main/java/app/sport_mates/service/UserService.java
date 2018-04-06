package app.sportmates_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import app.sportmates_backend.repository.UserRepository;
import app.sportmates_backend.class_interface.AuthUser;
import app.sportmates_backend.class_interface.NewUser;
import app.sportmates_backend.model.User;

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

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(newUser.getPassword());
            
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setUsername(newUser.getUsername());
            user.setPassword(hashedPassword);
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<User> optionalUser = userRepository.findByUsername(authUser.getIdentifier());
        if (!optionalUser.isPresent()){
            optionalUser = userRepository.findByEmail(authUser.getIdentifier());
        }
        return optionalUser.filter(user -> passwordEncoder.matches(authUser.getPassword(), user.getPassword()));
    }
   
    public Long delete(String username) {
        return userRepository.deleteByUsername(username);
    }
}