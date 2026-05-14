package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class NavigationTabsTest extends BaseTest{
	

	@BeforeEach
	public void setUp() throws Exception {	
		getDriver().get("https://antoniotrindade.com.br/treinoautomacao/index.html");		
	}

	
	@Test
	public void testTabs() throws InterruptedException {
			
		WebElement linkDragAndDropJQuery = getDriver().findElement(By.linkText("Drag and Drop JQuery"));
		linkDragAndDropJQuery.click();

		WebElement linkGeradorCpf = getDriver().findElement(By.linkText("Gerador de CPF"));
		linkGeradorCpf.click();
		
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		//Captura as guias abertas
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		//Navega para primeira guia aberta
		getDriver().switchTo().window(tabs.get(1));
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("numero")));
		
		assertEquals("Gerador de CPF", getDriver().getTitle());
		
		//Navega para segunda tab aberta
		getDriver().switchTo().window(tabs.get(2));
		
		assertEquals("jQuery UI Droppable - Default functionality", getDriver().getTitle());
		
		//Retorna o foco do driver para guia inicial
		getDriver().switchTo().window(tabs.get(0));
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
	}

}
