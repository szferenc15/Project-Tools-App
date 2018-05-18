package app.sportmates_backend.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.sportmates_backend.class_interface.NewSportCategory;
import app.sportmates_backend.model.SportCategory;
import app.sportmates_backend.repository.SportCategoryRepository;

/**
 * Ez az osztály végzi a sportkategóriával kapcsolatosz szolgáltatáso kezelését.
 * @author szendrei
 * @author polozgai
 *
 */
@Service
public class SportCategoryService {

    @Autowired
    private SportCategoryRepository sportCategoryRepository;

    /**
     * Visszadja az összes sport kategóriát.
     * @return Összes sport kategória.
     */
    public Iterable<SportCategory> all() {
        return sportCategoryRepository.findAll();
    }

    /**
     * Visszaadja a sport kategóriát a neve alapján.
     * @param category Kategória neve.
     * @return Sport kategória.
     */
    public Optional<SportCategory> byCategory(String category) {
        return sportCategoryRepository.findByCategory(category);
    }

    /**
     * Új sport kategória felvétele.
     * @param newSportCategory Sport kategória.
     * @return Kategória felvétele.
     */
    @Transactional
    public Optional<SportCategory> addNewCategory(NewSportCategory newSportCategory) {
        SportCategory sportCategory = new SportCategory();

        sportCategory.setCategory(sportCategory.getCategory());
        
        sportCategoryRepository.save(sportCategory);

        return Optional.of(sportCategory);
    }

    /**
     * Sport kategória törlése a neve alapján.
     * @param category Kategória neve.
     * @return Kategória törlése.
     */
    public long delete(String category) {
        return sportCategoryRepository.deleteByCategory(category);
    }
}