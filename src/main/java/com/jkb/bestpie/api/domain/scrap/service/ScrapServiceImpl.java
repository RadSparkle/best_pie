package com.jkb.bestpie.api.domain.scrap.service;

import com.jkb.bestpie.api.domain.scrap.Entity.Community;
import com.jkb.bestpie.api.domain.scrap.repository.ScraperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService {

    private final ScraperRepository scraperRepository;

    /**
     * Saves a new scrap entry if it doesn't already exist.
     * @param community The community to be saved.
     * @return The ID of the saved community, or {@code null} if a duplicate was found.
     */
    @Override
    public Long saveScrap(Community community) {
        if(!scraperRepository.existsBySiteNameAndTitle(community.getSiteName(), community.getTitle())){
            Community savedCommunity  = scraperRepository.save(community);
            return savedCommunity .getId();
        }
        return null;
    }

    /**
     * Finds a scrap by its ID.
     * @param id The ID of the scrap to find.
     * @return The found scrap, or {@code null} if not found.
     */
    @Override
    public Community findScrap(Long id) {
        return scraperRepository.findById(id).orElse(null);
    }
}
