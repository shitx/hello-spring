/**
 * 
 */
package seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.shitx.seckill.dto.Exposer;
import com.shitx.seckill.dto.SecKillExecution;
import com.shitx.seckill.service.SecKillService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author shitx
 * @date 2016年11月19日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:seckill/spring/spring-dao.xml",
	"classpath:seckill/spring/spring-service.xml"})
public class SecKillServiceImplTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SecKillService secKillService;
	
//	@Test
//	public void testGetById() {
//		logger.info("*************************************");
//		long secKillId = 1001L;
//		SecKill secKill = secKillService.getById(secKillId);
//		if(secKill != null){
//			logger.info("getById="+secKill.toString());
//		}else{
//			logger.info("getById is null");
//		}
//		logger.info("*************************************");
//	}
	
//	@Test
//	public void testGetSecKillList() {
//		List<SecKill> secKills = secKillService.getSecKillList();
//		for(SecKill secKill : secKills){
//			logger.info("xxxxxxxxxxxxxxx{}",secKill.toString());
//		}
//	}

	//测试代码完整逻辑，注意可重复执行【】
//	@Test
//	public void testExportSecKillLogic() {
//		long id = 1001L;
//		Exposer exposer = secKillService.exportSecKillUrl(id);
//		if(exposer.isExpose()){
//			logger.info("yyyyyy={}",exposer.toString());
//			long userPhone = 15210837230L;
//			String md5= exposer.getMd5();
//		}else{
//			//秒杀未开启
//			logger.warn("exposer={}",exposer);
//		}
//	}


//	@Test
//	public void testExecuteSecKill() throws Exception {
//		long id = 1000L;
//		long userPhone = 15210837230L;
//		String md5="42482625eee65a8d8b5635fd622ab0d4";
//		try {
//			SecKillExecution secKillExecution = secKillService.executeSecKill(id, userPhone, md5);
//			logger.info("zzzzzzzz{}",secKillExecution.toString());
//		} catch (RepeatKillException e1) { //e1,e2是允许的异常
//			logger.error(e1.getMessage());
//		}catch (SecKillException e2) {
//			logger.error(e2.getMessage());
//		}//其他的异常才抛给上一级
//	}

	//测试代码完整逻辑，注意可重复执行【】通过存储过程
	@Test
	public void testExportSecKillProcedure() {
		long id = 1000L;
		Exposer exposer = secKillService.exportSecKillUrl(id);
		if(exposer.isExpose()){
			logger.info("yyyyyy={}",exposer.toString());
			long userPhone = 15210837230L;
			String md5= exposer.getMd5();
			SecKillExecution secKillExecution = secKillService.executeSecKillProducedure(id, userPhone, md5);
			logger.info(secKillExecution.getStateInfo());
		}else{
			//秒杀未开启
			logger.warn("exposer={}",exposer);
		}
	}
	
}
