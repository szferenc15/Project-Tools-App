package app.sport_mates.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import app.sport_mates.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event,Integer>{
    Iterable<Event> findAll(); 
}