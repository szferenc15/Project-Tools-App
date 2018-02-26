package app.sport_mates.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import app.sport_mates.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Integer>{
    Iterable<Comment> findAll(); 
}