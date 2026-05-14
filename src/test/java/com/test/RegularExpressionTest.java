package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class RegularExpressionTest extends BaseTest{

	@BeforeEach
	public void setUp() throws Exception {
		getDriver().get("https://www.geradordecpf.org/");		
	}
	
	@Test
	public void testCPFValidationWithMask() {
		WebElement cbPontos = getDriver().findElement(By.id("cbPontos"));
		cbPontos.click();
		
		WebElement btnGerarCPF = getDriver().findElement(By.id("btn-gerar-cpf"));
		btnGerarCPF.click();
		
		WebElement tfNumero = getDriver().findElement(By.id("numero"));
		String cpfGerado = tfNumero.getAttribute("value");
		
		assertTrue(cpfGerado.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$"));
		
		//assertTrue(cpfGerado.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));
	}
	
	@Test
	public void testCPFValidationWithoutMask() {
		WebElement btnGerarCPF = getDriver().findElement(By.id("btn-gerar-cpf"));
		btnGerarCPF.click();
		
		WebElement tfNumero = getDriver().findElement(By.id("numero"));
		String cpfGerado = tfNumero.getAttribute("value");
		
		assertTrue(cpfGerado.matches("^\\d{11}$"));
	}

}
