package com.jkb.bestpie.api.domain.board.service;


import com.jkb.bestpie.api.domain.board.entity.Board;
import com.jkb.bestpie.api.domain.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    public String postBoard(Board board) {
        // 게시글을 저장하고 저장된 게시글의 ID를 반환합니다.
        Board savedBoard = boardRepository.save(board);
        // 저장된 엔티티의 ID를 추출하여 반환합니다.
        return "Board saved with ID: " + savedBoard.getId();
    }


}