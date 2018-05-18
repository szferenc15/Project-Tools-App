package app.sportmates_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.sportmates_backend.class_interface.AuthUser;
import app.sportmates_backend.class_interface.NewUser;
import app.sportmates_backend.class_interface.UserInfo;
import app.sportmates_backend.service.UserService;
import app.sportmates_backend.util.Response;

/**
 * Ez az osztály végzi a felhasználóval kapcsolatos műveletek kezelését.
 * @author szendrei
 * @author polozgai
 *
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    /**
     * Az összes felhasználó kilistázása.
     * @return Az összes felhasználó listája.
     */
    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<List<UserInfo>> getUserInfos()
    {
        List<UserInfo> userInfos = userService.all();
        return Response.ok(userInfos);
    }

    /**
     * A paraméterben megadott felhasználót adja vissza a neve alapján
     * @param username Felhasználó neve.
     * @return Siker esetén: az felhasználó adatai. Hiba esetén: "User: no user found with this username: username".
     */
    @RequestMapping(value= "/by_username", method=RequestMethod.GET)
    public Response<UserInfo> getUserByUsername(@RequestParam String username)
    {
        Optional<UserInfo> optionalUserInfo = userService.byUsername(username);

        if (!optionalUserInfo.isPresent()) {
            Response.error("User: no user found with this username: " + username);
        }

        return Response.ok(optionalUserInfo.get());
    }

    /**
     * A paraméterben megadott felhasználót regisztrálja a rendszerbe.
     * @param newUser Új felhasználó.
     * @return Felhasználó regisztrálása, hiba esetén: "User: registration failure".
     */
    @RequestMapping(value= "/register", method=RequestMethod.POST, consumes="application/json")
    public Response<UserInfo> register(@RequestBody NewUser newUser)
    {
        Optional<UserInfo> optionalUserInfo = userService.register(newUser);

        if(!optionalUserInfo.isPresent()){
            Response.error("User: registration failure");
        }

        return Response.ok(optionalUserInfo.get()); 
    }

    /**
     * A paraméterben megadott felhasználó bejelentkezését végzi.
     * @param authUser Felhasználót azonosító objektum.
     * @return Siker esetén: a felhasználó összes adata. Hiba esetén: "User: login failure".
     */
    @RequestMapping(value= "/login", method=RequestMethod.POST, consumes="application/json")
    public Response<UserInfo> login(@RequestBody AuthUser authUser)
    {
        Optional<UserInfo> optionalUserInfo = userService.login(authUser);

        if(!optionalUserInfo.isPresent()){
            Response.error("User: login failure");
        } 

        return Response.ok(optionalUserInfo.get()); 
    }

    /**
     * A felhasználó törlése minden adatával együtt, beleértve a kommenteket és eseményeket.
     * @param username Felhasználó neve.
     * @return Siker esetén: "User: deletion success". Hiba esetén: "User: deletion failure".
     */
    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam String username)
    {
        long deletedUsers = userService.delete(username);

        if (deletedUsers <= 0) {
            Response.error("User: deletion failure");          
        }

        return Response.ok("User: deletion success");
    }
}