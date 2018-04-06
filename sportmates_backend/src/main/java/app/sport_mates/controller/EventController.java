package app.sportmates_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.sportmates_backend.class_interface.NewEvent;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.service.EventService;
import app.sportmates_backend.util.Response;
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

        if (!optionalEvent.isPresent()) {
            Response.error("Event: addition failure");
        }

        return Response.ok("Event: addition success");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam Long id)
    {
        Long deletedEvents = eventService.delete(id);

        if (deletedEvents <= 0) {
            Response.error("Event: deletion failure");
        }

        return Response.ok("Event: deletion success");
    }
}