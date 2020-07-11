package com.example.regionstore.persistence.repos;

import com.example.regionstore.persistence.entity.Region;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepos extends JpaRepository<Region, Integer>, JpaSpecificationExecutor<Region> {

    Optional<Region> findByAreaCode(Long areaCode);
}
