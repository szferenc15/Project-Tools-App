package app.sport_mates.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.sport_mates.class_interface.NewComment;
import app.sport_mates.model.Comment;
import app.sport_mates.service.CommentService;
import app.sport_mates.util.Response;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController{
    
    @Autowired
    private CommentService commentService;

    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<Iterable<Comment>> getComments()
    {
        Iterable<Comment> comment = commentService.all();
        return Response.ok(comment);
    }

    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewComment newComment)
    {
        Optional<Comment> optionalComment = commentService.addNewComment(newComment);
                                        
        return optionalComment.isPresent() ? 
                    Response.ok("Comment: addition success") : 
                    Response.error("Comment: addition failure");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam Long id)
    {
        Long deletedComments = commentService.delete(id);

        return deletedComments > 0 ? 
                    Response.ok("Comment: deletion success") : 
                    Response.error("Comment: deletion failure");
    }
}