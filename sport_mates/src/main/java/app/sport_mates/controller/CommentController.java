package app.sport_mates.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import app.sport_mates.model.Comment;
import app.sport_mates.model.Event;
import app.sport_mates.model.User;
import app.sport_mates.service.CommentService;
import app.sport_mates.util.Response;
import java.util.Optional;

class NewComment {
    private String message;
    private Event eventId;
    private User userId;

    public String getMessage() {
        return message;
    }

    public Event getEventId() {
        return eventId;
    }

    public User getUserId() {
        return userId;
    }
}

@RestController
@RequestMapping("/comment")
public class CommentController{
    
    @Autowired()
    private CommentService commentService;

    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewComment newComment)
    {
        Optional<Comment> optionalComment = commentService.addNewComment(newComment.getMessage(),
                                                                         newComment.getEventId(),
                                                                         newComment.getUserId());
                                        
        return optionalComment.isPresent() ? Response.ok("Comment: ok") : Response.error("Comment: error");
    }
}