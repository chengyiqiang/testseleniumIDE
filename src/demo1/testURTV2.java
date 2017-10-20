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
			tv2.login("chengyiqiang","123456");  //��ȷ���˺�����
			Thread.sleep(2000);
			tv2.selector(By.id("ctl00_CP1_ddlFilterOpenClose"),"OPEN");
			tv2.creatNewTransaction();
			tv2.free();
		}catch (Exception e){
			System.out.println(e.getStackTrace());
		}

	}
	
	//ʹ�û�������
	public void openFirefox () throws Exception {
		driver= new FirefoxDriver();
	}
	
	//ʹ�ùȸ������
	public void openChrome() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fernando\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	
	//��
	public void init() throws Exception {
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		driver.get("http://192.168.50.199:800/pts/home.aspx");
	}
	
	//��¼����ϵͳ
	public void login (String username, String passwd) throws Exception {
		driver.findElement(By.id("txtEmail")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(passwd);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(1000);
//		String loginResult = driver.findElement(By.id("lblLoginResult")).getText();
//		System.out.println(loginResult);
//		if (loginResult.contains("��¼ʧ��")){
//			this.screenShot2();
//		}
	}
	
	//������ѡ��
	public void selector (By by,String value) throws Exception {
		Select select = new Select(driver.findElement(by));
		select.selectByValue(value);		
	}
	
	//�½�����
	public void creatNewTransaction () throws Exception {
		driver.findElement(By.id("ctl00_Siteheader1_lnkAddProblem")).click();
		Thread.sleep(2000);
		//�ж��Ƿ���ڡ���Լ�������Ԫ��
		driver.switchTo().frame("TB_iframeContent");
		if(this.waitForElement(By.id("ctl00_CP1_tvProjectst10"))){
			System.out.println("���½�iframe�ɹ�");
//			this.screenShot2();
		}else{
			System.out.println("���½�iframeʧ��");
			this.screenShot2();
		}
		driver.findElement(By.id("ctl00_CP1_tvProjectst10")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("ctl00_CP1_rblAvailableInitStates_1")).click();
		driver.findElement(By.id("ctl00_CP1_AssigneeSelector1_userSelector_imgDropDownJiTuan")).click();
		driver.findElement(By.xpath("//a[@class='user_link' and @data-name='��Сƽ']")).click();
		driver.findElement(By.id("ctl00_CP1_pe_F_Title")).sendKeys("");
		this.selector(By.id("ctl00_CP1_pe_F_ProblemPriorityID"), "128");
		driver.findElement(By.id("ctl00_CP1_FileUpload1")).click(); //�����ã���ԭ����................
		Thread.sleep(3000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		
//		driver.findElement(By.id("ctl00_CP1_FileUpload1")).sendKeys("C:\\Users\\Fernando\\Desktop\\Ӧ�ñ�����\\�Զ������Խ�ͼ\\20170301112744.jpg");
		Thread.sleep(3000);
	}
	

	//��ͼ1
	public void screenShot1 () throws Exception {
		TakesScreenshot tss = (TakesScreenshot)driver;
		File image = tss.getScreenshotAs(OutputType.FILE);
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//���Է�����޸����ڸ�ʽ 
		String nowtime = dateFormat.format(now);
		FileUtils.copyFile(image, new File("C:\\Users\\Fernando\\Desktop\\Ӧ�ñ�����\\�Զ������Խ�ͼ\\"+ nowtime +".jpg"));
	}
	//��ͼ2
	public void screenShot2 () throws Exception {
		//��ȡ��ǰʱ��
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String imagename = sdf.format(now);
		
		//�ļ���
		String path = System.getProperty("user.dir") + "/screenshot/";
		String name = imagename + ".jpg";
		
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //��ͼ��Ƭ�������ļ�image��
		FileUtils.copyFile(image, new File(path+name));
	}
	
	//�ȴ�30S���ж�һ��Ԫ���Ƿ����
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
	
	
	
	//�˳�
	public void free() throws Exception{
		driver.quit();
	}
	

}
