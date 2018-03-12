package app.sport_mates.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import app.sport_mates.model.Event;
import app.sport_mates.model.EventSportCategory;
import app.sport_mates.model.SportCategory;

@Repository
public interface EventSportCategoryRepository extends CrudRepository<EventSportCategory,Integer>{
    Optional<EventSportCategory> findByCategory(SportCategory category);
    Optional<EventSportCategory> findByEventId(Event eventId);
    Iterable<EventSportCategory> findAll(); 
}