package demo1;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import bsh.This;

public class mouseKeyboard {
	
	private WebDriver driver;
	private Actions action;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      mouseKeyboard mk=new mouseKeyboard();
      try{
    	  mk.init();
    	  mk.action.keyDown(Keys.CONTROL);

//    	  mk.click();
//    	  mk.switchlable();
//    	  mk.screenShot();
//    	  mk.quit();
//    	  mk.switchLable();
//    	  mk.login();
    	  
//    	  Thread.sleep(5000);
//    	  mk.rightClick();

      }catch (Exception e){
    	  System.out.println(e.getStackTrace());
      }
     
      
	}
	
	public void init() throws Exception {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fernando\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
//		driver = new ChromeDriver();                 //�����ȸ������
		driver = new FirefoxDriver();
		action = new Actions(driver);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//ȫ�����ã���Ԫ��ʶ�𲻵���ʱ�򣬿��Խ��ܵ����ȴ�ʱ��
	    driver.manage().window().maximize();
	    driver.get("http://www.baidu.com/");
	}
	
	public void click() throws Exception {
//		action.click(driver.findElement(By.id("kw"))).perform();
//		action.click(driver.findElement(By.name("tj_trtieba"))).perform();
//		action.click(driver.findElement(By.xpath("//a[@href='http://tieba.baidu.com' and @name='tj_trtieba' and @class='mnav']"))).perform();
		action.contextClick(driver.findElement(By.xpath("//a[@href='http://tieba.baidu.com' and @name='tj_trtieba' and @class='mnav']"))).sendKeys("W").perform();
//		Thread.sleep(2000);
//		action.sendKeys("T").perform();
		Thread.sleep(5000);
		for (String handle: driver.getWindowHandles()){
			driver.switchTo().window(handle);
			if(driver.getTitle().contains("�ٶ�����"))
			break;
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@class='btn_login' and @href='#']")).click();
//		action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).keyUp(Keys.CONTROL).perform();//�����ã���֪��ԭ��
	}
	
	//�Ҽ����±�ǩ���л�����ǩ
	public void switchlable() throws Exception{
		action.contextClick(driver.findElement(By.xpath("//a[@href='http://tieba.baidu.com' and @name='tj_trtieba' and @class='mnav']"))).sendKeys("T").perform();
		Thread.sleep(5000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(5000);
		for (String handle:driver.getWindowHandles()){
			driver.switchTo().window(handle);
			if(driver.getTitle().contains("�ٶ�����"))
				break;
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@class='btn_login' and @href='#']")).click();
	}
	

	
	//����
	public void screenShot () throws Exception {
		//��ȡ��ǰʱ��
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String imagename = sdf.format(now);
		
		//�ļ���
		String path = System.getProperty("user.dir") + "//screenshot//";
		String name = imagename + ".png";
		
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //��ͼ��Ƭ�������ļ�image��
		FileUtils.copyFile(image, new File(path+name));
	}
	
	//�˳�
	public void quit() throws Exception{
		driver.quit();
	}
	
	public void mouseMove(){
		
	}

}
