package demo1;

import com.thoughtworks.selenium.*;

import static org.junit.Assert.*;

import java.util.regex.Pattern;


public class testbaidufinal {
	
	private Selenium selenium;
	
	public static void main(String[] args) {
		testbaidufinal tbf = new testbaidufinal();
		try{
			tbf.before();
			tbf.dologin();
		}
		catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}
		

	
		

	}
	
	public void before () throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome C:/Program Files (x86)/Mozilla Firefox/firefox.exe", "http://demo.jdyg.com/");
		selenium.start();
		selenium.windowFocus();
		selenium.windowMaximize();
		selenium.open("/");
		Thread.sleep(3000);
		selenium.click("link=��¼");
		if(isElementPresent("id=login_button"))
			System.out.println("�����¼");
		else
			System.out.println("����ʧ��");
	}
	
	public void dologin () throws Exception{
		selenium.type("id=uname","csy15888");
		selenium.type("id=pword","789632145xzj");
		selenium.click("id=login_button");
        
	}
	
	public void addgoods() {
		
	}
	
	public void delectgoods() {
		
	}
	
	//60�����Ƿ���ֶ�Ӧ���,�з���true��û�з���false
	public boolean isElementPresent (String link) throws Exception {
		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
				return false;
			}
				
			try { if (selenium.isElementPresent(link))
				break; } 
			catch (Exception e) {
				
			}
			Thread.sleep(1000);
			selenium.captureScreenshot("C:\\Users\\Fernando\\Desktop\\Ӧ�ñ�����\\selenium��ͼ\\test.jpg"); //��ͼ����
		}
		return true;
	}
	
	
	//
	
}
