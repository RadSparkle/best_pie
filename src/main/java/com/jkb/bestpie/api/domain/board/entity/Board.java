package com.jkb.bestpie.api.domain.board.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

private String title;

private String content;

private Data date;





}
