package app.sportmates_backend.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.sportmates_backend.class_interface.NewComment;
import app.sportmates_backend.model.Comment;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.User;
import app.sportmates_backend.repository.CommentRepository;
import app.sportmates_backend.repository.EventRepository;
import app.sportmates_backend.repository.UserRepository;

/**
 * Ez az osztály végzi a kommentel kapcsolatos szolgáltatások kezelését.
 * @author szendrei
 * @author polozgai
 *
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Visszadja az összes kommentet.
     * @return Összes komment listája.
     */
    public Iterable<Comment> all() {
        return commentRepository.findAll();
    }

    /**
     * Visszaadja a kommenteket az esemény azonosítója alapján.
     * @param id Esemény azonosítója.
     * @return Eseményhez tartozó kommentek.
     */
    public Iterable<Comment> byEventId(long id) {
        Optional<Event> eventId = eventRepository.findById(id);
        return commentRepository.findByEventId(eventId.get());
    }

    /**
     * Visszaadja a kommenteket a felhasználó azonosítója alapján.
     * @param id Felhasználó azonosítója.
     * @return Felhasználó azonosítójához tartozó kommentek.
     */
    public Iterable<Comment> byUserId(long id) {
        Optional<User> userId = userRepository.findById(id);
        return commentRepository.findByUserId(userId.get());
    }

    /**
     * Új komment hozzáadása.
     * @param newComment Új komment.
     * @return Új komment.
     */
    @Transactional
    public Optional<Comment> addNewComment(NewComment newComment){
        Comment comment = new Comment();

        Optional<Event> eventId = eventRepository.findById(newComment.getEventId());
        Optional<User> userId = userRepository.findById(newComment.getUserId());

        if (eventId.isPresent() && userId.isPresent()) {
            comment.setMessage(newComment.getMessage());
            comment.setEventId(eventId.get());
            comment.setUserId(userId.get());
            
            commentRepository.save(comment);
    
            return Optional.of(comment);
        }

        return Optional.empty();
    }

    /**
     * Komment törlése az azonosítója alapján.
     * @param id Komment azonosítója.
     * @return Komment törlés.
     */
    public long delete(long id) {
        return commentRepository.deleteById(id);
    }
}