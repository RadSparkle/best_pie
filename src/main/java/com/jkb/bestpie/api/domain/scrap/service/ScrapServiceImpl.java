package com.jkb.bestpie.api.domain.scrap.service;

import com.jkb.bestpie.api.domain.scrap.Entity.Community;
import com.jkb.bestpie.api.domain.scrap.repository.ScraperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScarpService{

    private final ScraperRepository scraperRepository;

    @Override
    public void saveScrap(Community community) {
        if(!scraperRepository.existsBySiteNameAndTitle(community.getSiteName(), community.getTitle())){
            scraperRepository.save(community);
        }
    }
}
