/**
 * 
 */
package com.shitx.seckill.dao.cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.shitx.seckill.entity.SecKill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * @author shitx
 *
 */
public class RedisDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private JedisPool jedisPool;
	
	/**
	 * @param jedisPool
	 */
	public RedisDao(JedisPool jedisPool) {
		super();
		this.jedisPool = jedisPool;
	}

	/**
	 * @return the jedisPool
	 */
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	/**
	 * @param jedisPool the jedisPool to set
	 */
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	private RuntimeSchema<SecKill> schema = RuntimeSchema.createFrom(SecKill.class );
	
	public SecKill  getSecKill(Long secKillId) {
		//redis操作逻辑
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "secKill:" + secKillId;
				//redis没有实现内部序列化和反序列化操作，我们要用开源的序列和反序列   get->byte[]    -> 反序列化 ->Object(SecKill)  
				//
				byte[] bytes = jedis.get(key.getBytes());
				if(bytes != null){
					SecKill secKill = schema.newMessage(); //空对象，执行下面的代码会进行反序列化，和原生的jdk序列化比，压缩达到10：1
					ProtostuffIOUtil.mergeFrom(bytes, secKill, schema);
					return secKill;
				}
			} catch (Exception e1) {
				
			}finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	public String putSecKill(SecKill secKill){
		//set Object(SecKill) -> 序列化 -> bytes[]
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "secKill:" + secKill.getSecKillId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(secKill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				//超时缓存
				int timeout = 60 * 60; //1hour
				String result = jedis.setex(key.getBytes(), timeout, bytes);  //正确是OK,错误是错误信息
				return result;
			} catch (Exception e) {
				
			}finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		return null;
	}
}
