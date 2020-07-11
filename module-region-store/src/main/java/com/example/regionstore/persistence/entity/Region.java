package com.example.regionstore.persistence.entity;

import com.example.regionstore.persistence.LongTimestampedEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Created by AMe on 2020-07-09 14:26.
 */
@Data
@Entity
@Table(name = "china_region")
@SQLDelete(sql = "update china_region set entity_status = 1 where id = ?")
@Where(clause = "entity_status <> 1")
public class Region extends LongTimestampedEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "level")
    private Byte level; // 层级
    @Column(name = "parent_code")
    private Long parentCode; // 父级行政代码
    @Column(name = "area_code")
    private Long areaCode; // 行政代码
    @Column(name = "zip_code")
    private String zipCode; // 邮政编码
    @Column(name = "city_code")
    private String cityCode; // 区号
    @Column(name = "name")
    private String name; // 名称
    @Column(name = "short_name")
    private String shortName; // 简称
    @Column(name = "merger_name")
    private String mergerName; // 组合名
    @Column(name = "pinyin")
    private String pinyin; // 拼音
    @Column(name = "lng")
    private BigDecimal longitude; // 经度
    @Column(name = "lat")
    private BigDecimal latitude; // 纬度
    @Column(name = "entity_status")
    private Byte entityStatus;
    @Transient
    private Region parent;

}
