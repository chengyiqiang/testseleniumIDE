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
		selenium.click("link=登录");
		if(isElementPresent("id=login_button"))
			System.out.println("进入登录");
		else
			System.out.println("进入失败");
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
	
	//60秒内是否出现对应组件,有返回true，没有返回false
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
			selenium.captureScreenshot("C:\\Users\\Fernando\\Desktop\\应用宝截屏\\selenium截图\\test.jpg"); //截图保存
		}
		return true;
	}
	
	
	//
	
}
