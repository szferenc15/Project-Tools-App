package app.sportmates_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import app.sportmates_backend.class_interface.NewEventSportCategory;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.EventSportCategory;
import app.sportmates_backend.model.SportCategory;
import app.sportmates_backend.repository.EventRepository;
import app.sportmates_backend.repository.EventSportCategoryRepository;
import app.sportmates_backend.repository.SportCategoryRepository;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class EventSportCategoryService {

    @Autowired
    private EventSportCategoryRepository eventSportCategoryRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SportCategoryRepository sportCategoryRepository;

    public Iterable<EventSportCategory> all() {
        return eventSportCategoryRepository.findAll();
    }

    @Transactional
    public Optional<EventSportCategory> addNewEventSportCategory(NewEventSportCategory newEventSportCategory) {
        EventSportCategory eventSportCategory = new EventSportCategory();

        Optional<Event> eventId = eventRepository.findById(newEventSportCategory.getEventId());
        Optional<SportCategory> category = sportCategoryRepository.findByCategory(newEventSportCategory.getCategory());

        if (eventId.isPresent() && category.isPresent()) {
            eventSportCategory.setEventId(eventId.get());
            eventSportCategory.setSportCategory(category.get());
            
            eventSportCategoryRepository.save(eventSportCategory);

            return Optional.of(eventSportCategory);
        }

        return Optional.empty();
    }

    public Long delete(Long id) {
        return eventSportCategoryRepository.deleteById(id);
    }
}