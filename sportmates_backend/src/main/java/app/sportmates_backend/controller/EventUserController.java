package app.sportmates_backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.sportmates_backend.class_interface.NewEventUser;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.User;
import app.sportmates_backend.repository.EventRepository;
import app.sportmates_backend.repository.UserRepository;
import app.sportmates_backend.service.EventService;
import app.sportmates_backend.service.UserService;
import app.sportmates_backend.util.Response;

/**
 * Ez az osztály hangolja össze az esemény és a felhasználó kommunikációját.
 * @author szendrei
 * @author polozgai
 *
 */
@RestController
@RequestMapping("/event_user")
public class EventUserController{
    
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    /**
     * Egy adott eseményre való jelentkezés.
     * @param newEventUser Esemény felhasználója.
     * @return Siker esetén: "EventUser: addition success". Semleges esetben: "EventUser: addition has happened before". Hiba esetén: "EventUser: no event and/or user found with these ids: (Event: eventId, User: userId")".
     */
    @RequestMapping(value= "/signup", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewEventUser newEventUser)
    {
        long eventId = newEventUser.getEventId();
        long userId = newEventUser.getUserId();

        Optional<Event> optionalEvent = eventService.byId(eventId);
        Optional<User> optionalUser = userService.byId(userId);

        if (!optionalEvent.isPresent() && !optionalUser.isPresent()) {
            Response.error("EventUser: no event and/or user found with these ids: (Event: " + eventId + ", User:" + userId + ")");
        } else {
            if (!optionalEvent.get().getUsers().add(optionalUser.get()) 
                || !optionalUser.get().getEvents().add(optionalEvent.get())) {
                return Response.ok("EventUser: addition has happened before");
            }
        }

        userRepository.save(optionalUser.get());
        eventRepository.save(optionalEvent.get());
        
        return Response.ok("EventUser: addition success");
    }

    /**
     * Egy adott eseményre való lejelentkezés.
     * @param newEventUser Esemény felhasználója.
     * @return Siker esetén: "EventUser: deletion success". Semleges esetben: "EventUser: deletion has happened before or addition has never happened before". Hiba esetén: "EventUser: no event and/or user found with these ids: (Event: eventId, User: userId)".
     */
    @RequestMapping(value= "/quit", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestBody NewEventUser newEventUser)
    {
        long eventId = newEventUser.getEventId();
        long userId = newEventUser.getUserId();

        Optional<Event> optionalEvent = eventService.byId(eventId);
        Optional<User> optionalUser = userService.byId(userId);

        if (!optionalEvent.isPresent() && !optionalUser.isPresent()) {
            Response.error("EventUser: no event and/or user found with these ids: (Event: " + eventId + ", User:" + userId + ")");
        } else {
            if (!optionalEvent.get().getUsers().remove(optionalUser.get()) 
                || !optionalUser.get().getEvents().remove(optionalEvent.get())) {
                return Response.ok("EventUser: deletion has happened before or addition has never happened before");
            }
        }

        userRepository.save(optionalUser.get());
        eventRepository.save(optionalEvent.get());

        return Response.ok("EventUser: deletion success");
    }
}