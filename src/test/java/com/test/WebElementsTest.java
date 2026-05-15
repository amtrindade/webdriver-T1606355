package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.core.BaseTest;

public class WebElementsTest extends BaseTest{
	

	@BeforeEach
	public void setUp() throws Exception {	
		getDriver().get("https://antoniotrindade.com.br/treinoautomacao/elementsweb.html");		
	}

	@Test
	public void testHelloWorld() {
		//1. Identificar o elemento
		WebElement tfEnable = getDriver().findElement(By.name("txtbox1"));
		
		//2. Fazer a interação com o elemento enviando um valor
		tfEnable.sendKeys("Hello World!!!");
		
		//3. Valida o resultado esperado
		assertEquals("Hello World!!!", tfEnable.getAttribute("value"));	
	}
	
	@Test
	public void testTextFieldEnableDisable() {
		WebElement tfDisabled = getDriver().findElement(By.name("txtbox2"));
		WebElement tfEnable = getDriver().findElement(By.name("txtbox1"));
		
		
		assertFalse(tfDisabled.isEnabled(), "tfDisable deveria estar desabilitado!");
		assertTrue(tfEnable.isEnabled(), "tfEnable deveria estar habilitado!");
	}
	
	@Test
	public void testRadioButton() {
		List<WebElement> rbLista = getDriver().findElements(By.name("radioGroup1"));
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
		List<WebElement> chkBoxes = getDriver().findElements(By.name("chkbox"));
		
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
		WebElement dropSingle = getDriver().findElement(By.name("dropdownlist"));
		
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
		WebElement dropMulti = getDriver().findElement(By.name("multiselectdropdown"));
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
	
	@Disabled("Bug cadastrado no bucktracker com o codigo ABC907")
	@Test
	public void testIFrame() {
		//Entra no iframe
		getDriver().switchTo().frame("frame1");
		
		WebElement tfIframe = getDriver().findElement(By.id("tfiframe"));
		tfIframe.sendKeys("Curso automação com Selenium WebDriver");
		assertEquals("Curso automação com Selenium WebDriver", tfIframe.getAttribute("value"));	
		
		//Sai do iframe e volta a origem
		getDriver().switchTo().defaultContent();
		
		WebElement btnAlert = getDriver().findElement(By.name("alertbtn"));
		assertEquals("Alert", btnAlert.getAttribute("value"));		
	}
	
	@Test
	public void testAlert() throws InterruptedException {
		
		WebElement btnAlert = getDriver().findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alert = getDriver().switchTo().alert();
		assertEquals("Eu sou um alerta!", alert.getText());
		alert.accept();
		
	}
	
	@Test
	public void testConfirm() {
		
		WebElement btnConfirm = getDriver().findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert confirm = getDriver().switchTo().alert();
		assertEquals("Pressione um botão!", confirm.getText());		
		confirm.dismiss();		
	}
	
	@Test
	public void testPrompt() throws InterruptedException {
		
		WebElement btnPrompt = getDriver().findElement(By.id("promptBtn"));
		btnPrompt.click();
		
		Alert prompt = getDriver().switchTo().alert();
		assertEquals("Digite o ano:", prompt.getText());
		
		prompt.sendKeys("2026");
		//Só para visualizar
		Thread.sleep(2000);
		prompt.accept();
		
		Alert alert1 = getDriver().switchTo().alert();
		assertEquals("O ano é 2026?", alert1.getText());
		//Só para visualizar
		Thread.sleep(2000);
		alert1.accept();
		
		Alert alert2 = getDriver().switchTo().alert();
		assertEquals("Feito!", alert2.getText());
		alert2.accept();
	}
}
