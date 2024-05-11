package com.jkb.bestpie.api.domain.scrap.service;

import com.jkb.bestpie.api.domain.scrap.Entity.Community;

import java.util.List;

public interface CommunityService {

    public Long save(Community community);

    public Community findById(Long id);

    public Community findBySiteAndTitle(String site, String title);

    public List<Community> findAll();

}
