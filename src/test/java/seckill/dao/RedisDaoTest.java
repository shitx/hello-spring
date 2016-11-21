/**
 * 
 */
package seckill.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shitx.seckill.dao.SecKillDao;
import com.shitx.seckill.dao.cache.RedisDao;
import com.shitx.seckill.entity.SecKill;

/**
 * @author shitx
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:seckill/spring/spring-dao.xml"})
public class RedisDaoTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RedisDao redisDao;
	
	@Autowired
	SecKillDao secKillDao;
	
	@Test
	public void testGetSecKill(){
		Long secKillId = 1000L;
		SecKill secKill = redisDao.getSecKill(secKillId);
		if(secKill == null){
			secKill = secKillDao.queryById(secKillId);
			if(secKill != null){
				String result = redisDao.putSecKill(secKill);
				logger.info(result);
				redisDao.getSecKill(secKillId);
				logger.info(secKill.toString());
			}
		}else{
			logger.info("succeed:"+secKill.toString());
		}
	}
}
