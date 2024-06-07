package com.jkb.bestpie.api.domain.board.repository;


import com.jkb.bestpie.api.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Integer> {

    List<Board> findAllByOrderByIdDesc();
}
