package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class CalculatorTest extends BaseTest{
	
	private WebDriverWait wait;

	@BeforeEach
	public void setUp() throws Exception {
		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		getDriver().get("https://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");		
	}

	
	@Test
	public void testSoma() throws InterruptedException {
		
		Random random = new Random();
		Integer randomico01 = random.nextInt(100);
		Integer randomico02 = random.nextInt(100);
		
		Integer totalRandomico = randomico01 + randomico02;
		
		WebElement tfValor1 = getDriver().findElement(By.id("number1"));
		tfValor1.sendKeys(Integer.toString(randomico01));
		
		WebElement tfValor2 = getDriver().findElement(By.id("number2"));
		tfValor2.sendKeys(Integer.toString(randomico02));
		
		WebElement btnSomar = getDriver().findElement(By.id("somar"));
		btnSomar.click();
			
		WebElement tfTotal = getDriver().findElement(By.id("total"));
		
		//espera explicita
		wait.until(ExpectedConditions.textToBePresentInElementValue(tfTotal, Integer.toString(totalRandomico)));
				
		assertEquals(Integer.toString(totalRandomico) , tfTotal.getAttribute("value"));
	}
}
