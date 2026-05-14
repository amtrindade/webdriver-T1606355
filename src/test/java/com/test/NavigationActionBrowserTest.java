package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class NavigationActionBrowserTest extends BaseTest{
	
	@BeforeEach
	public void setUp() throws Exception {	
		getDriver().get("https://antoniotrindade.com.br/treinoautomacao/index.html");		
	}

	
	@Test
	public void testNavigationActionBrowser() {
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkCalculadora = getDriver().findElement(By.linkText("Calculadora"));
		linkCalculadora.click();
		
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		WebElement linkLocalizarTable = getDriver().findElement(By.linkText("Localizar Table"));
		linkLocalizarTable.click();
		
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
		getDriver().navigate().back();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		getDriver().navigate().back();
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		getDriver().navigate().forward();
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		getDriver().navigate().forward();
		assertEquals("Trabalhando com tables", getDriver().getTitle());
				
	}

}
