package app.sportmates_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.sportmates_backend.class_interface.NewComment;
import app.sportmates_backend.model.Comment;
import app.sportmates_backend.service.CommentService;
import app.sportmates_backend.util.Response;
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
        
        if (!optionalComment.isPresent()) {
            Response.error("Comment: addition failure");    
        }

        return Response.ok("Comment: addition success");
    }

    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam Long id)
    {
        Long deletedComments = commentService.delete(id);

        if (deletedComments <= 0) {
            Response.error("Comment: deletion failure");
        }

        return Response.ok("Comment: deletion success");
    }
}