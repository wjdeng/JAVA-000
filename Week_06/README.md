# 学习笔记

本周完成了必做题：基于电商交易场景（用户、商品、订单），设计一套简单的表结构

```
#创建数据库

CREATE SCHEMA `db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
use db;

#用户表
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `passport` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `id_card` char(18) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户身份证号码',
  `email` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否逻辑删除',
  `status` tinyint(1) DEFAULT '1' COMMENT '用户状态',
  `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updated_at` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_card_UNIQUE` (`id_card`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='电商场景-用户表';


#订单表

CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL COMMENT '订单id',
  `seller_id` bigint(20) DEFAULT NULL COMMENT '卖家id',
  `buyer_id` bigint(20) DEFAULT NULL COMMENT '买家id',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `status` tinyint(1) DEFAULT '1' COMMENT '订单状态',
  `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updated_at` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `seller` (`seller_id`),
  KEY `buyer` (`buyer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='电商场景-订单表';

#商品表
CREATE TABLE `tb_goods` (
  `id` bigint(20) NOT NULL COMMENT '商品id',
  `goods_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `seller_id` bigint(20) NOT NULL COMMENT '卖家id',
  `first_category` int(11) DEFAULT NULL COMMENT '第一级分类',
  `second_category` int(11) DEFAULT NULL COMMENT '第二级分类',
  `weight` decimal(10,3) NOT NULL COMMENT '商品重量',
  `unit` tinyint(2) NOT NULL COMMENT '商品单位',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updated_at` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `seller` (`seller_id`),
  KEY `goods_name` (`goods_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='电商场景-商品表';




```
