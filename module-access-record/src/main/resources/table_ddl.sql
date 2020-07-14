CREATE TABLE `access_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `req_url` varchar(100) NOT NULL DEFAULT '' COMMENT '请求地址',
  `visitor` varchar(36) NOT NULL DEFAULT 'anonym' COMMENT '访问者',
  `client_ip` varchar(46) NOT NULL DEFAULT '' COMMENT '客户端IP',
  `user_agent` varchar(255) NOT NULL DEFAULT '' COMMENT '客户端UA',
  `handler_class` varchar(100) NOT NULL DEFAULT '' COMMENT '处理请求的类',
  `handler_method` varchar(50) NOT NULL DEFAULT '' COMMENT '处理请求的方法',
  `req_params` varchar(1000) DEFAULT NULL COMMENT '请求参数',
  `resp_content` varchar(65535) DEFAULT NULL COMMENT '响应内容',
  `duration` int(11) NOT NULL DEFAULT '0' COMMENT '持续时间',
  `scope` varchar(20) DEFAULT NULL COMMENT '所属域',
  `entity_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '数据状态',
  `utc_create` datetime NOT NULL,
  `utc_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_create_time` (`utc_create`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;