package app.sportmates_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.sportmates_backend.class_interface.NewEventSportCategory;
import app.sportmates_backend.model.EventSportCategory;
import app.sportmates_backend.service.EventSportCategoryService;
import app.sportmates_backend.util.Response;
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

        if (!optionalSportCategory.isPresent()) {
            Response.error("EventSportCategory: addition failure");   
        }
        
        return Response.ok("EventSportCategory: addition success");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam Long id)
    {
        Long deletedEventSportCategories = eventSportCategoryService.delete(id);

        if (deletedEventSportCategories <= 0) {
            Response.error("EventSportCategory: deletion failure");
        }
        
        return Response.ok("EventSportCategory: deletion success");
    }
}