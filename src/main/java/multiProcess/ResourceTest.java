/**
 * 
 */
package multiProcess;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;


/**
 * @author shitx
 *
 */
public class ResourceTest {

	public Properties getProperties(){
		Properties properties = new Properties();
		URL url =  this.getClass().getResource("/config.properties");
		InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	public Properties getXML(){
		Properties properties = new Properties();
		InputStream inputStream = this.getClass().getResourceAsStream("/rr.xml");
		try {
			properties.loadFromXML(inputStream);
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceTest resourceTest = new ResourceTest();
//		Properties properties = resourceTest.getProperties();
//		System.out.println(properties.get("jdbc.url"));
		
		Properties properties = resourceTest.getXML();
		System.out.println(properties);
	}

}
