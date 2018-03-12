package app.sport_mates.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import app.sport_mates.service.UserService;
import app.sport_mates.model.User;
import app.sport_mates.util.Response;
import java.sql.Date;
import java.util.Optional;

class AuthUser {
    private String identifier;
    private String password;

    public String getIdentifier() {
        return identifier;
    }
    
    public String getPassword() {
        return password;
    }
}

class RegUser {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String city;
    private Date birthDate;
    private boolean isMale;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public boolean isMale() {
        return isMale;
    }
}

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired()
    private UserService userService;

    @RequestMapping(value= "/login", method=RequestMethod.POST, consumes="application/json")
    public Response<User> login(@RequestBody AuthUser user)
    {
        Optional<User> optionalUser = userService.login(user.getIdentifier(), user.getPassword());

        if(optionalUser.isPresent()){
            User loggedUser = optionalUser.get();
            return Response.ok(loggedUser); 
        }

        return Response.error("User: error - authentication");
    }

    @RequestMapping(value= "/register", method=RequestMethod.POST, consumes="application/json")
    public Response<User> register(@RequestBody RegUser new_user)
    {
        Optional<User> optionalUser = userService.register(new_user.getFirstName(), new_user.getLastName(),
                                                           new_user.getUsername(), new_user.getPassword(), 
                                                           new_user.getEmail(), new_user.getPhoneNumber(),
                                                           new_user.getCity(), new_user.getBirthDate(),
                                                           new_user.isMale());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return Response.ok(user); 
        }

        return Response.error("User: error - unique");
    }
}