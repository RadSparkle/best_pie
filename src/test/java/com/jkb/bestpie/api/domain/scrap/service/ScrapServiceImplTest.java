package com.jkb.bestpie.api.domain.scrap.service;

import com.jkb.bestpie.api.domain.scrap.Entity.Community;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ScrapServiceImplTest {

    @Autowired
    private ScrapService scrapService;

    @Test
    public void 스크랩저장() throws Exception{
        // given
        Community community = new Community();
        community.setSiteName("testSite");
        community.setUrl("test_test.com");
        community.setTitle("test_title");
        community.setRegDate(LocalDateTime.now());
        Long id = scrapService.saveScrap(community);

        // when
        Community scrap = scrapService.findScrap(id);

        // then
        assertNotNull(scrap);
        System.out.println("scrap = " + scrap);
        System.out.println("scrapSiteName = " + scrap.getSiteName());
    }
}