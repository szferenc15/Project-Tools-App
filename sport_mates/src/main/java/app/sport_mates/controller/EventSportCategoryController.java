package app.sport_mates.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.sport_mates.class_interface.NewEventSportCategory;
import app.sport_mates.model.EventSportCategory;
import app.sport_mates.service.EventSportCategoryService;
import app.sport_mates.util.Response;
import java.util.Optional;

@RestController
@RequestMapping("/event_sport_category")
public class EventSportCategoryController{
    
    @Autowired
    private EventSportCategoryService eventSportCategoryService;

    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<Iterable<EventSportCategory>> getEventSportCategories()
    {
        Iterable<EventSportCategory> sportCategory = eventSportCategoryService.all();
        return Response.ok(sportCategory);
    }

    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewEventSportCategory newEventSportCategory)
    {
        Optional<EventSportCategory> optionalSportCategory = 
                    eventSportCategoryService.addNewEventSportCategory(newEventSportCategory);
                                        
        return optionalSportCategory.isPresent() ? 
           Response.ok("EventSportCategory: addition success") : 
           Response.error("EventSportCategory: addition failure");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam Long id)
    {
        Long deletedEventSportCategories = eventSportCategoryService.delete(id);

        return deletedEventSportCategories > 0 ? 
                    Response.ok("EventSportCategory: deletion success") : 
                    Response.error("EventSportCategory: deletion failure");
    }
}