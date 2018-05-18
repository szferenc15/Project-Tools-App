package app.sportmates_backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.sportmates_backend.class_interface.NewEvent;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.service.EventService;
import app.sportmates_backend.util.Response;

/**
 * Ez az osztály az végzi az eseményekkel kapcsolatos műveletek kezelését.
 * @author szendrei
 *@author polozgai
 */
@RestController
@RequestMapping("/event")
public class EventController{
    
    @Autowired
    private EventService eventService;

    /**
     * Az összes esemény kilistázása.
     * @return Összes esemény listája.
     */
    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<Iterable<Event>> getEvents()
    {
        Iterable<Event> events = eventService.all();
        return Response.ok(events);
    }

    /**
     * Új esemény hozzáadása.
     * @param newEvent Új esemény.
     * @return Siker esetén: "Event: addition success". Hiba esetén: "Event: addition failure".
     */
    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewEvent newEvent)
    {
        Optional<Event> optionalEvent = eventService.addNewEvent(newEvent);

        if (!optionalEvent.isPresent()) {
            Response.error("Event: addition failure");
        }

        return Response.ok("Event: addition success");  
    }

    /**
     * Visszaadja az eseményt a paraméterben megadott azonosító szerint.
     * @param id Az esemény azonosítója.
     * @return Siker esetén: az esemény adatai. Hiba esetén: "Event: no event found with this id: id".
     */
    @RequestMapping(value= "/by_id", method=RequestMethod.GET)
    public Response<Event> getEventById(@RequestParam long id)
    {
        Optional<Event> optionalEvent = eventService.byId(id);

        if (!optionalEvent.isPresent()) {
            Response.error("Event: no event found with this id: " + id);
        }

        return Response.ok(optionalEvent.get());
    }

    /**
     * Esemény törlése a kommentjeivel együtt a paraméterben megadott azonosító alapján.
     * @param id Esemény azonosítója.
     * @return Siker esetén: "Event: deletion success". Hiba esetén: "Event: deletion failure".
     */
    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam long id)
    {
        long deletedEvents = eventService.delete(id);

        if (deletedEvents <= 0) {
            Response.error("Event: deletion failure");
        }

        return Response.ok("Event: deletion success");
    }
}