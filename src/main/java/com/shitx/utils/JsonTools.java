/**
 * 
 */
package com.shitx.utils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author shitx
 *
 */
public class JsonTools {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonTools.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static String toJson(Object o){
		try {
			return objectMapper.writeValueAsString(o);
		} catch (Exception e) {
			LOGGER.error("[※对象转Json]失败：" + e.getMessage());
		}
		return "";
	}
}
