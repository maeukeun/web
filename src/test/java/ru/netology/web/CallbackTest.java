package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldSuccessTestV1() {
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        driver.findElements(By.className("input__control")).get(0).sendKeys("Максим");
        driver.findElements(By.className("input__control")).get(1).sendKeys("+79273569501");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String actualText = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText.trim());
    }

    @Test
    public void shouldSuccessTestV2() {
//        WebElement form = driver.findElement(By.cssSelector("[data-test-id=callback-form]"));
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Максим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79273569501");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText() .trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);
    }
}

