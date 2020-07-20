package com.example.commonclassify.persistence.spec;

import com.example.commonclassify.objects.QueryCommonClassifyParams;
import com.example.commonclassify.persistence.entity.CommonClassify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by AMe on 2020-07-09 21:25.
 */
public class CommonClassifySpecification {

    public static class QueryCommonClassifySpecification implements Specification<CommonClassify> {

        private QueryCommonClassifyParams params;

        public QueryCommonClassifySpecification(QueryCommonClassifyParams params) {
            this.params = params;
        }

        @Override
        public Predicate toPredicate(Root<CommonClassify> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicateList = new ArrayList<>();
            Optional.ofNullable(params.getScope()).ifPresent(scope -> {
                predicateList.add(criteriaBuilder.equal(root.get("scope").as(String.class), scope));
            });
            Optional.ofNullable(params.getClassify()).ifPresent(classify -> {
                predicateList.add(criteriaBuilder.equal(root.get("classify").as(String.class), classify));
            });
            Optional.ofNullable(params.getName()).ifPresent(name -> {
                predicateList.add(criteriaBuilder.equal(root.get("name").as(String.class), name));
            });
            Optional.ofNullable(params.getPid()).ifPresent(pid -> {
                predicateList.add(criteriaBuilder.equal(root.get("pid").as(String.class), pid));
            });
            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            return criteriaQuery.getRestriction();
        }
    }

}
