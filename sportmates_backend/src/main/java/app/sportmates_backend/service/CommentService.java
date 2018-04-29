package app.sportmates_backend.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.sportmates_backend.class_interface.NewComment;
import app.sportmates_backend.model.Comment;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.User;
import app.sportmates_backend.repository.CommentRepository;
import app.sportmates_backend.repository.EventRepository;
import app.sportmates_backend.repository.UserRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<Comment> all() {
        return commentRepository.findAll();
    }

    public Iterable<Comment> byEventId(long id) {
        Optional<Event> eventId = eventRepository.findById(id);
        return commentRepository.findByEventId(eventId.get());
    }

    public Iterable<Comment> byUserId(long id) {
        Optional<User> userId = userRepository.findById(id);
        return commentRepository.findByUserId(userId.get());
    }

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

    public long delete(long id) {
        return commentRepository.deleteById(id);
    }
}