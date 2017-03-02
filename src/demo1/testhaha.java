package demo1;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class testhaha {

	/**
	 * @param args
	 */
	protected static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testhaha th = new testhaha();
		try {
			th.init();
			th.dotest();
		    driver.findElement(By.linkText("哈哈.MX——分享所有好笑的事情")).click();
			Thread.sleep(5000);
			th.changewindow();//切换到窗口
			th.dosearch("测试");
			th.free();
			
//			xpath=(//a[contains(text(),'搜索历史')])[2]
//					<a target="_blank" href="http://i.baidu.com/my/history?from=ps">搜索历史</a>
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}
	

	
	public void init() throws Exception{
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fernando\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//全局设置，当元素识别不到的时候，可以接受的最大等待时间
	    driver.manage().window().maximize();
	    driver.get("http://www.baidu.com/");
	}

	public void dotest() throws Exception{
		driver.findElement(By.id("kw")).click();
		driver.findElement(By.id("kw")).clear();
	    driver.findElement(By.id("kw")).sendKeys("haha");
	    if(this.waitelement(By.linkText("哈哈.MX——分享所有好笑的事情"))){
	    	System.out.println("百度搜索“哈哈”成功");	 
	    }else{
	    	System.out.println("百度搜索“哈哈”失败");	 
	    }
	}
	
	// 弹出新窗口，切换到新窗口中
	public void changewindow() throws Exception {
		// 得到当前窗口的句柄
		String currentWindow = driver.getWindowHandle();
		// 得到所有窗口的句柄，set方法可以方便地将需要的类型，以集合类型保存在一个变量中，Set是一个不包含重复元素的collection
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// hasNext检查序列中是否还有元素
		while (it.hasNext()) {
			// 第一次调用Iterator的next()方法时，它返回序列的第一个元素，使用next()获得序列中的下一个元素
			String handle = it.next();
			if (currentWindow.equals(handle))
				continue;
			driver.close();
			driver.switchTo().window(handle);
		}
	}
	
	
	//搜索相关内容
	public void dosearch (String search) throws Exception {
	    driver.findElement(By.id("key")).clear();
	    driver.findElement(By.id("key")).sendKeys(search);
	    
	    driver.findElement(By.cssSelector("button.header-top-btn-search.fr")).click();	    
	//	driver.findElement(By.className("header-top-btn-search fr")).click();     //此方法不可用，正在找原因中.........
		if (this.waitelement(By.className("search-highlight"))){
			if (driver.findElement(By.className("search-highlight")).getText().equals(search)){
//			if (driver.findElement(By.className("search-highlight")).getText() == search){
				System.out.println(search + "搜索成功");
			}
			else{
				System.out.println(search + "搜索失败");
			}
		}
		else{
			System.out.println(search + "搜索出错");
		}
	}
	



	//等待30s并判断是否存在某个元素
	public boolean waitelement(By by) throws Exception{
		boolean isexist=false;
		for(int second=1; second <10; second++){
		 try {
		      driver.findElement(by);
		      isexist=true;
		      break;
		    } catch (NoSuchElementException e) {
		      e.printStackTrace();
		    }
		 Thread.sleep(1000);
		}
		return isexist;
	}
	
	//后退再前进
	public void forwardback (){
		
	}
	
	
	//关闭浏览器，释放资源
	public void free() throws Exception{
		driver.quit();
	}

}
