package com.jkb.bestpie.api.domain.comment.repository;

import com.jkb.bestpie.api.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // 여기에 추가적인 메서드를 정의할 수 있습니다.
}
