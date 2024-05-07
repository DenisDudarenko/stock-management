package stock.management;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeliveryIssuancePagesTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    void Page() {
        driver.get("http://localhost:8080/deliveries-and-issuance");
        assertEquals("Поставки и выдачи", driver.getTitle());
    }

    @Test
    @Order(2)
    void testOneDeliveryIssuancePerPage() {
        driver.get("http://localhost:8080/deliveries-and-issuance?page=1&count=1");
        assertEquals("Поставки и выдачи", driver.getTitle());

        WebElement deliveriesIssuanceTable = driver.findElement(By.cssSelector("table.table tbody"));
        int deliveriesIssuanceCount = deliveriesIssuanceTable.findElements(By.tagName("tr")).size();
        assertEquals(1, deliveriesIssuanceCount);
    }

    @Test
    @Order(3)
    public void testCreateDeliveryIssuanceAndCheckPresence() {
        driver.get("http://localhost:8080/delivery-and-issuance/new");

        WebElement operationTimeInput = driver.findElement(By.id("operationTime"));
        operationTimeInput.sendKeys("2024-05-07 12:00:00");

        WebElement operationTypeInput = driver.findElement(By.id("operationType"));
        operationTypeInput.sendKeys("Delivery");

        WebElement innInput = driver.findElement(By.id("inn"));
        innInput.sendKeys("123456789");

        // Assuming there is JavaScript logic for adding products
        WebElement addProductButton = driver.findElement(By.xpath("//button[contains(text(),'Добавить продукт')]"));
        addProductButton.click();

        WebElement productNameInput = driver.findElement(By.id("productName0"));
        productNameInput.sendKeys("Название продукта");

        WebElement productQuantityInput = driver.findElement(By.id("productQuantity0"));
        productQuantityInput.sendKeys("10");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        driver.get("http://localhost:8080/deliveries-and-issuance?page=1&count=100");

        assertTrue(driver.getPageSource().contains("2024-05-07 12:00:00"));
    }

    @Test
    @Order(4)
    public void testUpdateDeliveryIssuanceAndCheckChanges() {
        driver.get("http://localhost:8080/delivery-and-issuance?id=1");

        WebElement operationTimeInput = driver.findElement(By.id("operationTime"));
        operationTimeInput.clear();
        operationTimeInput.sendKeys("2024-05-08 12:00:00");

        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();

        driver.get("http://localhost:8080/deliveries-and-issuance?page=1&count=100");

        assertTrue(driver.getPageSource().contains("2024-05-08 12:00:00"));
    }
}
