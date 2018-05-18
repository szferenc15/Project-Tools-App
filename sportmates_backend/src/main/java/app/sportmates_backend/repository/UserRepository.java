package app.sportmates_backend.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;
import app.sportmates_backend.model.User;

import java.sql.Date;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface a felhasználót megvalósító osztály számára.
 * @author szendrei
 * @author polozgai
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
	
	/**
	 * Visszadja a felhasználót az azonosítója alapján.
	 * @param id Felhasználó azonosítója.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findById(long id);
	
	/**
	 * Visszadja a felhasználó keresztnevét.
	 * @param firstName Felhasználó keresztneve.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findByFirstName(String firstName);
	
	/**
	 * Visszadja a felhasználó vezetéknevét.
	 * @param lastName Felhasználó vezetékneve.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findByLastName(String lastName);
	
	/**
	 * Visszadja a felhasználó felhasználónevét.
	 * @param username Felhasználó felhasználóneve.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findByUsername(String username);
	
	/**
	 * Visszadja a felhasználó email-ét.
	 * @param email Felhasználó email-e.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findByEmail(String email);
	
	/**
	 * Visszadja a felhasználó telefonszámát.
	 * @param phoneNumber Felhasználó telefonszáma.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findByPhoneNumber(String phoneNumber);
	
	/**
	 * Visszadja a felhasználó városát.
	 * @param city Felhasználó városa.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findByCity(String city);
	
	/**
	 * Visszadja a felhasználó születésnapját.
	 * @param birthDate Felhasználó születésnapja.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findByBirthDate(Date birthDate);
	
	/**
	 * Visszadja a felhasználó nemét.
	 * @param isMale Felhasználó neme.
	 * @return Felhasználó adathalmaz.
	 */
    Optional<User> findByIsMale(boolean isMale);

    /**
     * Visszaadja az összes felhasználót.
     */
    Iterable<User> findAll(); 
    
    /**
     * Törli a felhasználót az azonosítója alapján.
     * @param username felhasználó azonosítója.
     * @return Visszatérési érték.
     */
    @Transactional
    long deleteByUsername(String username);
}