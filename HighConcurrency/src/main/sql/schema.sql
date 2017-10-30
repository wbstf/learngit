-- 数据库初始化脚本
-- 创建数据库表
DROP DATABASE IF EXISTS  seckill;
create DATABASE seckill ;
-- 使用数据库
use seckill;
-- 创建秒杀库存表
CREATE TABLE seckill(
  seckill_id BIGINT NOT NULL  AUTO_INCREMENT   COMMENT '商品库存id',
  name VARCHAR(120)  NOT NULL  COMMENT '商品名称',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  numbers INT NOT NULL COMMENT '库存数量',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀的结束时间',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
) ENGINE =InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET =utf8 COMMENT ='库存秒杀表';

-- 初始化数据

INSERT into seckill(name, numbers, start_time, end_time)
VALUES
  ('1000元秒杀iPhone6s',100,'2015-11-01 00:00:00','2015-11-02 00:00:00');
INSERT into seckill(name, numbers, start_time, end_time)
VALUES('800元秒杀iPad',200,'2015-11-01 00:00:00','2015-11-02 00:00:00');
INSERT into seckill(name, numbers, start_time, end_time)
VALUES('100元秒杀mi',300,'2015-11-01 00:00:00','2015-11-02 00:00:00');
INSERT into seckill(name, numbers, start_time, end_time)
VALUES('500元秒杀note',400,'2015-11-01 00:00:00','2015-11-02 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE TABLE success_killed (
  seckill_id INT NOT NULL COMMENT '秒杀商品id',
  user_phone INT NOT NULL COMMENT '用户手机号',
  state TINYINT NOT NULL  DEFAULT  -1 COMMENT '状态表示:-1 无效 0:成功  1:已付款 2:已发货' ,
  create_time TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),/** 联合主键*/
  key idx_create_time(create_time)
)ENGINE=InnoDB  DEFAULT CHARSET =utf8 COMMENT '秒杀成功明细表' ;

-- 控制台库连接