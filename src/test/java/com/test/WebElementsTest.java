package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElementsTest {
	
	public WebDriver driver;

	@BeforeEach
	public void setUp() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("https://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
	}

	@AfterEach
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.quit();
	}

	@Test
	public void testHelloWorld() {
		//1. Identificar o elemento
		WebElement tfEnable = driver.findElement(By.name("txtbox1"));
		
		//2. Fazer a interação com o elemento enviando um valor
		tfEnable.sendKeys("Hello World!!!");
		
		//3. Valida o resultado esperado
		assertEquals("Hello World!!!", tfEnable.getAttribute("value"));
		
	}

}
