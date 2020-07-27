package com.example.commonclassify.persistence.repos;

import com.example.commonclassify.persistence.entity.CommonClassify;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by AMe on 2020-07-08 06:05.
 */
@Repository
public interface CommonClassifyRepos extends JpaRepository<CommonClassify, String>, JpaSpecificationExecutor<CommonClassify> {

    List<CommonClassify> findAllByPid(String pid);

    List<CommonClassify> findAllByScopeAndClassifyAndName(String scope, String classify, String name);

    void deleteByIdIn(List<String> ids);

    void deleteAllByPid(String pid);

    void deleteAllByScopeAndClassifyAndName(String scope, String classify, String name);

    void deleteAllByScopeAndClassify(String scope, String classify);

    void deleteAllByScope(String scope);

}
