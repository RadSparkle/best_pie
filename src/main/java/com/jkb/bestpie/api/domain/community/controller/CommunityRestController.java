package com.jkb.bestpie.api.domain.community.controller;

import com.jkb.bestpie.api.domain.community.service.CommunityService;
import com.jkb.bestpie.common.Entity.Community;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
public class CommunityRestController {

    private final CommunityService communityService;

    /**
     * DcincideList
     */
    @GetMapping("/DcincideList")
    public List<CommunityDto> findDcincideList() {
        List<Community> communities = communityService.dcincideList();
        List<CommunityDto> collect = communities.stream()
                .map(c -> new CommunityDto(c))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * nateList
     */
    @GetMapping("/nateList")
    public List<CommunityDto> findNateList() {
        List<Community> communities = communityService.nateList();
        List<CommunityDto> collect = communities.stream()
                .map(c -> new CommunityDto(c))
                .collect(Collectors.toList());
        return collect;
    }

    /**
     * bobaeList
     */
    @GetMapping("/bobaeList")
    public List<CommunityDto> findBobaeList() {
        List<Community> communities = communityService.bobaeList();
        List<CommunityDto> collect = communities.stream()
                .map(c -> new CommunityDto(c))
                .collect(Collectors.toList());
        return collect;
    }

    @Getter
    @NoArgsConstructor
    static class CommunityDto{
        private Long Id;
        private String site;
        private String title;
        private String url;

        public CommunityDto(Community community) {
            this.Id = community.getId();
            this.site = community.getSiteName();
            this.title = community.getTitle();
            this.url = community.getUrl();
        }
    }
}
