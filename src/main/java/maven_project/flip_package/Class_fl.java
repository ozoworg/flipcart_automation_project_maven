package maven_project.flip_package;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Class_fl {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\User\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div/div/div/div/div/div/div/div/div/div[1]/div/div/header/div[1]/div[2]/form/div/div/input"));
		searchBox.sendKeys("Helmet under 7000");
		Thread.sleep(2000);
		searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		Select select = new Select (driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div/section[2]/div[4]/div[3]/select")));
		select.selectByIndex(3);
		Thread.sleep(2000);
		
		WebElement assurance= driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div/section[3]/label/div[1]"));
		assurance.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(2000);
		
		WebElement youtubeLink= driver.findElement(By.xpath("//*[@id=\"container\"]/div/footer/div/div[2]/div[1]/div[2]/a[1]"));
		youtubeLink.click();
		
		System.out.println("Mynthra link clicked");
		
		
		String parent=driver.getWindowHandle();//get parent window handle
		Set<String> allwindows=driver.getWindowHandles();//get all window handles
		
		for(String i:allwindows) {		//iterate through all handles
			if (!parent.equals(i)) {//compare parent window handle with all window handles
				driver.switchTo().window(i); //switch to child window
				Thread.sleep(2000);
				
				driver.close(); //close the child window

			}
		}
		
		Thread.sleep(2000);
		driver.switchTo().window(parent); //switch back to parent window
		
		//quit the i
		
		Thread.sleep(2000);
		//scroll up to top
		js.executeScript("window.scrollTo(0,0)");
		

	}

}
