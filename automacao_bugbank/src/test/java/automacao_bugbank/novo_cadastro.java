package automacao_bugbank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class novo_cadastro {

	private WebDriver driver;
	private String email_usuario_um = "userum@gmail.com";
	private String email_usuario_dois = "userdois@gmail.com";
	private String password_userUm = "senhaUser1@";
	private String password_userDois = "senhaUser2@";
	
	
	
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drives/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testMain() {
    	driver.get("https://bugbank.netlify.app/");
    	criaConta_UserUm();
    	driver.get("https://bugbank.netlify.app/");
    	criaConta_UserDois();
        driver.findElement(By.cssSelector(".CMabB")).click();
        {
          WebElement element = driver.findElement(By.cssSelector(".CMabB"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element).perform();
        }
        {
          WebElement element = driver.findElement(By.tagName("body"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.id("btnCloseModal")).click();
        
        driver.findElement(By.name("email")).sendKeys(email_usuario_um);
        driver.findElement(By.name("password")).sendKeys(password_userUm);
        
        driver.findElement(By.cssSelector(".otUnI")).click();
        {
          WebElement element = driver.findElement(By.cssSelector(".otUnI"));
          Actions builder = new Actions(driver);
          builder.moveToElement(element).perform();
        }
    
    }
    public void criaConta_UserUm() {
        assertEquals("BugBank | O banco com bugs e falhas do seu jeito", driver.getTitle());
        driver.findElement(By.xpath("//button[contains(text(), 'Registrar')]")).click();
        driver.findElement(By.cssSelector(".style__ContainerFieldInput-sc-s3e9ea-0:nth-child(2) > .input__default")).sendKeys(email_usuario_um);
        driver.findElement(By.name("name")).sendKeys("Paulo");
        driver.findElement(By.cssSelector(".login__password:nth-child(4) .input__default")).sendKeys(password_userUm);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(password_userUm);
        
        WebElement element = driver.findElement(By.id("toggleAddBalance"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        
        driver.findElement(By.id("toggleAddBalance")).click();
        
        WebElement submit = driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]"));
        actions.moveToElement(submit).click().perform();
        
        driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]")).click();
       
    }
    public void criaConta_UserDois() {
        assertEquals("BugBank | O banco com bugs e falhas do seu jeito", driver.getTitle());
        driver.findElement(By.xpath("//button[contains(text(), 'Registrar')]")).click();
        driver.findElement(By.cssSelector(".style__ContainerFieldInput-sc-s3e9ea-0:nth-child(2) > .input__default")).sendKeys(email_usuario_dois);
        driver.findElement(By.name("name")).sendKeys("Mauro");
        driver.findElement(By.cssSelector(".login__password:nth-child(4) .input__default")).sendKeys(password_userDois);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(password_userDois);
        
        WebElement element = driver.findElement(By.id("toggleAddBalance"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        
        driver.findElement(By.id("toggleAddBalance")).click();
        
        WebElement submit = driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]"));
        actions.moveToElement(submit).click().perform();
        
        driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]")).click();
        
    }

    @AfterEach
    public void tearDown() {
        //driver.quit();
    }

}
