package com.example.commonclassify.manager;

import com.example.commonclassify.objects.QueryCommonClassifyParams;
import com.example.commonclassify.persistence.entity.CommonClassify;
import com.example.commonclassify.persistence.repos.CommonClassifyRepos;
import com.example.commonclassify.persistence.spec.CommonClassifySpecification.QueryCommonClassifySpecification;
import com.example.devutils.utils.EntityUtils;
import com.example.devutils.utils.id.IdUtils;
import com.example.devutils.utils.text.StringUtils;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by AMe on 2020-07-08 06:10.
 */
@Component
public class CommonClassifyManager {

    private static final int tracingMaxDepth = 20;
    private static final int cascadeMaxDepth = 20;
    private static final Logger logger = LoggerFactory.getLogger(CommonClassifyManager.class);

    @Autowired
    private IdUtils idUtils;
    @Autowired
    private CommonClassifyRepos commonClassifyRepos;

    public CommonClassify add(CommonClassify commonClassify) {
        commonClassify.setId(StringUtils.isNotBlank(commonClassify.getId()) ? commonClassify.getId() : String.valueOf(idUtils.nextSnowId()));
        return commonClassifyRepos.save(commonClassify);
    }

    public List<CommonClassify> addAll(List<CommonClassify> commonClassifyList) {
        commonClassifyList.forEach(classify -> {
            classify.setId(StringUtils.isNotBlank(classify.getId()) ? classify.getId() : String.valueOf(idUtils.nextSnowId()));
        });
         return commonClassifyRepos.saveAll(commonClassifyList);
    }

    public boolean deleteById(String id) {
        commonClassifyRepos.deleteById(id);
        return true;
    }

    public boolean deleteByIds(List<String> ids) {
        commonClassifyRepos.deleteByIdIn(ids);
        return true;
    }

    public boolean deleteAllByPid(String pid) {
        commonClassifyRepos.deleteAllByPid(pid);
        return true;
    }

    public boolean deleteAllByScopeAndClassifyAndName(String scope, String classify, String name) {
        commonClassifyRepos.deleteAllByScopeAndClassifyAndName(scope, classify, name);
        return true;
    }

    public boolean deleteAllByScopeAndClassify(String scope, String classify) {
        commonClassifyRepos.deleteAllByScopeAndClassify(scope, classify);
        return true;
    }

    public boolean deleteAllByScope(String scope) {
        commonClassifyRepos.deleteAllByScope(scope);
        return true;
    }

    public CommonClassify updateById(CommonClassify commonClassify) {
        Assert.hasText(commonClassify.getId(), "id不能为空");
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

    public List<CommonClassify> getAllByScopeAndClassifyAndName(String scope, String classify, String name) {
        return commonClassifyRepos.findAllByScopeAndClassifyAndName(scope, classify, name);
    }

    public Page<CommonClassify> getAllByQueryParams(QueryCommonClassifyParams params) {
        return commonClassifyRepos.findAll(new QueryCommonClassifySpecification(params), PageRequest.of(params.getPage(), params.getPageSize()));
    }

}
