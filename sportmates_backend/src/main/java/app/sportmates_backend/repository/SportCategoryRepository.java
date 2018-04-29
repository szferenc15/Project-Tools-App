package app.sportmates_backend.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.sportmates_backend.model.SportCategory;

@Repository
public interface SportCategoryRepository extends CrudRepository<SportCategory,Integer>{
    Optional<SportCategory> findById(long id);
    Optional<SportCategory> findByCategory(String category);

    Iterable<SportCategory> findAll();

    @Transactional
    long deleteByCategory(String category);
}