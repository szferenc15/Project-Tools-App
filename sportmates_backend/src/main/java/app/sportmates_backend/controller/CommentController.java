package app.sportmates_backend.controller;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.sportmates_backend.class_interface.NewComment;
import app.sportmates_backend.model.Comment;
import app.sportmates_backend.service.CommentService;
import app.sportmates_backend.util.Response;
/**
 * Ez az osztály végzi a kommentekkel kapcsolatos műveletek kezelését.
 * @author szendrei
 * @author polozgai
 *
 */
@RestController
@RequestMapping("/comment")
public class CommentController{
    
    @Autowired
    private CommentService commentService;

    /**
     * Az összes komment kilistázása.
     * @return Összes komment listája.
     */
    @RequestMapping(value= "/all", method=RequestMethod.GET)
    public Response<Iterable<Comment>> getComments()
    {
        Iterable<Comment> comments = commentService.all();
        
        return Response.ok(comments);
    }

    /**
     * Komment lekérdezése az esemény azonosítója alapján.
     * @param eventId Esemény azonosítója.
     * @return Siker esetén: a komment adatai. Hiba esetén: "Comment: no comment found with this event id: id".
     */
    @RequestMapping(value= "/by_event_id", method=RequestMethod.GET)
    public Response<Iterable<Comment>> getCommentByEventId(@RequestParam long eventId)
    {
        Iterable<Comment> comments = commentService.byEventId(eventId);

        int size = 0;

        Iterator<Comment> it = comments.iterator();
        while(it.hasNext()) {
            size++;
            it.next();
        }

        if (size == 0) {
            Response.error("Comment: no comment found with this event id: " + eventId);
        }

        return Response.ok(comments);
    }

    /**
     * Kommentek lekérdezése a felhasználó azonosítója alapján.
     * @param userId Felhasználó azonosítója.
     * @return Siker esetén: a komment adatai. Hiba esetén: "Comment: no comment found with this user id: id".
     */
    @RequestMapping(value= "/by_user_id", method=RequestMethod.GET)
    public Response<Iterable<Comment>> getCommentByUserId(@RequestParam long userId)
    {
        Iterable<Comment> comments = commentService.byUserId(userId);
        
        int size = 0;
        
        Iterator<Comment> it = comments.iterator();
        while(it.hasNext()) {
            size++;
            it.next();
        }

        if (size == 0) {
            Response.error("Comment: no comment found with this user id: " + userId);
        }

        return Response.ok(comments);
    }

    /**
     * Új komment hozzáadása
     * @param newComment Új komment.
     * @return Siker esetén: "Comment: addition success". Hiba esetén: "Comment: addition failure".
     */
    @RequestMapping(value= "/add", method=RequestMethod.POST, consumes="application/json")
    public Response<String> add(@RequestBody NewComment newComment)
    {
        Optional<Comment> optionalComment = commentService.addNewComment(newComment);
        
        if (!optionalComment.isPresent()) {
            Response.error("Comment: addition failure");    
        }

        return Response.ok("Comment: addition success");
    }

    /**
     * Komment törlése.
     * @param id Komment azonosítója.
     * @return Siker esetén: "Comment: deletion success". Hiba esetén: "Comment: deletion failure". 
     */
    @RequestMapping(value= "/delete", method=RequestMethod.DELETE, consumes="application/json")
    public Response<String> delete(@RequestParam long id)
    {
        long deletedComments = commentService.delete(id);

        if (deletedComments <= 0) {
            Response.error("Comment: deletion failure");
        }

        return Response.ok("Comment: deletion success");
    }
}