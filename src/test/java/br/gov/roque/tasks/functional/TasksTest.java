package br.gov.roque.tasks.functional;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.3:4444/wd/hub") , cap);
		driver.navigate().to("http://192.168.0.3:8080/tasks/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefa() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		//Clicar botáo add
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//clicar salvar
		driver.findElement(By.id("saveButton")).click();
		
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals("Success!", mensagem);
		
		driver.quit();
				
		
	}
	
	@Test
	public void deveApresentarErroData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		//Clicar botáo add
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		
		//clicar salvar
		driver.findElement(By.id("saveButton")).click();
		
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals("Due date must not be in past", mensagem);
		
		driver.quit();
				
		
	}
	
	

}
