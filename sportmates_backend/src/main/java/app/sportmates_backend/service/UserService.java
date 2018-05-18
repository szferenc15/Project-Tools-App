package app.sportmates_backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.sportmates_backend.class_interface.AuthUser;
import app.sportmates_backend.class_interface.NewUser;
import app.sportmates_backend.class_interface.UserInfo;
import app.sportmates_backend.model.User;
import app.sportmates_backend.repository.UserRepository;

/**
 * Ez az osztály végzi a felhasználóval kapcsolatos szolgáltatások kezelését.
 * @author szendrei
 * @author polozgai
 *
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Visszaadja az összes felhasználóval kapcsolatos információt.
     * @return Felhasználók adatai. 
     */
    public List<UserInfo> all() {
        Iterable<User> users = userRepository.findAll();

        List<UserInfo> userInfos = new ArrayList<>();

        Iterator<User> userIterator = users.iterator();
        while(userIterator.hasNext()){
            userInfos.add(new UserInfo(userIterator.next()));
        }

        return userInfos;
    }

    /**
     * Visszaadja a felhasználót az azonosítója alapján.
     * @param id Felhasználó azonosítója.
     * @return Felhasználó
     */
    public Optional<User> byId(long id) {
        return userRepository.findById(id);
    }

    /**
     * Visszadja a felhasználó adatait az felhasználó neve alapján.
     * @param username Felhasználó felhasználóneve.
     * @return Felhasználó adatai.
     */
    public Optional<UserInfo> byUsername(String username) {
        UserInfo userInfo = new UserInfo(userRepository.findByUsername(username).get());
        return Optional.of(userInfo);
    }

    /**
     * Új felhasználó regisztrálása a rendszerbe.
     * @param newUser Újfelhasználó
     * @return Újfelhasználó hozzáadása.
     */
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

    /**
     * Felhasználó bejelentkeztetését végzi.
     * @param authUser Felhasználó azonosító objektum
     * @return Felhasználó összes adata.
     */
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