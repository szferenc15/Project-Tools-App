package app.sportmates_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import app.sportmates_backend.repository.CommentRepository;
import app.sportmates_backend.repository.EventRepository;
import app.sportmates_backend.repository.UserRepository;
import app.sportmates_backend.class_interface.NewComment;
import app.sportmates_backend.model.Comment;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.User;

import java.util.Optional;
import javax.transaction.Transactional;

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

    public Long delete(Long id) {
        return commentRepository.deleteById(id);
    }
}