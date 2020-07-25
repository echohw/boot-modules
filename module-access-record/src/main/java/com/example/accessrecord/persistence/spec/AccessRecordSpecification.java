package com.example.accessrecord.persistence.spec;

import com.example.accessrecord.objects.QueryAccessRecordParams;
import com.example.accessrecord.persistence.entity.AccessRecord;
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
public class AccessRecordSpecification {

    public static class QueryAccessRecordSpecification implements Specification<AccessRecord> {

        private QueryAccessRecordParams params;

        public QueryAccessRecordSpecification(QueryAccessRecordParams params) {
            this.params = params;
        }

        @Override
        public Predicate toPredicate(Root<AccessRecord> root, CriteriaQuery<?> criteriaQuery,
            CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicateList = new ArrayList<>();
            Optional.ofNullable(params.getVisitor()).ifPresent(visitor -> {
                predicateList.add(criteriaBuilder.equal(root.get("visitor").as(String.class), visitor));
            });
            Optional.ofNullable(params.getClientIp()).ifPresent(clientIp -> {
                predicateList.add(criteriaBuilder.equal(root.get("clientIp").as(String.class), clientIp));
            });
            Optional.ofNullable(params.getUserAgentLike()).ifPresent(userAgentLike -> {
                predicateList.add(criteriaBuilder.like(root.get("userAgent").as(String.class), "%" + userAgentLike + "%"));
            });
            Optional.ofNullable(params.getHandlerClass()).ifPresent(handlerClass -> {
                predicateList.add(criteriaBuilder.equal(root.get("handlerClass").as(String.class), handlerClass));
            });
            Optional.ofNullable(params.getHandlerMethod()).ifPresent(handlerMethod -> {
                predicateList.add(criteriaBuilder.equal(root.get("handlerMethod").as(String.class), handlerMethod));
            });
            Optional.ofNullable(params.getHttpMethod()).ifPresent(httpMethod -> {
                predicateList.add(criteriaBuilder.equal(root.get("httpMethod").as(String.class), httpMethod));
            });
            Optional.ofNullable(params.getDuration()).ifPresent(duration -> {
                Optional.ofNullable(duration.getFrom()).ifPresent(from -> predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("duration").as(Integer.class), from)));
                Optional.ofNullable(duration.getTo()).ifPresent(to -> predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("duration").as(Integer.class), to)));
            });
            Optional.ofNullable(params.getScope()).ifPresent(scope -> {
                predicateList.add(criteriaBuilder.equal(root.get("scope").as(String.class), scope));
            });
            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            return criteriaQuery.getRestriction();
        }
    }

}
