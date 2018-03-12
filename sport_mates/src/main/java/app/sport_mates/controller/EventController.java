package app.sport_mates.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import app.sport_mates.model.Event;
import app.sport_mates.service.EventService;
import app.sport_mates.util.Response;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

class NewEvent {
    private String name;
    private String locale;
    private short price;
    private Date dateOfEvent;
    private Time start;
    private Time finish;
    private short headcount;
    private String audience;
    private String description;

    public String getName() {
        return name;
    }
    
    public String getLocale() {
        return locale;
    }

    public short getPrice() {
        return price;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public Time getStart() {
        return start;
    }

    public Time getFinish() {
        return finish;
    }

    public short getHeadcount() {
        return headcount;
    }

    public String getAudience() {
        return audience;
    }
    
    public String getDescription() {
        return description;
    }
}

@RestController
@RequestMapping("/event")
public class EventController{
    
    @Autowired()
    private EventService eventService;

    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewEvent newEvent)
    {
        Optional<Event> optionalEvent = eventService.addNewEvent(newEvent.getName(), newEvent.getLocale(),
                                                           newEvent.getPrice(), newEvent.getDateOfEvent(), 
                                                           newEvent.getStart(), newEvent.getFinish(),
                                                           newEvent.getHeadcount(), newEvent.getAudience(),
                                                           newEvent.getDescription());
                                        
        return optionalEvent.isPresent() ? Response.ok("Event: ok") : Response.error("Event: error");
    }
}