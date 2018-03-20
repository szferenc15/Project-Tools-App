package app.sport_mates.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import app.sport_mates.model.Event;
import app.sport_mates.model.EventSportCategory;
import app.sport_mates.model.SportCategory;

@Repository
public interface EventSportCategoryRepository extends CrudRepository<EventSportCategory,Integer>{
    Optional<EventSportCategory> findById(Long id);
    Optional<EventSportCategory> findBySportCategory(SportCategory sportCategory);
    Optional<EventSportCategory> findByEventId(Event eventId);

    Iterable<EventSportCategory> findAll(); 

    @Transactional
    Long deleteById(Long id);
}