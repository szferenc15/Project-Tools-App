package app.sportmates_backend.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.sportmates_backend.class_interface.NewEvent;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.SportCategory;
import app.sportmates_backend.model.User;
import app.sportmates_backend.repository.EventRepository;
import app.sportmates_backend.repository.SportCategoryRepository;
import app.sportmates_backend.repository.UserRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportCategoryRepository sportCategoryRepository;
    
    public Iterable<Event> all() {
        return eventRepository.findAll();
    }

    public Optional<Event> byId(long id) {
        return eventRepository.findById(id);
    }

    @Transactional
    public Optional<Event> addNewEvent(NewEvent newEvent){
        Event event = new Event();

        event.setName(newEvent.getName());
        event.setCountry(newEvent.getCountry());
        event.setCity(newEvent.getCity());
        event.setLocale(newEvent.getLocale());
        event.setPrice(newEvent.getPrice());
        event.setDateOfEvent(newEvent.getDateOfEvent());
        event.setStart(newEvent.getStart());
        event.setFinish(newEvent.getFinish());
        event.setHeadcount(newEvent.getHeadcount());
        event.setAudience(newEvent.getAudience());
        event.setDescription(newEvent.getDescription());

        Optional<User> organizer = userRepository.findByUsername(newEvent.getOrganizer());
        event.setOrganizer(organizer.get());

        Optional<SportCategory> category = sportCategoryRepository.findByCategory(newEvent.getCategory());
        event.setCategory(category.get());

        eventRepository.save(event);

        return Optional.of(event);
    }

    public long delete(long id) {
        return eventRepository.deleteById(id);
    }
}