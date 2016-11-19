-- 创建数据库
CREATE TABLE seckill(
seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
name VARCHAR(120) NOT NULL COMMENT '商品名称',
number INT NOT NULL COMMENT '库存数量',
start_time TIMESTAMP NULL COMMENT '秒杀开始时间',
end_time TIMESTAMP NULL COMMENT '秒杀结束时间',
create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET = utf8 COMMENT='秒杀库存表';

-- 初始化数据
insert into 
	seckill(name,number,start_time,end_time)
values
	('1000元秒杀iphone6',100,'2016-11-11 00:00:00','2016-11-12 00:00:00'),
	('500元秒杀ipad2',200,'2016-11-11 00:00:00','2016-11-12 00:00:00'),
	('300元秒杀小米4',300,'2016-11-11 00:00:00','2016-11-12 00:00:00'),
	('200元秒杀红米note',400,'2016-11-11 00:00:00','2016-11-12 00:00:00');
	
-- 秒杀成功明细表
-- 用户登陆认证信息 no
create table success_killed(
seckill_id BIGINT NOT NULL COMMENT '秒杀商品id',
user_phone BIGINT NOT NULL COMMENT '用户手机号',
state tinyint NOT NULL DEFAULT -1 COMMENT '-1无效 , 0：成功 , 1：已付款 , 2:已发货',
create_time timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY(seckill_id,user_phone), /*联合主键*/
KEY idx_create_time(create_time)
)ENGINE=innoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';
