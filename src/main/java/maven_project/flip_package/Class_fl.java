package maven_project.flip_package;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Class_fl {

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\User\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;

        driver.get("https://www.flipkart.com/");
    }

    // 🔹 Step 1: Search product
    @Test(priority = 1)
    public void searchProduct() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.xpath(
                "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div/div/div/div/div/div[1]/div/div/header/div[1]/div[2]/form/div/div/input"));
        searchBox.sendKeys("Helmet under 7000");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    // 🔹 Step 2: Apply filters
    @Test(priority = 2)
    public void applyFilters() throws InterruptedException {
        Select select = new Select(driver.findElement(By.xpath(
                "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div/section[2]/div[4]/div[3]/select")));
        select.selectByIndex(3);
        Thread.sleep(2000);

        WebElement assurance = driver.findElement(By.xpath(
                "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div/section[3]/label/div[1]"));
        assurance.click();

        js.executeScript("window.scrollBy(0,800)");
        Thread.sleep(2000);
    }

    // 🔹 Step 3: Click on YouTube link (footer)
    @Test(priority = 3)
    public void clickYoutubeLink() throws InterruptedException {
        WebElement youtubeLink = driver.findElement(By.xpath(
                "//*[@id=\"container\"]/div/footer/div/div[2]/div[1]/div[2]/a[1]"));
        youtubeLink.click();

        System.out.println("Myntra link clicked");

        String parent = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String i : allWindows) {
            if (!parent.equals(i)) {
                driver.switchTo().window(i);
                Thread.sleep(2000);
                driver.close();
            }
        }

        driver.switchTo().window(parent);
        js.executeScript("window.scrollTo(0,0)");
        Thread.sleep(2000);
    }

    // 🔹 Step 4: Open product details
    @Test(priority = 4)
    public void openProduct() throws InterruptedException {
        WebElement clickProduct = driver.findElement(By.xpath(
                "//*[@id=\"container\"]/div/div[3]/div/div[2]/div[2]/div/div[1]/div/a[1]/div[1]/div/div/img"));
        clickProduct.click();

        String parent1 = driver.getWindowHandle();
        Set<String> allWindows1 = driver.getWindowHandles();
        for (String i : allWindows1) {
            if (!parent1.equals(i)) {
                driver.switchTo().window(i);
                Thread.sleep(2000);
            }
        }
    }

    // 🔹 Step 5: Hover over product image
    @Test(priority = 5)
    public void moveOnProductImage() throws InterruptedException {
        WebElement product_img = driver.findElement(By.xpath(
                "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div[1]/div[2]/div/div[2]/img"));

        Actions action = new Actions(driver);
        action.moveToElement(product_img).perform();

        // move the cursor through the image
        action.moveToElement(product_img, 50, 0).perform(); // Move right
        Thread.sleep(1000);
        action.moveToElement(product_img, 0, 50).perform(); // Move down
        Thread.sleep(1000);
        action.moveToElement(product_img, -50, 0).perform(); // Move left
        Thread.sleep(1000);
        action.moveToElement(product_img, 0, -50).perform(); // Move up
        Thread.sleep(1000);
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
