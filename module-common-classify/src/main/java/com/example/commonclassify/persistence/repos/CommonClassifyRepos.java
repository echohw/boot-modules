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

    List<CommonClassify> findAllByScopeAndGroupAndName(String scope, String group, String name);

    void deleteAllByPid(String pid);

    void deleteAllByScopeAndGroupAndName(String scope, String group, String name);

    void deleteAllByScopeAndGroup(String scope, String group);

    void deleteAllByScope(String scope);

}
