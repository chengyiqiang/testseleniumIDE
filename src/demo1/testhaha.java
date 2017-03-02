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
		    driver.findElement(By.linkText("����.MX�����������к�Ц������")).click();
			Thread.sleep(5000);
			th.changewindow();//�л�������
			th.dosearch("����");
			th.free();
			
//			xpath=(//a[contains(text(),'������ʷ')])[2]
//					<a target="_blank" href="http://i.baidu.com/my/history?from=ps">������ʷ</a>
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}
	

	
	public void init() throws Exception{
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Fernando\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//ȫ�����ã���Ԫ��ʶ�𲻵���ʱ�򣬿��Խ��ܵ����ȴ�ʱ��
	    driver.manage().window().maximize();
	    driver.get("http://www.baidu.com/");
	}

	public void dotest() throws Exception{
		driver.findElement(By.id("kw")).click();
		driver.findElement(By.id("kw")).clear();
	    driver.findElement(By.id("kw")).sendKeys("haha");
	    if(this.waitelement(By.linkText("����.MX�����������к�Ц������"))){
	    	System.out.println("�ٶ��������������ɹ�");	 
	    }else{
	    	System.out.println("�ٶ�������������ʧ��");	 
	    }
	}
	
	// �����´��ڣ��л����´�����
	public void changewindow() throws Exception {
		// �õ���ǰ���ڵľ��
		String currentWindow = driver.getWindowHandle();
		// �õ����д��ڵľ����set�������Է���ؽ���Ҫ�����ͣ��Լ������ͱ�����һ�������У�Set��һ���������ظ�Ԫ�ص�collection
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// hasNext����������Ƿ���Ԫ��
		while (it.hasNext()) {
			// ��һ�ε���Iterator��next()����ʱ�����������еĵ�һ��Ԫ�أ�ʹ��next()��������е���һ��Ԫ��
			String handle = it.next();
			if (currentWindow.equals(handle))
				continue;
			driver.close();
			driver.switchTo().window(handle);
		}
	}
	
	
	//�����������
	public void dosearch (String search) throws Exception {
	    driver.findElement(By.id("key")).clear();
	    driver.findElement(By.id("key")).sendKeys(search);
	    
	    driver.findElement(By.cssSelector("button.header-top-btn-search.fr")).click();	    
	//	driver.findElement(By.className("header-top-btn-search fr")).click();     //�˷��������ã�������ԭ����.........
		if (this.waitelement(By.className("search-highlight"))){
			if (driver.findElement(By.className("search-highlight")).getText().equals(search)){
//			if (driver.findElement(By.className("search-highlight")).getText() == search){
				System.out.println(search + "�����ɹ�");
			}
			else{
				System.out.println(search + "����ʧ��");
			}
		}
		else{
			System.out.println(search + "��������");
		}
	}
	



	//�ȴ�30s���ж��Ƿ����ĳ��Ԫ��
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
	
	//������ǰ��
	public void forwardback (){
		
	}
	
	
	//�ر���������ͷ���Դ
	public void free() throws Exception{
		driver.quit();
	}

}
