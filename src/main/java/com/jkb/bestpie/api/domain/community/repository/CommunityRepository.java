package com.jkb.bestpie.api.domain.community.repository;

import com.jkb.bestpie.common.Entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    public List<Community> findBySiteNameOrderByIdDesc(String siteName);
}
