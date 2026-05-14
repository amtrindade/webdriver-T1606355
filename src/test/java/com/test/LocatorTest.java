package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class LocatorTest extends BaseTest{
	
	@BeforeEach
	public void setUp() throws Exception {
		getDriver().get("https://antoniotrindade.com.br/treinoautomacao/localizandovalorestable.html");		
	}
	
	@Test
	public void testRealizaCheckBox() {
		String nome = "Milene";
				
		WebElement checkBox = getDriver().findElement(By.xpath("//td[contains(text(),'"+ nome +"')]/../td/input"));
		checkBox.click();
		assertTrue(checkBox.isSelected());	
	}
	
	@Test
	public void testCopiaNomeParaTextField() {
		String mail = "mail3@gmail.com";
		
		WebElement tdNome = getDriver().findElement(By.xpath("//td[.='"+ mail +"']/../td[1]"));
		WebElement tfReserva = getDriver().findElement(By.id("txt01"));
		tfReserva.sendKeys(tdNome.getText());
		
		assertEquals("João Pedro Nascimento", tfReserva.getAttribute("value"));
			
	}
}
