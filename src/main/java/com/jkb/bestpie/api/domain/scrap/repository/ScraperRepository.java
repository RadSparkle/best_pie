package com.jkb.bestpie.api.domain.scrap.repository;

import com.jkb.bestpie.common.Entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScraperRepository extends JpaRepository<Community, Long> {
    boolean existsBySiteNameAndTitle(String siteName, String title);
}
