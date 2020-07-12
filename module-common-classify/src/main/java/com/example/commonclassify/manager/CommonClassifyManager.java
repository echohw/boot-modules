package com.example.commonclassify.manager;

import com.example.commonclassify.objects.QueryCommonClassifyParams;
import com.example.commonclassify.persistence.entity.CommonClassify;
import com.example.commonclassify.persistence.repos.CommonClassifyRepos;
import com.example.commonclassify.persistence.spec.CommonClassifySpecification.QueryCommonClassifySpecification;
import com.example.commonclassify.utils.IdUtils;
import com.example.devutils.utils.EntityUtils;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Created by AMe on 2020-07-08 06:10.
 */
@Component
public class CommonClassifyManager {

    private static final int tracingMaxDepth = 20;
    private static final int cascadeMaxDepth = 20;
    private static final Logger logger = LoggerFactory.getLogger(CommonClassifyManager.class);

    @Autowired
    private CommonClassifyRepos commonClassifyRepos;

    public CommonClassify add(CommonClassify commonClassify) {
        commonClassify.setId(Optional.ofNullable(commonClassify.getId()).orElseGet(IdUtils::getId));
        return commonClassifyRepos.save(commonClassify);
    }

    public List<CommonClassify> addAll(List<CommonClassify> commonClassifyList) {
         return commonClassifyRepos.saveAll(commonClassifyList);
    }

    public boolean deleteById(String id) {
        commonClassifyRepos.deleteById(id);
        return true;
    }

    public boolean deleteAllByPid(String pid) {
        commonClassifyRepos.deleteAllByPid(pid);
        return true;
    }

    public boolean deleteAllByScopeAndGroupAndName(String scope, String group, String name) {
        commonClassifyRepos.deleteAllByScopeAndGroupAndName(scope, group, name);
        return true;
    }

    public boolean deleteAllByScopeAndGroup(String group, String scope) {
        commonClassifyRepos.deleteAllByScopeAndGroup(group, scope);
        return true;
    }

    public boolean deleteAllByScope(String scope) {
        commonClassifyRepos.deleteAllByScope(scope);
        return true;
    }

    public CommonClassify updateById(CommonClassify commonClassify) {
        return commonClassifyRepos.save(commonClassify);
    }

    public CommonClassify updateSelectiveById(CommonClassify commonClassify) {
        CommonClassify classify = getById(commonClassify.getId()).orElseThrow(() -> new RuntimeException("数据不存在"));
        BeanUtils.copyProperties(commonClassify, classify, EntityUtils.getNullPropertyNames(commonClassify));
        return updateById(classify);
    }

    public Optional<CommonClassify> getById(String id) {
        return commonClassifyRepos.findById(id);
    }

    /**
     * 级联子级
     */
    public Optional<CommonClassify> getByIdCascade(String id) {
        Optional<CommonClassify> commonClassifyOpt = getById(id);
        commonClassifyOpt.ifPresent(classify -> this.getByIdCascade(classify, 1));
        return commonClassifyOpt;
    }

    private void getByIdCascade(CommonClassify commonClassify, int depth) {
        if (depth > cascadeMaxDepth) {
            logger.error("搜索路径超过最大深度");
            return;
        }
        List<CommonClassify> classifyList = getAllByPid(commonClassify.getId());
        commonClassify.setChildren(classifyList);
        for (CommonClassify classify : classifyList) {
            getByIdCascade(classify, depth++);
            depth--;
        }
    }

    public List<CommonClassify> getAllByPid(String pid) {
        return commonClassifyRepos.findAllByPid(pid);
    }

    public List<CommonClassify> getAllByScopeAndGroupAndName(String name, String group, String scope) {
        return commonClassifyRepos.findAllByScopeAndGroupAndName(name, group, scope);
    }

    public Page<CommonClassify> getAllByQueryParams(QueryCommonClassifyParams params) {
        return commonClassifyRepos.findAll(new QueryCommonClassifySpecification(params), PageRequest.of(params.getPage(), params.getPageSize()));
    }

}
