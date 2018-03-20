package app.sport_mates.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import app.sport_mates.repository.EventRepository;
import app.sport_mates.class_interface.NewEvent;
import app.sport_mates.model.Event;

import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    
    public Iterable<Event> all() {
        return eventRepository.findAll();
    }

    @Transactional
    public Optional<Event> addNewEvent(NewEvent newEvent){
        Event event = new Event();

        event.setName(newEvent.getName());
        event.setLocale(newEvent.getLocale());
        event.setPrice(newEvent.getPrice());
        event.setDateOfEvent(newEvent.getDateOfEvent());
        event.setStart(newEvent.getStart());
        event.setFinish(newEvent.getFinish());
        event.setHeadcount(newEvent.getHeadcount());
        event.setAudience(newEvent.getAudience());
        event.setDescription(newEvent.getDescription());
        
        eventRepository.save(event);

        return Optional.of(event);
    }

    public Long delete(Long id) {
        return eventRepository.deleteById(id);
    }
}