package com.example.accessrecord.manager;

import com.example.accessrecord.objects.QueryAccessRecordParams;
import com.example.accessrecord.persistence.entity.AccessRecord;
import com.example.accessrecord.persistence.repos.AccessRecordRepos;
import com.example.accessrecord.persistence.spec.AccessRecordSpecification.QueryAccessRecordSpecification;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Created by AMe on 2020-07-09 16:51.
 */
@Component
public class AccessRecordManager {

    private static final Logger logger = LoggerFactory.getLogger(AccessRecordManager.class);

    @Autowired
    private AccessRecordRepos accessRecordRepos;

    public AccessRecord add(AccessRecord accessRecord) {
        return accessRecordRepos.save(accessRecord);
    }

    public boolean deleteById(Long id) {
        accessRecordRepos.deleteById(id);
        return true;
    }

    public Optional<AccessRecord> getById(Long id) {
        return accessRecordRepos.findById(id);
    }

    public Page<AccessRecord> getAllByQueryParams(QueryAccessRecordParams params) {
        return accessRecordRepos.findAll(new QueryAccessRecordSpecification(params), PageRequest.of(params.getPage(), params.getPageSize()));
    }

}
