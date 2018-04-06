package app.sportmates_backend.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.EventSportCategory;
import app.sportmates_backend.model.SportCategory;

@Repository
public interface EventSportCategoryRepository extends CrudRepository<EventSportCategory,Integer>{
    Optional<EventSportCategory> findById(Long id);
    Optional<EventSportCategory> findBySportCategory(SportCategory sportCategory);
    Optional<EventSportCategory> findByEventId(Event eventId);

    Iterable<EventSportCategory> findAll(); 

    @Transactional
    Long deleteById(Long id);
}