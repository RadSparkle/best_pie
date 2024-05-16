package com.jkb.bestpie.api.domain.comment.cotroller;


import com.jkb.bestpie.api.domain.comment.entity.Comment;
import com.jkb.bestpie.api.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board/commnets")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Comment>> getCommentsByBoardId(@PathVariable("id") Integer boardId) {
        List<Comment> comments = commentService.getCommentsByBoardId(boardId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> addCommentToBoard(@PathVariable("id") Integer boardId, @RequestBody Comment comment) {
        String result = commentService.postCommentToBoard(boardId, comment);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable("id") Integer commentId, @RequestBody Comment comment) {
        String result = commentService.updateComment(commentId, comment);
        return ResponseEntity.ok(result);
    }



}
