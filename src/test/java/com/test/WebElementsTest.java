package com.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElementsTest {
	
	public WebDriver driver;

	@BeforeEach
	public void setUp() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		
		//Abrir o browser no monitor auxiliar
		Point point = new Point(-1500, -150); 
		driver.manage().window().setPosition(point);
		
		driver.get("https://antoniotrindade.com.br/treinoautomacao/elementsweb.html");		
	}

	@AfterEach
	public void tearDown() throws Exception {
		// tempo de 2s para visualizar
		Thread.sleep(2000);
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
	
	@Test
	public void testTextFieldEnableDisable() {
		WebElement tfDisabled = driver.findElement(By.name("txtbox2"));
		WebElement tfEnable = driver.findElement(By.name("txtbox1"));
		
		
		assertFalse(tfDisabled.isEnabled(), "tfDisable deveria estar desabilitado!");
		assertTrue(tfEnable.isEnabled(), "tfEnable deveria estar habilitado!");
	}
	
	@Test
	public void testRadioButton() {
		List<WebElement> rbLista = driver.findElements(By.name("radioGroup1"));
		//Os 4 elementos foram capturados
		assertEquals(4, rbLista.size());
		
		for (WebElement element : rbLista) {
			//System.out.println(element.getAttribute("value"));
			if (element.getAttribute("value").equals("Radio 3")) {
				element.click();
				break;
			}
		}
		
		assertTrue(rbLista.get(2).isSelected());
		assertFalse(rbLista.get(0).isSelected());
		assertFalse(rbLista.get(1).isSelected());
		assertFalse(rbLista.get(3).isSelected());
	}
	
	@Test
	public void testCheckBoxes() {
		List<WebElement> chkBoxes = driver.findElements(By.name("chkbox"));
		
		for (WebElement chkBox : chkBoxes) {
			if ((chkBox.getAttribute("value").equals("Check 3")) 
					|| (chkBox.getAttribute("value").equals("Check 4"))){
				chkBox.click();
			}	
		}
		
		assertTrue(chkBoxes.get(2).isSelected());
		assertTrue(chkBoxes.get(3).isSelected());
		
		assertFalse(chkBoxes.get(0).isSelected());
		assertFalse(chkBoxes.get(1).isSelected());
	}
	
	@Test
	public void testDropDownSingle() throws InterruptedException {
		WebElement dropSingle = driver.findElement(By.name("dropdownlist"));
		
		Select dropSelect = new Select(dropSingle);
		
		dropSelect.selectByValue("item1");
		Thread.sleep(2000);
		dropSelect.selectByIndex(5);
		Thread.sleep(2000);
		dropSelect.selectByVisibleText("Item 7");
				
		assertEquals("Item 7", dropSelect.getFirstSelectedOption().getText());
	}
	
	@Test
	public void testDropDownMulti() {
		WebElement dropMulti = driver.findElement(By.name("multiselectdropdown"));
		Select multiDrop = new Select(dropMulti);
		
		assertTrue(multiDrop.isMultiple());
		
		multiDrop.selectByVisibleText("Item 5");
		multiDrop.selectByVisibleText("Item 8");
		multiDrop.selectByVisibleText("Item 9");
		
		//multiDrop.deselectByVisibleText("Item 9");
		
		List<WebElement> optionsSelected = multiDrop.getAllSelectedOptions();
		
		assertEquals(3, optionsSelected.size());
		assertEquals("Item 5", optionsSelected.get(0).getText());
		assertEquals("Item 8", optionsSelected.get(1).getText());
		assertEquals("Item 9", optionsSelected.get(2).getText());
		
		multiDrop.deselectAll();
		
	}

}
