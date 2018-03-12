package app.sport_mates.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import app.sport_mates.repository.EventRepository;
import app.sport_mates.model.Event;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

@Service
public class EventService {

    @Autowired()
    private EventRepository eventRepository;

    public Optional<Event> addNewEvent(String name,
                                      String locale,
                                      short price,
                                      Date dateOfEvent,
                                      Time start,
                                      Time finish,
                                      short headcount,
                                      String audience,
                                      String description){
        Event event = new Event();

        event.setName(name);
        event.setLocale(locale);
        event.setPrice(price);
        event.setDateOfEvent(dateOfEvent);
        event.setStart(start);
        event.setFinish(finish);
        event.setHeadcount(headcount);
        event.setAudience(audience);
        event.setDescription(description);
        
        eventRepository.save(event);

        return Optional.of(event);
    }
}