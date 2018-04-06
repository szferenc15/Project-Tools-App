package app.sportmates_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import app.sportmates_backend.repository.UserRepository;
import app.sportmates_backend.class_interface.AuthUser;
import app.sportmates_backend.class_interface.NewUser;
import app.sportmates_backend.class_interface.UserInfo;
import app.sportmates_backend.model.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserInfo> all() {
        Iterable<User> users = userRepository.findAll();

        List<UserInfo> userInfos = new ArrayList<>();

        Iterator<User> userIterator = users.iterator();
        while(userIterator.hasNext()){
            userInfos.add(new UserInfo(userIterator.next()));
        }

        return userInfos;
    }

    public Optional<UserInfo> byUsername(String username) {
        UserInfo userInfo = new UserInfo(userRepository.findByUsername(username).get());
        return Optional.of(userInfo);
    }

    @Transactional
    public Optional<UserInfo> register(NewUser newUser){
        Optional<User> optionalUser = userRepository.findByUsername(newUser.getUsername());

        if(!optionalUser.isPresent()){
            User user = new User();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(newUser.getPassword());
            
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPictureUrl(newUser.getPictureUrl());
            user.setUsername(newUser.getUsername());
            user.setPassword(hashedPassword);
            user.setEmail(newUser.getEmail());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setCity(newUser.getCity());
            user.setBirthDate(newUser.getBirthDate());
            user.setIsMale(newUser.isMale());
            
            userRepository.save(user);

            UserInfo optionalUserInfo = new UserInfo(user);
            return Optional.of(optionalUserInfo);
        }
        return Optional.empty();
    }

    public Optional<UserInfo> login(AuthUser authUser){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<User> optionalUser = userRepository.findByUsername(authUser.getIdentifier());
        if (!optionalUser.isPresent()){
            optionalUser = userRepository.findByEmail(authUser.getIdentifier());
        }

        optionalUser = optionalUser.filter(user -> passwordEncoder.matches(authUser.getPassword(), user.getPassword()));
        
        UserInfo optionalUserInfo = new UserInfo(optionalUser.get());
        return Optional.of(optionalUserInfo);
    }
   
    public long delete(String username) {
        return userRepository.deleteByUsername(username);
    }
}