package demo1;


import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;





public class testbaidu {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome C:/Program Files (x86)/Mozilla Firefox/firefox.exe", "https://www.baidu.com/");
		selenium.start();
		selenium.windowFocus();
		selenium.windowMaximize();
	}

	@Test
	public void testTestbaidu() throws Exception {
		selenium.open("/");
		selenium.click("id=kw");
		selenium.click("id=kw");
		selenium.type("id=kw", "�ٶ�");
		selenium.click("id=su");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("link=�ٶ�����")) break; } catch (Exception e) {}
			Thread.sleep(1000);
			selenium.captureScreenshot("C:\\Users\\Fernando\\Desktop\\Ӧ�ñ�����\\selenium��ͼ\\test.jpg"); //��ͼ����
		}

		selenium.click("link=�ٶ�����");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
		selenium=null;
	}
}
