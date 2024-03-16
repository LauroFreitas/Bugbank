package automacao_bugbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;


public class test_transferencia_entre_conta {

    private WebDriver driver;
    private String email_usuario_um = "userum@gmail.com";
    private String email_usuario_dois = "userdois@gmail.com";
    private String password_userUm = "senhaUser1@";
    private String password_userDois = "senhaUser2@";
    private String conta_usuario_um;
    private String conta_usuario_dois;
    
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drives/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testMain()  throws InterruptedException{
        driver.get("https://bugbank.netlify.app/");
        criaConta_UserUm();
        driver.get("https://bugbank.netlify.app/");
        criaConta_UserDois();
        driver.get("https://bugbank.netlify.app/");
        
        driver.findElement(By.name("email")).sendKeys(email_usuario_dois);
        driver.findElement(By.name("password")).sendKeys(password_userDois);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".otUnI")).click();
      
        validaConta(conta_usuario_dois);
        {
        	 transfereSaldo(conta_usuario_um,1000);
        	
        }
        
        Thread.sleep(1000);
        driver.findElement(By.name("email")).sendKeys(email_usuario_um);
        driver.findElement(By.name("password")).sendKeys(password_userUm);
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".otUnI")).click();
        
        validaConta(conta_usuario_um);
        {
        	 transfereSaldo(conta_usuario_dois,2000);
       
        }
        
       }
    
    public void validaConta(String conta) {
   	 driver.get("https://bugbank.netlify.app/home");
	 driver.findElement(By.cssSelector(".home__ContainerAccountNumber-sc-1auj767-8")).click();
	 assertThat(driver.findElement(By.cssSelector("#textAccountNumber > span")).getText(), is(conta));
    }
   
    public void transfereSaldo(String contaUsada, float valor)  throws InterruptedException{
    	 WebDriverWait wait = new WebDriverWait(driver, 10);
         WebElement accountNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#textAccountNumber > span")));
         {
         	String numeroConta = getConta(contaUsada);
         	String digito = getDigito(contaUsada);
         	
         	Thread.sleep(1000);
             driver.findElement(By.id("btn-TRANSFERÊNCIA")).click();
             
             Thread.sleep(1000);
             driver.findElement(By.name("accountNumber")).sendKeys(numeroConta);
             driver.findElement(By.name("digit")).sendKeys(digito);
             driver.findElement(By.name("transferValue")).sendKeys(Float.toString(valor));
             driver.findElement(By.name("description")).sendKeys("pix transferência");
             driver.findElement(By.cssSelector(".style__ContainerButton-sc-1wsixal-0")).click();
                          
         }
         Thread.sleep(1000);
    	 driver.findElement(By.id("btnCloseModal")).click();
    	 driver.findElement(By.id("btnExit")).click();
    }
    
    public void criaConta_UserUm()  throws InterruptedException {
        assertEquals("BugBank | O banco com bugs e falhas do seu jeito", driver.getTitle());
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(), 'Registrar')]")).click();
        driver.findElement(By.cssSelector(".style__ContainerFieldInput-sc-s3e9ea-0:nth-child(2) > .input__default")).sendKeys(email_usuario_um);
        driver.findElement(By.name("name")).sendKeys("Paulo");
        driver.findElement(By.cssSelector(".login__password:nth-child(4) .input__default")).sendKeys(password_userUm);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(password_userUm);
        
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id("toggleAddBalance"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

         
        
        Thread.sleep(1000);
        WebElement submit = driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]"));
        actions.moveToElement(submit).click().perform();

        driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]")).click();

        Thread.sleep(1000);
        driver.findElement(By.id("modalText")).getText();
        {
            String conta_criada = driver.findElement(By.id("modalText")).getText();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            conta_usuario_um = getConta_Cadastro(conta_criada);
        }
        Thread.sleep(3000);
     }

    public void criaConta_UserDois()  throws InterruptedException  {
        assertEquals("BugBank | O banco com bugs e falhas do seu jeito", driver.getTitle());
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(), 'Registrar')]")).click();
        driver.findElement(By.cssSelector(".style__ContainerFieldInput-sc-s3e9ea-0:nth-child(2) > .input__default")).sendKeys(email_usuario_dois);
        driver.findElement(By.name("name")).sendKeys("Mauro");
        driver.findElement(By.cssSelector(".login__password:nth-child(4) .input__default")).sendKeys(password_userDois);
        driver.findElement(By.name("passwordConfirmation")).sendKeys(password_userDois);
        
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id("toggleAddBalance"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        Thread.sleep(1000);
        WebElement submit = driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]"));
        actions.moveToElement(submit).click().perform();

        driver.findElement(By.xpath("//button[contains(text(), 'Cadastrar')]")).click();
        
        Thread.sleep(1000);

        driver.findElement(By.id("modalText")).getText();
        {
            String conta_criada = driver.findElement(By.id("modalText")).getText();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            conta_usuario_dois = getConta_Cadastro(conta_criada);
        }
        Thread.sleep(3000);
        
        
    }

    @AfterEach
    public void tearDown() {
       driver.quit();
    }

    private String getConta_Cadastro(String conta) {
        int a = conta.indexOf("conta ") + "conta ".length();
        int b = conta.indexOf(" ", a);

        if (a >= 0 && b >= 0 && b > a && b <= conta.length()) {
            String dados = conta.substring(a, b);
            return dados;
        } else {
            return "";
        }
    }
    
    private String getConta(String conta_cadastrada) {
    	 if (conta_cadastrada != null && conta_cadastrada.length() > 0) {
    		 conta_cadastrada = conta_cadastrada.substring(0, conta_cadastrada.length() - 2);
    		 String conta = conta_cadastrada;
    		 return conta;
         } else {
        	 return null;
         }
    }
    
    private String getDigito(String texto) {
        if (texto != null && texto.length() > 1) {
            return texto.substring(texto.length() - 1);
        } else {
            return texto;  
        }
    }
    
   
}


