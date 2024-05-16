package com.jkb.bestpie.api.domain.comment.service;


import com.jkb.bestpie.api.domain.board.repository.BoardRepository;
import com.jkb.bestpie.api.domain.comment.entity.Comment;
import com.jkb.bestpie.api.domain.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    public List<Comment> getCommentsByBoardId(Integer boardId) {
        return commentRepository.findByBoardId(boardId);
    }
    public String postCommentToBoard(Integer boardId, Comment comment) {
        try {
            comment.setBoardId(boardId);
            commentRepository.save(comment);
            return "댓글이 성공적으로 등록되었습니다.";
        } catch (Exception e) {
            return "댓글 등록에 실패했습니다.";
        }
    }




}
