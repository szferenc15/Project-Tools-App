package app.sport_mates.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import app.sport_mates.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Iterable<User> findAll(); 
}