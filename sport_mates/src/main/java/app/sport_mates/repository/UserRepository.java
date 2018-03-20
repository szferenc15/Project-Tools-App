package app.sport_mates.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import app.sport_mates.model.User;

import java.sql.Date;
import java.util.Optional;
import javax.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
    Optional<User> findById(Long id);
    Optional<User> findByFirstName(String firstName);
    Optional<User> findByLastName(String lastName);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByCity(String city);
    Optional<User> findByBirthDate(Date birthDate);
    Optional<User> findByIsMale(boolean isMale);

    Iterable<User> findAll(); 
    
    @Transactional
    Long deleteByUsername(String username);
}