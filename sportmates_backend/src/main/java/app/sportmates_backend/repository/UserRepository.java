package app.sportmates_backend.repository;

import java.sql.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.sportmates_backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
    Optional<User> findById(long id);
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
    long deleteByUsername(String username);
}