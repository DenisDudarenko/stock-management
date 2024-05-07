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
public class SupplierConsumerPagesTests {

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
        driver.get("http://localhost:8080/suppliers-and-consumers");
        assertEquals("Список поставщиков и потребителей", driver.getTitle());
    }

    @Test
    @Order(2)
    void testOneSupplierConsumerPerPage() {
        driver.get("http://localhost:8080/suppliers-and-consumers?page=1&count=1");
        assertEquals("Список поставщиков и потребителей", driver.getTitle());

        WebElement suppliersConsumersTable = driver.findElement(By.cssSelector("table.table tbody"));
        int suppliersConsumersCount = suppliersConsumersTable.findElements(By.tagName("tr")).size();
        assertEquals(1, suppliersConsumersCount);
    }

    @Test
    @Order(3)
    public void testCreateSupplierConsumerAndCheckPresence() {
        driver.get("http://localhost:8080/supplier-and-consumer/new");

        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys("Новый поставщик/потребитель");

        WebElement innInput = driver.findElement(By.id("inn"));
        innInput.sendKeys("123456789");

        WebElement addressInput = driver.findElement(By.id("address"));
        addressInput.sendKeys("Адрес поставщика/потребителя");

        WebElement phoneInput = driver.findElement(By.id("phone"));
        phoneInput.sendKeys("123-456-789");

        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys("example@example.com");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        driver.get("http://localhost:8080/suppliers-and-consumers?page=1&count=100");

        assertTrue(driver.getPageSource().contains("Новый поставщик/потребитель"));
    }

    @Test
    @Order(4)
    public void testUpdateSupplierConsumerAndCheckChanges() {
        driver.get("http://localhost:8080/supplier-and-consumer?inn=123456789");

        WebElement addressInput = driver.findElement(By.id("supplierAddress"));
        addressInput.clear();
        addressInput.sendKeys("Новый адрес поставщика/потребителя");

        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();

        driver.get("http://localhost:8080/suppliers-and-consumers?page=1&count=100");

        assertTrue(driver.getPageSource().contains("Новый адрес поставщика/потребителя"));
    }
}
