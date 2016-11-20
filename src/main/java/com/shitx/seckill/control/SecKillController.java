/**
 * 
 */
package com.shitx.seckill.control;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shitx.seckill.dto.Exposer;
import com.shitx.seckill.dto.SecKillExecution;
import com.shitx.seckill.dto.SecKillResult;
import com.shitx.seckill.entity.SecKill;
import com.shitx.seckill.enums.SecKillStatEnum;
import com.shitx.seckill.exception.RepeatKillException;
import com.shitx.seckill.exception.SecKillCloseException;
import com.shitx.seckill.service.SecKillService;
import com.sun.org.apache.bcel.internal.generic.FADD;

/**
 * @author shitx
 *
 */

@Controller
@RequestMapping("/seckill") //url: 模块/资源/{id}/细分   /seckill/list
public class SecKillController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SecKillService SecKillService;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String details(@PathVariable("id") String id){
		logger.info("id="+id);   
		return "xx";
	}
	
	//详情页
	@RequestMapping(value="/{secKillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable Long secKillId, Model model){
		if(secKillId == null){
			return "redirect:/seckill/list";
		}
		SecKill secKill = SecKillService.getById(secKillId);
		if(secKill == null){
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", secKill);
		return "detail";
	}
	
	@GetMapping("/list")
	public String list(Model model){
		//获取列表页
		List<SecKill> list = SecKillService.getSecKillList();
		model.addAttribute("list",list);
		//list.jsp + model = ModelAndView
		return "list"; //其实返回的是 "/WEB-INF/jsp/"list".jsp"
	}
	
	//ajax json[返回JSON结果，结果信息中应该还有success,error,data这些，所以我们专门写个类封装结果]
	@RequestMapping(value="/{secKillId}/exposer",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"}) //解决JSON中中文乱码问题
	@ResponseBody  //告诉SPRINGMVC，这里返回的是对象，但是要转为JSON，所以resolver会把它封装为JSON
	public SecKillResult<Exposer>  exposer(@PathVariable("secKillId") Long secKillId){
		SecKillResult<Exposer> result;
		
		try {
			Exposer exposer = SecKillService.exportSecKillUrl(secKillId);
			result = new SecKillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result = new SecKillResult<Exposer>(false, e.getMessage());
		}
	
		return result;
	}
	
	@RequestMapping(value="/{secKillId}/{md5}/exucution",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SecKillResult<SecKillExecution> execute(@PathVariable("secKillId") Long secKillId, 
			@PathVariable("md5") String md5,
			@CookieValue(value="killPhone", required = false) Long userPhone){  //手机号不放在url中，而是设置在cookie中
		if(userPhone == null){
			return new SecKillResult<SecKillExecution>(false, "未注册");
		}
		
		SecKillResult< SecKillExecution> result;
		try {   //注意我们在controller中处理所有的异常！！！包括重复秒杀，
			//秒杀结束，以及其他的内部错误。所以我们的result是要返回给resolver的对象，它要转为JSON的
			//它有是否成功的标识，如果成功有数据【secKillExecution对象】，如果成功则有我们自定义的异常对象！
			SecKillExecution secKillExecution = SecKillService.executeSecKill(secKillId, userPhone, md5);
			return new SecKillResult<SecKillExecution>(true, secKillExecution);
		} catch(SecKillCloseException e1){
			SecKillExecution execution = new SecKillExecution(secKillId, SecKillStatEnum.END);
			return new SecKillResult<SecKillExecution>(false, execution);
		}catch (RepeatKillException e2) {
			SecKillExecution execution = new SecKillExecution(secKillId, SecKillStatEnum.REPEAT_KILL);
			return new SecKillResult<SecKillExecution>(false, execution);
		}
		catch (Exception e) {
			logger.error(e.getMessage(),e);
//			return new SecKillResult<SecKillExecution>(false, e.getMessage());
			SecKillExecution execution = new SecKillExecution(secKillId, SecKillStatEnum.INNER_ERROR);
			return new SecKillResult<SecKillExecution>(false, execution);
		}
	}
	
	//获取服务器当前时间
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	public SecKillResult<Long> time() {
		Date date = new Date();
		return new SecKillResult<Long>(true, date.getTime());
	}
}