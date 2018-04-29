package app.sportmates_backend.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.SportCategory;
import app.sportmates_backend.model.User;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer>{
    Optional<Event> findById(long id);
    Optional<Event> findByName(String name);
    Optional<Event> findByLocale(String locale);
    Optional<Event> findByPrice(short price);
    Optional<Event> findByDateOfEvent(Date event);
    Optional<Event> findByStart(Time start);
    Optional<Event> findByFinish(Time finish);
    Optional<Event> findByHeadcount(short headCount);
    Optional<Event> findByAudience(String audience);
    Optional<Event> findByOrganizer(User organizer);
    Optional<Event> findByCategory(SportCategory category);

    Iterable<Event> findAll(); 

    @Transactional
    long deleteById(long id);
}