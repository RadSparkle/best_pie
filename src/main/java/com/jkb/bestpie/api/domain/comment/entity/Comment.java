package com.jkb.bestpie.api.domain.comment.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "COMMENT")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    private String title;

    private String content;

    @Column(name = "board_id")
    private Integer boardId; // board_id 컬럼 추가

}
