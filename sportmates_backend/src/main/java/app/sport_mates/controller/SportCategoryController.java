package app.sportmates_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.sportmates_backend.class_interface.NewSportCategory;
import app.sportmates_backend.model.SportCategory;
import app.sportmates_backend.service.SportCategoryService;
import app.sportmates_backend.util.Response;
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

    @RequestMapping(value= "/by_category", method=RequestMethod.GET)
    public Response<SportCategory> getSportCategoryByCategory(@RequestParam String category)
    {
        Optional<SportCategory> optionalSportCategory = sportCategoryService.byCategory(category);

        if (!optionalSportCategory.isPresent()) {
            Response.error("SportCategory: no category found with this category: " + category);
        }

        return Response.ok(optionalSportCategory.get());
    }

    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewSportCategory newSportCategory)
    {
        Optional<SportCategory> optionalSportCategory = sportCategoryService.addNewCategory(newSportCategory);

        if (!optionalSportCategory.isPresent()) {
            Response.error("SportCategory: addition failure");
        }

        return Response.ok("SportCategory: addition success");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam String category)
    {
        long deletedCategories = sportCategoryService.delete(category);

        if (deletedCategories <= 0) {
            Response.error("SportCategory: deletion failure");
        }

        return Response.ok("SportCategory: deletion success");
    }
}