package co.coldflow.depot_music.service;

import co.coldflow.depot_music.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
