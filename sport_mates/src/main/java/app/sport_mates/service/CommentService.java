package app.sport_mates.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import app.sport_mates.repository.CommentRepository;
import app.sport_mates.model.Comment;
import app.sport_mates.model.Event;
import app.sport_mates.model.User;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired()
    private CommentRepository commentRepository;

    public Optional<Comment> addNewComment(String message,
                                         Event eventId,
                                         User userId){
        Comment comment = new Comment();

        comment.setMessage(message);
        comment.setEventId(eventId);
        comment.setUserId(userId);
        
        commentRepository.save(comment);

        return Optional.of(comment);
    }
}