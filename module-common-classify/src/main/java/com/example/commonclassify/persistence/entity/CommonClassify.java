package com.example.commonclassify.persistence.entity;

import com.example.commonbase.persistence.LongTimestampedEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

/**
 * Created by AMe on 2020-07-08 05:59.
 */
@Data
@Entity
@Table(name = "common_classify", indexes = {
    @Index(name = "idx_scope_classify_name", columnList = "scope,classify,name")
})
public class CommonClassify extends LongTimestampedEntity implements Serializable {

    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "name", length = 36, nullable = false) // 名称
    private String name;
    @Column(name = "value", length = 255, nullable = true) // 值
    private String value;
    @Column(name = "value_remarks", length = 100, nullable = true) // 值备注
    private String valueRemarks;
    @Column(name = "classify", length = 36, nullable = false) // 分类
    private String classify;
    @Column(name = "scope", length = 36, nullable = false) // 所属域
    private String scope;
    @Column(name = "pid", length = 36, nullable = false) // 父级ID
    private String pid;
    @Column(name = "remarks", length = 255, nullable = true) // 备注
    private String remarks;
    @Transient
    private List<CommonClassify> children;

}
