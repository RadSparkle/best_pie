package com.jkb.bestpie.api.domain.board.service;


import com.jkb.bestpie.api.domain.board.entity.Board;
import com.jkb.bestpie.api.domain.board.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Board> getPosts() {
        return boardRepository.findAllByOrderByIdDesc();
    }

    public Board getPostById(Integer id) {
        return boardRepository.findById(id).orElse(null);
    }

    public String savePost(Board board) {
        return "Board saved with ID: " + boardRepository.save(board).getId();
    }

    public String updatePost(int id, Board board) {
        Board existingBoard = boardRepository.findById(id).orElse(null);
        if (existingBoard == null) {
            return "게시글을 찾을 수 없습니다.";
        }
        // 기존 게시글의 내용을 새로운 내용으로 업데이트
        existingBoard.setTitle(board.getTitle());
        existingBoard.setContent(board.getContent());
        // 수정된 게시글 저장
        boardRepository.save(existingBoard);
        return "게시글이 성공적으로 수정되었습니다.";
    }

    public String deletePost(int id) {
        boardRepository.deleteById(id);
        return "게시글이 성공적으로 삭제되었습니다";
    }


}
