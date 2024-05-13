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

    @PostMapping("/post")
    public ResponseEntity<String> postBoard(@RequestBody Board board) {
        // 클라이언트가 전송한 데이터를 이용하여 Board 엔티티 생성

        // BoardService를 사용하여 게시판 등록 수행
        String result = boardService.postBoard(board);

        // 결과를 ResponseEntity로 반환
        return ResponseEntity.ok(result);
    }


}
