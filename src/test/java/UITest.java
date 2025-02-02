import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UITest {

    @Test
    public void UICheck() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("gh-ac")).sendKeys("book");
        driver.findElement(By.id("gh-search-btn")).click();
        driver.findElement(By.xpath("//span[contains(text(),'3 BOOKS: QUOTATIONS FROM CHAIRMAN')]")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> handleList = new ArrayList<>(windowHandles);
        driver.switchTo().window(handleList.get(1));
        driver.findElement(By.id("qtyTextBox")).clear();
        driver.findElement(By.id("qtyTextBox")).sendKeys("2");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//span[text()='Add to cart']")));
        driver.findElement(By.xpath("//span[text()='Add to cart']")).click();
        String cartValue = driver.findElement(By.xpath("//span[@class='badge']")).getText();
        System.out.println("CartValue is: " + cartValue);
        Assert.assertEquals(cartValue, "2");
        driver.quit();
    }
}
