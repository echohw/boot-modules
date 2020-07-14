CREATE TABLE `common_classify` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `value_remarks` varchar(100) DEFAULT NULL,
  `group` varchar(36) NOT NULL,
  `scope` varchar(36) NOT NULL,
  `pid` varchar(36) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `utc_create` datetime NOT NULL,
  `utc_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_scope_group_name` (`scope`,`group`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;