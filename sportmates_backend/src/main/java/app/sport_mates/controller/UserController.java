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
import app.sportmates_backend.class_interface.UserInfo;
import app.sportmates_backend.model.User;
import app.sportmates_backend.util.Response;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<List<UserInfo>> getUsers()
    {
        List<UserInfo> userInfos = userService.all();
        return Response.ok(userInfos);
    }

    @RequestMapping(value= "/by_username", method=RequestMethod.GET)
    public Response<UserInfo> getUserByUsername(@RequestParam String username)
    {
        Optional<UserInfo> optionalUserInfo = userService.byUsername(username);

        if (!optionalUserInfo.isPresent()) {
            Response.error("User: no user found with this username: " + username);
        }

        return Response.ok(optionalUserInfo.get());
    }

    @RequestMapping(value= "/register", method=RequestMethod.POST, consumes="application/json")
    public Response<UserInfo> register(@RequestBody NewUser newUser)
    {
        Optional<UserInfo> optionalUserInfo = userService.register(newUser);

        if(!optionalUserInfo.isPresent()){
            Response.error("User: registration failure");
        }

        return Response.ok(optionalUserInfo.get()); 
    }

    @RequestMapping(value= "/login", method=RequestMethod.POST, consumes="application/json")
    public Response<UserInfo> login(@RequestBody AuthUser authUser)
    {
        Optional<UserInfo> optionalUserInfo = userService.login(authUser);

        if(!optionalUserInfo.isPresent()){
            Response.error("User: login failure");
        } 

        return Response.ok(optionalUserInfo.get()); 
    }

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