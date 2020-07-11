package com.example.regionstore.persistence.spec;

import com.example.regionstore.objects.QueryRegionParams;
import com.example.regionstore.persistence.entity.Region;
import java.math.BigDecimal;
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
public class RegionSpecification {

    public static class QueryRegionSpecification implements Specification<Region> {

        private QueryRegionParams params;

        public QueryRegionSpecification(QueryRegionParams params) {
            this.params = params;
        }

        @Override
        public Predicate toPredicate(Root<Region> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicateList = new ArrayList<>();
            Optional.ofNullable(params.getLevel()).ifPresent(level -> {
                predicateList.add(criteriaBuilder.equal(root.get("level").as(Byte.class), level));
            });
            Optional.ofNullable(params.getParentCode()).ifPresent(parentCode -> {
                predicateList.add(criteriaBuilder.equal(root.get("parentCode").as(Long.class), parentCode));
            });
            Optional.ofNullable(params.getZipCode()).ifPresent(zipCode -> {
                predicateList.add(criteriaBuilder.equal(root.get("zipCode").as(String.class), zipCode));
            });
            Optional.ofNullable(params.getCityCode()).ifPresent(cityCode -> {
                predicateList.add(criteriaBuilder.equal(root.get("cityCode").as(String.class), cityCode));
            });
            Optional.ofNullable(params.getName()).ifPresent(name -> {
                predicateList.add(criteriaBuilder.equal(root.get("name").as(String.class), name));
            });
            Optional.ofNullable(params.getNameLike()).ifPresent(nameLike -> {
                predicateList.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + nameLike + "%"));
            });
            Optional.ofNullable(params.getShortName()).ifPresent(shortName -> {
                predicateList.add(criteriaBuilder.equal(root.get("shortName").as(String.class), shortName));
            });
            Optional.ofNullable(params.getShortNameLike()).ifPresent(shortNameLike -> {
                predicateList.add(criteriaBuilder.like(root.get("shortName").as(String.class), "%" + shortNameLike + "%"));
            });
            Optional.ofNullable(params.getPinyin()).ifPresent(pinyin -> {
                predicateList.add(criteriaBuilder.equal(root.get("pinyin").as(String.class), pinyin));
            });
            Optional.ofNullable(params.getPinyinLike()).ifPresent(pinyinLike -> {
                predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("pinyin").as(String.class)), "%" + pinyinLike.toLowerCase() + "%"));
            });
            Optional.ofNullable(params.getLongitude()).ifPresent(longitude -> {
                Optional.ofNullable(longitude.getFrom()).ifPresent(from -> predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("longitude").as(BigDecimal.class), from)));
                Optional.ofNullable(longitude.getTo()).ifPresent(to -> predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("longitude").as(BigDecimal.class), to)));
            });
            Optional.ofNullable(params.getLatitude()).ifPresent(latitude -> {
                Optional.ofNullable(latitude.getFrom()).ifPresent(from -> predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("latitude").as(BigDecimal.class), from)));
                Optional.ofNullable(latitude.getTo()).ifPresent(to -> predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("latitude").as(BigDecimal.class), to)));
            });
            criteriaQuery.where(predicateList.toArray(new Predicate[0]));
            return criteriaQuery.getRestriction();
        }
    }

}
