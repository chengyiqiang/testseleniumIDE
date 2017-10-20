package demo1;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class testURTV2 {

	/**
	 * @param args
	 */
	
	private WebDriver driver;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testURTV2 tv2 = new testURTV2();
		try{
			tv2.openChrome();
			tv2.init();
			tv2.login("chengyiqiang","123456");  //正确的账号密码
			Thread.sleep(2000);
			tv2.selector(By.id("ctl00_CP1_ddlFilterOpenClose"),"OPEN");
			tv2.creatNewTransaction();
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
	
	//打开
	public void init() throws Exception {
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		driver.get("http://192.168.50.199:800/pts/home.aspx");
	}
	
	//登录事务系统
	public void login (String username, String passwd) throws Exception {
		driver.findElement(By.id("txtEmail")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(passwd);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(1000);
//		String loginResult = driver.findElement(By.id("lblLoginResult")).getText();
//		System.out.println(loginResult);
//		if (loginResult.contains("登录失败")){
//			this.screenShot2();
//		}
	}
	
	//下拉框选择
	public void selector (By by,String value) throws Exception {
		Select select = new Select(driver.findElement(by));
		select.selectByValue(value);		
	}
	
	//新建事务
	public void creatNewTransaction () throws Exception {
		driver.findElement(By.id("ctl00_Siteheader1_lnkAddProblem")).click();
		Thread.sleep(2000);
		//判断是否存在“网约车”这个元素
		driver.switchTo().frame("TB_iframeContent");
		if(this.waitForElement(By.id("ctl00_CP1_tvProjectst10"))){
			System.out.println("打开新建iframe成功");
//			this.screenShot2();
		}else{
			System.out.println("打开新建iframe失败");
			this.screenShot2();
		}
		driver.findElement(By.id("ctl00_CP1_tvProjectst10")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("ctl00_CP1_rblAvailableInitStates_1")).click();
		driver.findElement(By.id("ctl00_CP1_AssigneeSelector1_userSelector_imgDropDownJiTuan")).click();
		driver.findElement(By.xpath("//a[@class='user_link' and @data-name='高小平']")).click();
		driver.findElement(By.id("ctl00_CP1_pe_F_Title")).sendKeys("");
		this.selector(By.id("ctl00_CP1_pe_F_ProblemPriorityID"), "128");
		driver.findElement(By.id("ctl00_CP1_FileUpload1")).click(); //不可用，找原因中................
		Thread.sleep(3000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		
//		driver.findElement(By.id("ctl00_CP1_FileUpload1")).sendKeys("C:\\Users\\Fernando\\Desktop\\应用宝截屏\\自动化测试截图\\20170301112744.jpg");
		Thread.sleep(3000);
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
	
	//等待30S，判断一个元素是否存在
	public boolean waitForElement (By by) throws Exception {
		boolean result = false;
		for (int second=0;second<=30;second++){
			try{
				driver.findElement(by);
				result = true;
				break;
			}catch (Exception e){
				Thread.sleep(1000);
			}
		}
		return result;
	}
	
	
	
	//退出
	public void free() throws Exception{
		driver.quit();
	}
	

}
