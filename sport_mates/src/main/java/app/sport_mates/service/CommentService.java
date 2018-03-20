package app.sport_mates.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import app.sport_mates.repository.CommentRepository;
import app.sport_mates.repository.EventRepository;
import app.sport_mates.repository.UserRepository;
import app.sport_mates.class_interface.NewComment;
import app.sport_mates.model.Comment;
import app.sport_mates.model.Event;
import app.sport_mates.model.User;

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