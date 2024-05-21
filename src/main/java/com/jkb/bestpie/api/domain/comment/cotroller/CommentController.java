// CommentController
package com.jkb.bestpie.api.domain.comment.cotroller;

import com.jkb.bestpie.api.domain.comment.entity.Comment;
import com.jkb.bestpie.api.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public ResponseEntity<List<Comment>> getCommentsByBoardId(@PathVariable("boardId") Integer boardId) {
        List<Comment> comments = commentService.getCommentsByBoardId(boardId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<String> addCommentToBoard(@PathVariable("boardId") Integer boardId, @RequestBody Comment comment) {
        String result = commentService.postCommentToBoard(boardId, comment);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable("commentId") Integer commentId, @RequestBody Comment comment) {
        String result = commentService.updateComment(commentId, comment);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") Integer commentId) {
        String result = commentService.deleteComment(commentId);
        return ResponseEntity.ok(result);
    }
}
