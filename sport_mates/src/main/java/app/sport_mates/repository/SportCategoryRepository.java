package app.sport_mates.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import app.sport_mates.model.SportCategory;

@Repository
public interface SportCategoryRepository extends CrudRepository<SportCategory,Integer>{
    Iterable<SportCategory> findAll(); 
}