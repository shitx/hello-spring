/**
 * 
 */
package com.shitx.seckill.dto;

/**
 * 数据传输层。类似于实体层，方便service返回数据以DTO形式的对象返回。
 * @author shitx
 *
 */
public class Exposer {

	private boolean expose;
	
	//加密措施
	private String md5;
	
	private Long secKillId;
	
	//系统当前时间(毫秒)
	private Long now;

	//
	private Long start;
	
	private Long end;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exposer [expose=" + expose + ", md5=" + md5 + ", secKillId=" + secKillId + ", now=" + now + ", start="
				+ start + ", end=" + end + "]";
	}

	/**
	 * @param expose
	 * @param secKillId
	 * @param now
	 * @param start
	 * @param end
	 */
	public Exposer(boolean expose, Long secKillId, Long now, Long start, Long end) {
		super();
		this.expose = expose;
		this.secKillId = secKillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * @param expose
	 * @param md5
	 * @param secKillId
	 */
	public Exposer(boolean expose, String md5, Long secKillId) {
		super();
		this.expose = expose;
		this.md5 = md5;
		this.secKillId = secKillId;
	}

	/**
	 * @param expose
	 * @param secKillId
	 */
	public Exposer(boolean expose, Long secKillId) {
		super();
		this.expose = expose;
		this.secKillId = secKillId;
	}

	/**
	 * @param expose
	 * @param now
	 * @param start
	 * @param end
	 */
	public Exposer(boolean expose, Long now, Long start, Long end) {
		super();
		this.expose = expose;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	/**
	 * @return the expose
	 */
	public boolean isExpose() {
		return expose;
	}

	/**
	 * @param expose the expose to set
	 */
	public void setExpose(boolean expose) {
		this.expose = expose;
	}

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5 the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return the secKillId
	 */
	public Long getSecKillId() {
		return secKillId;
	}

	/**
	 * @param secKillId the secKillId to set
	 */
	public void setSecKillId(Long secKillId) {
		this.secKillId = secKillId;
	}

	/**
	 * @return the now
	 */
	public Long getNow() {
		return now;
	}

	/**
	 * @param now the now to set
	 */
	public void setNow(Long now) {
		this.now = now;
	}

	/**
	 * @return the start
	 */
	public Long getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Long start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Long getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Long end) {
		this.end = end;
	}
}
