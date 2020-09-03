package co.coldflow.depot_music.web;

import co.coldflow.depot_music.service.CommentService;
import co.coldflow.depot_music.web.dto.json_response.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<CustomResponse> deleteComment(@PathVariable long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new CustomResponse());
    }
}
