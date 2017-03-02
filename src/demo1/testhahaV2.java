package demo1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class testhahaV2 {

	/**
	 * @param args
	 */
	
	private WebDriver driver;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testhahaV2 tv2 = new testhahaV2();
		try{
			tv2.openFirefox();
			tv2.init();
			tv2.login();
			Thread.sleep(2000);
			tv2.selector();
			tv2.screenShot2();
			tv2.free();
		}catch (Exception e){
			System.out.println(e.getStackTrace());
		}

	}
	
	//使用火狐浏览器
	public void openFirefox () throws Exception {
		driver= new FirefoxDriver();
	}
	
	//使用谷歌浏览器
	public void openChrome() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fernando\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	
	//打开百度
	public void init() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://192.168.50.199:800/pts/home.aspx");
	}
	
	//登录事务系统
	public void login () throws Exception {
		driver.findElement(By.id("txtEmail")).sendKeys("chengyiqiang");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("btnLogin")).click();
	}
	
	//下拉框选择
	public void selector () throws Exception {
		Select select = new Select(driver.findElement(By.id("ctl00_CP1_ddlFilterOpenClose")));
		select.selectByValue("OPEN");		
	}
	
	//截图1
	public void screenShot1 () throws Exception {
		TakesScreenshot tss = (TakesScreenshot)driver;
		File image = tss.getScreenshotAs(OutputType.FILE);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式 
		String nowtime = dateFormat.format(now);
		FileUtils.copyFile(image, new File("C:\\Users\\Fernando\\Desktop\\应用宝截屏\\自动化测试截图\\"+ nowtime +".jpg"));
	}
	//截图2
	public void screenShot2 () throws Exception {
		//获取当前时间
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String imagename = sdf.format(now);
		
		//文件名
		String path = System.getProperty("user.dir") + "/screenshot/";
		String name = imagename + ".jpg";
		
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //截图照片保存在文件image中
		FileUtils.copyFile(image, new File(path+name));
	}
	
	//退出
	public void free() throws Exception{
		driver.quit();
	}
	

}
