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

    @GetMapping(value = "/list")
    public List<Board> getBoard() {
        return boardService.getBoards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable("id") Integer id) {
        Board board = boardService.getBoardById(id);
        if (board != null) {
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<String> postBoard(@RequestBody Board board) {
        // 클라이언트가 전송한 데이터를 이용하여 Board 엔티티 생성

        // BoardService를 사용하여 게시판 등록 수행
        String result = boardService.postBoard(board);

        // 결과를 ResponseEntity로 반환
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") Integer id, @RequestBody Board board) {
        String result = boardService.updateBoard(id, board);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") Integer id) {
        String result = boardService.deleteBoard(id);
        return ResponseEntity.ok(result);
    }





}
