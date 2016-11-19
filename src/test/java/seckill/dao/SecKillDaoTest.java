/**
 * 
 */
package seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shitx.seckill.dao.SecKillDao;
import com.shitx.seckill.entity.SecKill;

import sun.util.logging.resources.logging;

/**
 * @author shitx
 *
 */

@RunWith(SpringJUnit4ClassRunner.class) //加载容器
@ContextConfiguration({"classpath:seckill/spring/spring-dao.xml"}) //容器根据xml加载对应beans
public class SecKillDaoTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入BEAN
	@Autowired  //或者@Resource
	private SecKillDao secKillDao;
	
	
	@Test
	public void queryById(){
		logger.info("*************************************");
		long id = 1000;
		SecKill secKill = secKillDao.queryById(id);
		logger.info(secKill.toString());
		logger.info("*************************************");
	}
	
	@Test
	public void reduceNumber(){
		logger.info("*************************************");
		Date killTime = new Date(); 
		int updateCount = secKillDao.reduceNumber(1000L, killTime);
		logger.info("reduceNumber=" + updateCount);
		logger.info("*************************************");
	}
	
	@Test
	public void queryAll(){
		logger.info("*************************************");
		List<SecKill> secKills = secKillDao.queryAll(0, 100);
		for(SecKill secKill : secKills){
			logger.info(secKill.toString());
		}
		logger.info("*************************************");
	}
}
