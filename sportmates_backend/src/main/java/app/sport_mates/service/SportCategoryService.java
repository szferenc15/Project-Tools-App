package app.sportmates_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import app.sportmates_backend.repository.SportCategoryRepository;
import app.sportmates_backend.class_interface.NewSportCategory;
import app.sportmates_backend.model.SportCategory;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class SportCategoryService {

    @Autowired
    private SportCategoryRepository sportCategoryRepository;

    public Iterable<SportCategory> all() {
        return sportCategoryRepository.findAll();
    }

    @Transactional
    public Optional<SportCategory> addNewCategory(NewSportCategory newSportCategory) {
        SportCategory sportCategory = new SportCategory();

        sportCategory.setCategory(sportCategory.getCategory());
        
        sportCategoryRepository.save(sportCategory);

        return Optional.of(sportCategory);
    }

    public Long delete(String category) {
        return sportCategoryRepository.deleteByCategory(category);
    }
}