package com.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegularExpressionTest {
	
	public WebDriver driver;

	@BeforeEach
	public void setUp() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		
		//Abrir o browser no monitor auxiliar
		Point point = new Point(-1500, -150); 
		driver.manage().window().setPosition(point);
		
		driver.get("https://www.geradordecpf.org/");		
	}

	@AfterEach
	public void tearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void testCPFValidationWithMask() {
		WebElement cbPontos = driver.findElement(By.id("cbPontos"));
		cbPontos.click();
		
		WebElement btnGerarCPF = driver.findElement(By.id("btn-gerar-cpf"));
		btnGerarCPF.click();
		
		WebElement tfNumero = driver.findElement(By.id("numero"));
		String cpfGerado = tfNumero.getAttribute("value");
		
		System.out.println(cpfGerado);
		
		assertTrue(cpfGerado.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$"));
		
		//assertTrue(cpfGerado.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));
	}
	
	@Test
	public void testCPFValidationWithoutMask() {
		WebElement btnGerarCPF = driver.findElement(By.id("btn-gerar-cpf"));
		btnGerarCPF.click();
		
		WebElement tfNumero = driver.findElement(By.id("numero"));
		String cpfGerado = tfNumero.getAttribute("value");
		
		System.out.println(cpfGerado);
		
		assertTrue(cpfGerado.matches("^\\d{11}$"));
	}

}
