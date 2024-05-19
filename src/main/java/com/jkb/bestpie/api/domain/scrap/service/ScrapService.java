package com.jkb.bestpie.api.domain.scrap.service;

import com.jkb.bestpie.common.Entity.Community;

public interface ScrapService {

    public Long saveScrap(Community community);

    public Community findScrap(Long id);
}
