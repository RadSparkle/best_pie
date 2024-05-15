package com.jkb.bestpie.api.domain.scrap.service;

import com.jkb.bestpie.api.domain.scrap.Entity.Community;

public interface ScrapService {

    public Long saveScrap(Community community);

    public Community findScrap(Long id);
}
