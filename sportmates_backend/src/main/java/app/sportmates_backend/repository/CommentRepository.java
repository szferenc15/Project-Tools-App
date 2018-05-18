package app.sportmates_backend.repository;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.sportmates_backend.model.Comment;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.User;
/**
 * Interface a kommentet megvalósító osztályok számára.
 * @author szendrei
 * @author polozgai
 *
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment,Integer>{
	
    /**
     * Visszaadja a kommentet az azonosítója alapján.
     * @param id Komment azonosítója.
     * @return Komment.
     */
	Optional<Comment> findById(long id);
	
	/**
	 * Megkeresi a kommentet az esemény azonosítója alapján.
	 * @param eventId Esemény azonosítója.
	 * @return Komment adatfolyam.
	 */
    Iterable<Comment> findByEventId(Event eventId);
    
    /**
     * Megkeresi a kommentet a felhasználó azonosítója alapján.
     * @param userId Felhasználó azonosítója.
     * @return Komment adatfolyam.
     */
    Iterable<Comment> findByUserId(User userId);

    /**
     * Megkeresi az összes kommentet.
     */
    Iterable<Comment> findAll(); 

    /**
     * Komment törlése az azonostója alapján.
     * @param id Komment azonosítója.
     * @return Visszatérési érték.
     */
    @Transactional
    long deleteById(long id);
}