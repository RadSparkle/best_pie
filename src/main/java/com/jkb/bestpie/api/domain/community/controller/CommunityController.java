package com.jkb.bestpie.api.domain.community.controller;

import com.jkb.bestpie.api.domain.scrap.Entity.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class CommunityController {
    /**
     * 만드는중 질문안받음
     * @return
     */
    @GetMapping("/list")
    public List<Community> communityList() {
        return null;
    }

}
