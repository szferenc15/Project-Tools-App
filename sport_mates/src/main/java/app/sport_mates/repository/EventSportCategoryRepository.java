package app.sport_mates.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import app.sport_mates.model.EventSportCategory;

@Repository
public interface EventSportCategoryRepository extends CrudRepository<EventSportCategory,Integer>{
    Iterable<EventSportCategory> findAll(); 
}