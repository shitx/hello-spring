/**
 * 
 */
package com.shitx.autowiring;

/**
 * @author shitx
 *
 */
public class AutoWiringService1 {
	private AutoWiringDAO autoWiringDAO;

	/**
	 * @param autoWiringDAO the autoWringDao to set
	 */
	public void setAutoWiringDAO(AutoWiringDAO autoWiringDAO) {
		this.autoWiringDAO = autoWiringDAO;
	}
	
	public void say(String word){
		this.autoWiringDAO.say(word);
	}
}
