package app.sportmates_backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import app.sportmates_backend.model.Event;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import javax.transaction.Transactional;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer>{
    Optional<Event> findById(Long id);
    Optional<Event> findByName(String name);
    Optional<Event> findByLocale(String locale);
    Optional<Event> findByPrice(short price);
    Optional<Event> findByDateOfEvent(Date event);
    Optional<Event> findByStart(Time start);
    Optional<Event> findByFinish(Time finish);
    Optional<Event> findByHeadcount(short headCount);
    Optional<Event> findByAudience(String audience);

    Iterable<Event> findAll(); 

    @Transactional
    Long deleteById(Long id);
}