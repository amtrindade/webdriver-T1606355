package com.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigationActionBrowserTest {
	
	public WebDriver driver;

	@BeforeEach
	public void setUp() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		
		//Abrir o browser no monitor auxiliar
		Point point = new Point(-1500, -150); 
		driver.manage().window().setPosition(point);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://antoniotrindade.com.br/treinoautomacao/index.html");		
	}

	@AfterEach
	public void tearDown() throws Exception {
		// tempo de 2s para visualizar
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void testNavigationActionBrowser() {
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		WebElement linkCalculadora = driver.findElement(By.linkText("Calculadora"));
		linkCalculadora.click();
		
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		WebElement linkLocalizarTable = driver.findElement(By.linkText("Localizar Table"));
		linkLocalizarTable.click();
		
		assertEquals("Trabalhando com tables", driver.getTitle());
		
		driver.navigate().back();
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		driver.navigate().back();
		assertEquals("Treino Automação de Testes", driver.getTitle());
		driver.navigate().forward();
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		driver.navigate().forward();
		assertEquals("Trabalhando com tables", driver.getTitle());
				
	}

}
