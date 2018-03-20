package app.sport_mates.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.sport_mates.class_interface.NewEvent;
import app.sport_mates.model.Event;
import app.sport_mates.service.EventService;
import app.sport_mates.util.Response;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController{
    
    @Autowired
    private EventService eventService;

    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<Iterable<Event>> getEvents()
    {
        Iterable<Event> events = eventService.all();
        return Response.ok(events);
    }

    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewEvent newEvent)
    {
        Optional<Event> optionalEvent = eventService.addNewEvent(newEvent);
                                        
        return optionalEvent.isPresent() ? 
                        Response.ok("Event: addition success") : 
                        Response.error("Event: addition failure");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam Long id)
    {
        Long deletedEvents = eventService.delete(id);

        return deletedEvents > 0 ? 
                    Response.ok("Event: deletion success") : 
                    Response.error("Event: deletion failure");
    }
}