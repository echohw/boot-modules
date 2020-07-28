package com.example.accessrecord.persistence.entity;

import com.example.commonbase.persistence.LongTimestampedEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Created by AMe on 2020-07-09 14:26.
 */
@Data
@Entity
@Table(name = "access_record", indexes = {
    @Index(name = "idx_create_time", columnList = "utc_create")
})
@SQLDelete(sql = "update access_record set entity_status = 1 where id = ?")
@Where(clause = "entity_status <> 1")
public class AccessRecord extends LongTimestampedEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "req_url", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '请求地址'")
    private String reqUrl;
    @Column(name = "req_params", columnDefinition = "VARCHAR(1000) DEFAULT NULL COMMENT '请求参数'")
    private String reqParams;
    @Column(name = "visitor", columnDefinition = "VARCHAR(36) NOT NULL DEFAULT 'anonym' COMMENT '访问者'")
    private String visitor;
    @Column(name = "client_ip", columnDefinition = "VARCHAR(46) NOT NULL DEFAULT '' COMMENT '客户端IP'")
    private String clientIp;
    @Column(name = "user_agent", columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '客户端UA'")
    private String userAgent;
    @Column(name = "handler_class", columnDefinition = "VARCHAR(100) NOT NULL DEFAULT '' COMMENT '处理请求的类'")
    private String handlerClass;
    @Column(name = "handler_method", columnDefinition = "VARCHAR(50) NOT NULL DEFAULT '' COMMENT '处理请求的方法'")
    private String handlerMethod;
    @Column(name = "http_method", columnDefinition = "VARCHAR(10) NOT NULL DEFAULT '' COMMENT '请求方式'")
    private String httpMethod;
    @Column(name = "method_args", columnDefinition = "VARCHAR(1000) DEFAULT NULL COMMENT '方法参数'")
    private String methodArgs;
    @Column(name = "resp_content", columnDefinition = "TEXT DEFAULT NULL COMMENT '响应内容'")
    private String respContent;
    @Column(name = "resp_code", columnDefinition = "INT(11) NOT NULL DEFAULT '0' COMMENT '响应状态码'")
    private Integer respCode;
    @Column(name = "duration", columnDefinition = "INT(11) NOT NULL DEFAULT '0' COMMENT '持续时间'")
    private Integer duration;
    @Column(name = "scope", columnDefinition = "VARCHAR(20) DEFAULT NULL COMMENT '所属域'")
    private String scope;
    @Column(name = "entity_status", columnDefinition = "TINYINT(1) NOT NULL DEFAULT '0' COMMENT '数据状态'")
    private Byte entityStatus = 0;
}
