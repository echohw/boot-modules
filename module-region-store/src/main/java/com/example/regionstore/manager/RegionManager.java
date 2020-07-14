package com.example.regionstore.manager;

import com.example.devutils.utils.EntityUtils;
import com.example.regionstore.objects.QueryRegionParams;
import com.example.regionstore.persistence.entity.Region;
import com.example.regionstore.persistence.repos.RegionRepos;
import com.example.regionstore.persistence.spec.RegionSpecification.QueryRegionSpecification;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Created by AMe on 2020-07-09 16:51.
 */
@Component
public class RegionManager {

    private static final int tracingMaxDepth = 20;
    private static final int cascadeMaxDepth = 1;
    private static final Logger logger = LoggerFactory.getLogger(RegionManager.class);

    @Autowired
    private RegionRepos regionRepos;

    public Region add(Region region) {
        return regionRepos.save(region);
    }

    public boolean deleteById(Integer id) {
        regionRepos.deleteById(id);
        return true;
    }

    public boolean updateById(Region region) {
        Objects.requireNonNull(region.getId());
        regionRepos.save(region);
        return true;
    }

    public boolean updateSelectiveById(Region region) {
        Region dbRegion = getById(region.getId()).orElseThrow(() -> new RuntimeException("数据不存在"));
        BeanUtils.copyProperties(region, dbRegion, EntityUtils.getNullPropertyNames(region));
        return updateById(dbRegion);
    }

    public Optional<Region> getById(Integer id) {
        return regionRepos.findById(id);
    }

    /**
     * 溯源父级
     */
    public Optional<Region> getByIdTracing(Integer id) {
        Optional<Region> regionOpt = getById(id);
        regionOpt.ifPresent(region -> {
            Region baseRegion = region;
            Region parentRegion;
            int depth = 1;
            while ((parentRegion = getByAreaCode(baseRegion.getParentCode()).orElse(null)) != null) {
                if (depth++ > tracingMaxDepth) {
                    logger.error("搜索路径超过最大深度");
                    break;
                }
                baseRegion.setParent(parentRegion);
                baseRegion = parentRegion;
            }
        });
        return regionOpt;
    }

    public Optional<Region> getByAreaCode(Long areaCode) {
        return regionRepos.findByAreaCode(areaCode);
    }

    public Page<Region> getAllByQueryParams(QueryRegionParams params) {
        return regionRepos.findAll(new QueryRegionSpecification(params), PageRequest.of(params.getPage(), params.getPageSize()));
    }

}
