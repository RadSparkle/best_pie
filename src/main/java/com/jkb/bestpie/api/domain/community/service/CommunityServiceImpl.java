package com.jkb.bestpie.api.domain.community.service;

import com.jkb.bestpie.api.domain.community.repository.CommunityRepository;
import com.jkb.bestpie.common.Entity.Community;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    private final CommunityRepository communityRepository;

    @Override
    public List<Community> dcincideList() {
        return communityRepository.findBySiteNameOrderByIdDesc("DCINSIDE");
    }

    @Override
    public List<Community> nateList() {
        return communityRepository.findBySiteNameOrderByIdDesc("NATE");
    }

    @Override
    public List<Community> bobaeList() {
        return communityRepository.findBySiteNameOrderByIdDesc("BOBAE");
    }
}
