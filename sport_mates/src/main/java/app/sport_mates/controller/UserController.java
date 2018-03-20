package app.sport_mates.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.sport_mates.service.UserService;
import app.sport_mates.class_interface.AuthUser;
import app.sport_mates.class_interface.NewUser;
import app.sport_mates.model.User;
import app.sport_mates.util.Response;
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
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return Response.ok(user); 
        }

        return Response.error("User: registration failure");
    }

    @RequestMapping(value= "/login", method=RequestMethod.POST, consumes="application/json")
    public Response<User> login(@RequestBody AuthUser authUser)
    {
        Optional<User> optionalUser = userService.login(authUser);

        if(optionalUser.isPresent()){
            User authenticatedUser = optionalUser.get();
            return Response.ok(authenticatedUser); 
        }

        return Response.error("User: login failure");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam String username)
    {
        Long deletedUsers = userService.delete(username);

        return deletedUsers > 0 ? 
                    Response.ok("User: deletion success") : 
                    Response.error("User: deletion failure");
    }
}