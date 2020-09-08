package co.coldflow.depot_music.web_student;

import co.coldflow.depot_music.dto.CommentRequestDto;
import co.coldflow.depot_music.dto.json_response.CustomResponse;
import co.coldflow.depot_music.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCommentController {
    private final CommentService commentService;

    StudentCommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/student/comments")
    public ResponseEntity<CustomResponse> postComment(CommentRequestDto commentRequestDto) {
        long id = commentService.insertComment(commentRequestDto);
        return ResponseEntity.ok(new CustomResponse(id));
    }

    @DeleteMapping("/student/comments/{commentId}")
    public ResponseEntity<CustomResponse> deleteComment(@PathVariable long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new CustomResponse());
    }
}
