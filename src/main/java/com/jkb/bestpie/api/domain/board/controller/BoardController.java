package com.jkb.bestpie.api.domain.board.controller;

import com.jkb.bestpie.api.domain.board.entity.Board;
import com.jkb.bestpie.api.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/board")
    public List<Board> getBoard() {
        return boardService.getBoards();
    }

    @PostMapping(value = "/board/create/")
    public String postBoard(@RequestBody Board board){

        return boardService.postBoard(board);
    }

}
