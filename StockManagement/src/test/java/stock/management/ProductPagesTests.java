package stock.management;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductPagesTests {

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
    void Page() {
        driver.get("http://localhost:8080/products");
        assertEquals("Список товаров", driver.getTitle());
    }

    @Test
    @Order(1)
    void testOneProductPerPage() {
        driver.get("http://localhost:8080/products?page=1&count=1");
        assertEquals("Список товаров", driver.getTitle());

        WebElement productsTable = driver.findElement(By.cssSelector("table.table tbody"));
        int productsCount = productsTable.findElements(By.tagName("tr")).size();
        assertEquals(1, productsCount);
    }

    @Test
    @Order(2)
    void testProductTypeFilter() {
        driver.get("http://localhost:8080/products?type=Food");

        WebElement productsTable = driver.findElement(By.cssSelector("table.table tbody"));
        List<WebElement> rows = productsTable.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            WebElement typeColumn = row.findElement(By.xpath("//td[2]"));
            assertEquals("Food", typeColumn.getText());
        }
    }

    @Test
    @Order(3)
    public void testCreateProductAndCheckPresence() {
        driver.get("http://localhost:8080/product/new");

        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys("Новый продукт");

        WebElement typeInput = driver.findElement(By.id("type"));
        typeInput.sendKeys("Тип продукта");

        WebElement quantityInput = driver.findElement(By.id("quantity"));
        quantityInput.sendKeys("10");

        WebElement measurementInput = driver.findElement(By.id("measurement"));
        measurementInput.sendKeys("Единицы измерения");

        WebElement lifeTimeInput = driver.findElement(By.id("lifeTime"));
        lifeTimeInput.sendKeys("2024-05-07 12:00:00");

        WebElement roomNumberInput = driver.findElement(By.id("roomNumber"));
        roomNumberInput.sendKeys("101");

        WebElement shelfNumberInput = driver.findElement(By.id("shelfNumber"));
        shelfNumberInput.sendKeys("1");

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        driver.get("http://localhost:8080/products?page=1&count=100");

        assertTrue(driver.getPageSource().contains("Новый продукт"));
    }

    @Test
    @Order(4)
    public void testUpdateProductAndCheckChanges() {
        driver.get("http://localhost:8080/product?name=Новый продукт");

        WebElement quantityInput = driver.findElement(By.id("productQuantity"));

        quantityInput.clear();
        quantityInput.sendKeys("15");

        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();

        driver.get("http://localhost:8080/products?page=1&count=100");

        assertTrue(driver.getPageSource().contains("Новый продукт"));
        assertTrue(driver.getPageSource().contains("15"));
    }
}
