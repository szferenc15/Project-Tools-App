package app.sportmates_backend.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.SportCategory;
import app.sportmates_backend.model.User;

/**
 * Interface az eseményt megvalósító osztályok számára.
 * @author szendrei
 * @author polozgai
 *
 */
@Repository
public interface EventRepository extends CrudRepository<Event,Integer>{
	
	/**
	 * Visszaadja az eseményt az azonosítója alapján.
	 * @param id Esemény azonosítója.
	 * @return Esemény adathalmaz.
	 */
    Optional<Event> findById(long id);
    
    /**
     * Visszaadja az esemény nevét.
     * @param name Esemény neve.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByName(String name);
    
    /**
     * Visszaadja az esemény helyszínét.
     * @param locale Esemény helyszíne.
     * @return Esemény adathalmaz.
     */    
    Optional<Event> findByLocale(String locale);
    
    /**
     * Visszaadja az esemény költségét.
     * @param price Esemény költsége.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByPrice(short price);
    
    /**
     * Visszaadja az esemény időpontját.
     * @param event Esemény időpontja.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByDateOfEvent(Date event);
    
    /**
     * Visszaadja az esemény kezdetét.
     * @param start Esemény kezdete.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByStart(Time start);
    
    /**
     * Visszaadja az esemény végét.
     * @param finish Esemény vége.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByFinish(Time finish);
    
    /**
     * Visszaadja az eseményben résztvevők létszámát.
     * @param headCount Eseményben résztvevők létszáma.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByHeadcount(short headCount);
    
    /**
     * Visszaadja az esemény célközönségét.
     * @param audience Esemény célközönsége.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByAudience(String audience);
    
    /**
     * Visszaadja az esemény szervezőjét.
     * @param organizer Esemény szervezője.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByOrganizer(User organizer);
    
    /**
     * Visszaadja az esemény sport kategóriáját.
     * @param category Esemény sport kategóriája.
     * @return Esemény adathalmaz.
     */
    Optional<Event> findByCategory(SportCategory category);

    /**
     * Megkeresi az összes eseményt.
     */
    Iterable<Event> findAll(); 

    /**
     * Esemény törlése az azonosítója alapján.
     * @param id Esemény azonosítója.
     * @return Visszatérési érték.
     */
    @Transactional
    long deleteById(long id);
}