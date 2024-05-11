package com.jkb.bestpie.api.domain.scrap.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "COMMUNITY")
@Getter @Setter @NoArgsConstructor
public class Community {
    @Id @GeneratedValue
    @Column(name = "community_id")
    private Long id;

    private String siteName;

    private String title;

    private String url;

    private LocalDateTime regDate;

}
