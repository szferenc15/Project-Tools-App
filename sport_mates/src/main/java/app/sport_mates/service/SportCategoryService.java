package app.sport_mates.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import app.sport_mates.repository.SportCategoryRepository;
import app.sport_mates.class_interface.NewSportCategory;
import app.sport_mates.model.SportCategory;
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