package com.jkb.bestpie.api.domain.board.controller;

import com.jkb.bestpie.api.domain.board.entity.Board;
import com.jkb.bestpie.api.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public List<Board> getPosts() {
        return boardService.getPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getPostById(@PathVariable("id") int id) {
        Board post = boardService.getPostById(id);
        if (post != null) return ResponseEntity.ok(post);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/post")
    public ResponseEntity<String> postBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.savePost(board));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") int id, @RequestBody Board board) {
        return ResponseEntity.ok(boardService.updatePost(id, board));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") int id) {
        return ResponseEntity.ok(boardService.deletePost(id));
    }
}
