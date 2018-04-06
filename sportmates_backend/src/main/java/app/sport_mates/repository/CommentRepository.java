package app.sportmates_backend.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import app.sportmates_backend.model.Comment;
import app.sportmates_backend.model.Event;
import app.sportmates_backend.model.User;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Integer>{
    Optional<Comment> findById(long id);
    Iterable<Comment> findByEventId(Event eventId);
    Iterable<Comment> findByUserId(User userId);

    Iterable<Comment> findAll(); 

    @Transactional
    long deleteById(long id);
}