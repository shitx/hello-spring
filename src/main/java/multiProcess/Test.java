/**
 * 
 */
package multiProcess;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

import org.zeromq.ZMQException.IOException;

/**
 * @author shitx
 *
 */
public class Test {
	public void run(){
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ipconfig /all");
		} catch(IOException | java.io.IOException  e) {
		  System.out.println(e.getMessage());
		}
		try {
		    String line;
		    InputStream is = process.getInputStream();
		    BufferedReader br = new BufferedReader(new InputStreamReader(is,"GBK"));
		        
		    while(null != (line = br.readLine())){
		        System.out.println(line);
		    }
		    System.out.println("---------------------------------------------------------------------------------------");
		    InputStream is_error = process.getErrorStream();
		    BufferedReader br_error = new BufferedReader(new InputStreamReader(is_error,"GBK"));
		        
		    while(null != (line = br_error.readLine())){
		        System.out.println(line);
		    }
		} catch (IOException | java.io.IOException e) {
		    e.printStackTrace();
		}
	}
	public static void main(String args[]){
		Test test = new Test();
		test.run();
		System.out.println("父进程名字："+ManagementFactory.getRuntimeMXBean().getName());
	}
}
