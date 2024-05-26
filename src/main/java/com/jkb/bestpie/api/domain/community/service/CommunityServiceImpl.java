package com.jkb.bestpie.api.domain.community.service;

import com.jkb.bestpie.api.domain.community.repository.CommunityRepository;
import com.jkb.bestpie.common.Entity.Community;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    private final CommunityRepository communityRepository;
    private final Pageable topTen = PageRequest.of(0, 10);

    @Override
    public List<Community> dcincideList() {
        return communityRepository.findBySiteNameOrderByIdDesc("DCINSIDE",topTen);
    }

    @Override
    public List<Community> nateList() {
        return communityRepository.findBySiteNameOrderByIdDesc("NATE",topTen);
    }

    @Override
    public List<Community> bobaeList() {
        return communityRepository.findBySiteNameOrderByIdDesc("BOBAE",topTen);
    }
}
