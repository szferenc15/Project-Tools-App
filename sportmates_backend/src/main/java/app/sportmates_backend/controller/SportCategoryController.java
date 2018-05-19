package app.sportmates_backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.sportmates_backend.class_interface.NewSportCategory;
import app.sportmates_backend.model.SportCategory;
import app.sportmates_backend.service.SportCategoryService;
import app.sportmates_backend.util.Response;

/**
 * Ez az osztály végzi a sportágakkal kapcsolatos műveleteket kezelését.
 * @author szendrei
 *@author polozgai
 *
 */
@RestController
@RequestMapping("/sport_category")
public class SportCategoryController{
    
    @Autowired
    private SportCategoryService sportCategoryService;

    /**
     * Az összes sport kategória kilistázása.
     * @return Összes sport kategória listája.
     */
    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<Iterable<SportCategory>> getSportCategories()
    {
        Iterable<SportCategory> sportCategory = sportCategoryService.all();
        return Response.ok(sportCategory);
    }

    /**
     * A paraméterben megadott sport kategória lekérdezése.
     * @param category Kategória neve.
     * @return Siker esetén: a kategória adatai. Hiba esetén: "SportCategory: no category found with this category: category".
     */
    @RequestMapping(value= "/by_category", method=RequestMethod.GET)
    public Response<SportCategory> getSportCategoryByCategory(@RequestParam String category)
    {
        Optional<SportCategory> optionalSportCategory = sportCategoryService.byCategory(category);

        if (!optionalSportCategory.isPresent()) {
            Response.error("SportCategory: no category found with this category: " + category);
        }

        return Response.ok(optionalSportCategory.get());
    }

    /**
     * A paraméterben megadott új sport kategória hozzáadása.
     * @param newSportCategory Kategória neve.
     * @return Siker esetén: "SportCategory: addition success". Hiba esetén: "SportCategory: addition failure".
     */
    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json;charset=UTF-8")
    public Response<String> add(@RequestBody NewSportCategory newSportCategory)
    {
        Optional<SportCategory> optionalSportCategory = sportCategoryService.addNewCategory(newSportCategory);

        if (!optionalSportCategory.isPresent()) {
            Response.error("SportCategory: addition failure");
        }

        return Response.ok("SportCategory: addition success");
    }

    /**
     * A paraméterben megadott sport kategória törlése.
     * @param category Kategória neve.
     * @return Siker esetén: "SportCategory: deletion success". Hiba esetén: "SportCategory: deletion failure".
     */
    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json;charset=UTF-8")
    public Response<String> delete(@RequestParam String category)
    {
        long deletedCategories = sportCategoryService.delete(category);

        if (deletedCategories <= 0) {
            Response.error("SportCategory: deletion failure");
        }

        return Response.ok("SportCategory: deletion success");
    }
}