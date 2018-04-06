package app.sportmates_backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import app.sportmates_backend.model.SportCategory;
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