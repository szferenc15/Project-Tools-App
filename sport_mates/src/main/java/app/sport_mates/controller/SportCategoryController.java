package app.sport_mates.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.sport_mates.class_interface.NewSportCategory;
import app.sport_mates.model.SportCategory;
import app.sport_mates.service.SportCategoryService;
import app.sport_mates.util.Response;
import java.util.Optional;

@RestController
@RequestMapping("/sport_category")
public class SportCategoryController{
    
    @Autowired
    private SportCategoryService sportCategoryService;

    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<Iterable<SportCategory>> getSportCategories()
    {
        Iterable<SportCategory> sportCategory = sportCategoryService.all();
        return Response.ok(sportCategory);
    }

    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewSportCategory newSportCategory)
    {
        Optional<SportCategory> optionalSportCategory = sportCategoryService.addNewCategory(newSportCategory);
                                        
        return optionalSportCategory.isPresent() ? 
                Response.ok("SportCategory: addition success") : 
                Response.error("SportCategory: addition failure");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam String category)
    {
        Long deletedCategories = sportCategoryService.delete(category);

        return deletedCategories > 0 ? 
                    Response.ok("SportCategory: deletion success") : 
                    Response.error("SportCategory: deletion failure");
    }
}