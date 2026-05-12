package com.test;

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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocatorTest {
	
	public WebDriver driver;

	@BeforeEach
	public void setUp() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		
		//Abrir o browser no monitor auxiliar
		Point point = new Point(-1500, -150); 
		driver.manage().window().setPosition(point);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://antoniotrindade.com.br/treinoautomacao/localizandovalorestable.html");		
	}

	@AfterEach
	public void tearDown() throws Exception {
		// tempo de 2s para visualizar
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void testRealizaCheckBox() {
		String nome = "Milene";
				
		WebElement checkBox = driver.findElement(By.xpath("//td[contains(text(),'"+ nome +"')]/../td/input"));
		checkBox.click();
		assertTrue(checkBox.isSelected());	
	}
	
	@Test
	public void testCopiaNomeParaTextField() {
		String mail = "mail3@gmail.com";
		
		WebElement tdNome = driver.findElement(By.xpath("//td[.='"+ mail +"']/../td[1]"));
		WebElement tfReserva = driver.findElement(By.id("txt01"));
		tfReserva.sendKeys(tdNome.getText());
		
		assertEquals("João Pedro Nascimento", tfReserva.getAttribute("value"));
			
	}


}
