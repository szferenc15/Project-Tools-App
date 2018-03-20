package app.sport_mates.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import app.sport_mates.model.Comment;
import app.sport_mates.model.Event;
import app.sport_mates.model.User;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Integer>{
    Optional<Comment> findById(Long id);
    Optional<Comment> findByEventId(Event eventId);
    Optional<Comment> findByUserId(User userId);

    Iterable<Comment> findAll(); 

    @Transactional
    Long deleteById(Long id);
}