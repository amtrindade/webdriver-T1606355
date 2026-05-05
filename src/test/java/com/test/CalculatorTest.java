package com.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalculatorTest {
	
	public WebDriver driver;

	@BeforeEach
	public void setUp() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		
		//Abrir o browser no monitor auxiliar
//		Point point = new Point(-1500, -150); 
//		driver.manage().window().setPosition(point);
		
		driver.get("https://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");		
	}

	@AfterEach
	public void tearDown() throws Exception {
		// tempo de 2s para visualizar
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void testSoma() throws InterruptedException {
		
		Random random = new Random();
		Integer randomico01 = random.nextInt(100);
		Integer randomico02 = random.nextInt(100);
		
		Integer totalRandomico = randomico01 + randomico02;
		
		WebElement tfValor1 = driver.findElement(By.id("number1"));
		tfValor1.sendKeys(Integer.toString(randomico01));
		
		WebElement tfValor2 = driver.findElement(By.id("number2"));
		tfValor2.sendKeys(Integer.toString(randomico02));
		
		WebElement btnSomar = driver.findElement(By.id("somar"));
		btnSomar.click();
		
		//TODO Ajustar esta espera por outra espera explicita
		Thread.sleep(3000);
		
		WebElement tfTotal = driver.findElement(By.id("total"));
		assertEquals(Integer.toString(totalRandomico) , tfTotal.getAttribute("value"));
	}
}
