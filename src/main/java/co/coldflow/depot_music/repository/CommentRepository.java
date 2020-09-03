package co.coldflow.depot_music.repository;

import co.coldflow.depot_music.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
