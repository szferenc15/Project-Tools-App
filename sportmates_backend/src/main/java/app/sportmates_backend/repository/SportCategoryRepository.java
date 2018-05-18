package app.sportmates_backend.repository;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.sportmates_backend.model.SportCategory;

/**
 * Interface a sport kategóriát megvalósító osztály számára.
 * @author szendrei
 * @author polozgai
 *
 */
@Repository
public interface SportCategoryRepository extends CrudRepository<SportCategory,Integer>{
	
	/**
	 * Visszaadja a sport kategóriát az azonosítója alapján.
	 * @param id Sport kategória azonosítója.
	 * @return Sport kategória adathalmaz.
	 */
    Optional<SportCategory> findById(long id);
    
    /**
     * Visszaadja a sport kategóriát a neve alapján.
     * @param category Sport kategória neve.
     * @return Sport kategória adathalmaz.
     */
    Optional<SportCategory> findByCategory(String category);

    /**
     * Visszaadja az összes sport kategóriát.
     */
    Iterable<SportCategory> findAll();

    /**
     * sport kategória törlése.
     * @param category Sport kategória neve.
     * @return Visszatérési érték.
     */
    @Transactional
    long deleteByCategory(String category);
}