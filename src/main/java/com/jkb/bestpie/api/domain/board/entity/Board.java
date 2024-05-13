package com.jkb.bestpie.api.domain.board.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "board")
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title") // 컬럼 이름 명시
    private String title;

    @Column(name = "content") // 컬럼 이름 명시
    private String content;

    @Column(name = "date") // 컬럼 이름 명시
    private LocalDateTime date;
}

