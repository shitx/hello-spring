/**
 * 
 */
package seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shitx.seckill.dao.SecKillDao;
import com.shitx.seckill.dao.SuccessKilledDao;
import com.shitx.seckill.entity.SecKill;
import com.shitx.seckill.entity.SuccessKilled;

/**
 * @author shitx
 *
 */

@RunWith(SpringJUnit4ClassRunner.class) //加载容器
@ContextConfiguration({"classpath:seckill/spring/spring-dao.xml"}) //容器根据xml加载对应beans
public class SuccessKilledDaoTest {
	
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//注入BEAN
	@Autowired  //或者@Resource
	private SuccessKilledDao successKilledDao;
	
	
	@Test
	public void queryByIdWithSecKill(){
		logger.info("*************************************");
		long id = 1001L;
		SuccessKilled secKilled = successKilledDao.queryByIdWithSecKill(id);
		if(null != secKilled){
			logger.info("secKilled queryById result=" + secKilled.toString());
		}else{
			System.out.println("secKilled queryById is null");
		}
		logger.info("*************************************");
	}
	
	@Test
	public void insertSuccessKilled(){
		logger.info("*************************************");
		long userPhone = 15210837230L;
		long secKillId = 1000L;
		int updateCount = successKilledDao.insertSuccessKilled(secKillId, userPhone);
		logger.info("reduceNumber=" + updateCount);
		logger.info("*************************************");
	}
	
}
