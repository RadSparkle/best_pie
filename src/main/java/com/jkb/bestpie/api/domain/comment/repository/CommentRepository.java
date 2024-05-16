package com.jkb.bestpie.api.domain.comment.repository;

import com.jkb.bestpie.api.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByBoardId(Integer boardId);
}
