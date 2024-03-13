package automacao_bugbank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class novo_cadastro {

	private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drives/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void abreAPagina() {
        driver.get("https://bugbank.netlify.app/");
        assertEquals("BugBank", driver.getTitle());
    }

    @AfterEach
    public void tearDown() {
     //   driver.quit();
    }

}
