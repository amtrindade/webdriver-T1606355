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

import java.time.Duration;
import java.util.ArrayList;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigationTabsTest {
	
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
	public void testTabs() throws InterruptedException {
			
		WebElement linkDragAndDropJQuery = driver.findElement(By.linkText("Drag and Drop JQuery"));
		linkDragAndDropJQuery.click();

		WebElement linkGeradorCpf = driver.findElement(By.linkText("Gerador de CPF"));
		linkGeradorCpf.click();
		
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		//Captura as guias abertas
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		//Navega para primeira guia aberta
		driver.switchTo().window(tabs.get(1));
		
		//TODO alterar forma de espera
		Thread.sleep(2000);
		
		assertEquals("Gerador de CPF", driver.getTitle());
		
		//Navega para segunda tab aberta
		driver.switchTo().window(tabs.get(2));
		
		assertEquals("jQuery UI Droppable - Default functionality", driver.getTitle());
		
		//Retorna o foco do driver para guia inicial
		driver.switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", driver.getTitle());
	}

}
