package app.sportmates_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.sportmates_backend.service.UserService;
import app.sportmates_backend.class_interface.AuthUser;
import app.sportmates_backend.class_interface.NewUser;
import app.sportmates_backend.model.User;
import app.sportmates_backend.util.Response;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<Iterable<User>> getUsers()
    {
        Iterable<User> users = userService.all();
        return Response.ok(users);
    }

    @RequestMapping(value= "/register", method=RequestMethod.POST, consumes="application/json")
    public Response<User> register(@RequestBody NewUser newUser)
    {
        Optional<User> optionalUser = userService.register(newUser);
        if(!optionalUser.isPresent()){
            Response.error("User: registration failure");
        }

        User user = optionalUser.get();
        return Response.ok(user); 
    }

    @RequestMapping(value= "/login", method=RequestMethod.POST, consumes="application/json")
    public Response<User> login(@RequestBody AuthUser authUser)
    {
        Optional<User> optionalUser = userService.login(authUser);

        if(!optionalUser.isPresent()){
            Response.error("User: login failure");
        } 
        
        User authenticatedUser = optionalUser.get();
        return Response.ok(authenticatedUser); 
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam String username)
    {
        Long deletedUsers = userService.delete(username);

        if (deletedUsers <= 0) {
            Response.error("User: deletion failure");          
        }

        return Response.ok("User: deletion success");
    }
}