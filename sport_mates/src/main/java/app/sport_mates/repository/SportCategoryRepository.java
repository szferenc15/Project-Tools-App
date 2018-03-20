package app.sport_mates.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import app.sport_mates.model.SportCategory;
import java.util.Optional;
import javax.transaction.Transactional;

@Repository
public interface SportCategoryRepository extends CrudRepository<SportCategory,Integer>{
    Optional<SportCategory> findById(Long id);
    Optional<SportCategory> findByCategory(String category);

    Iterable<SportCategory> findAll();

    @Transactional
    Long deleteByCategory(String category);
}