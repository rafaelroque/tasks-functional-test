package br.gov.roque.tasks.functional;


import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class TasksTest {
	
	@Test
	public void deveSalvarTarefa() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8080/tasks/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
	public void deveApresentarErroData() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8080/tasks/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
