package com.jkb.bestpie.api.domain.board.repository;


import com.jkb.bestpie.api.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {

}