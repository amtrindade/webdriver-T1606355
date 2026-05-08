package com.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DragAndDropTest {
	
	public WebDriver driver;

	@BeforeEach
	public void setUp() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		
		driver = new FirefoxDriver();
		
		//Abrir o browser no monitor auxiliar
		Point point = new Point(-1500, -150); 
		driver.manage().window().setPosition(point);		
	}

	@AfterEach
	public void tearDown() throws Exception {
		// tempo de 2s para visualizar
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void testDragAndDropAction() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/droppable/default.html");
		
		WebElement divDraggable = driver.findElement(By.id("draggable"));
		WebElement divDroppable = driver.findElement(By.id("droppable"));
		
		assertEquals("Drag me to my target", divDraggable.getText());
		assertEquals("Drop here", divDroppable.getText());
		
		new Actions(driver)
			.dragAndDrop(divDraggable, divDroppable)
			.perform();
		
		assertEquals("Dropped!", divDroppable.getText());
		
		Thread.sleep(3000);
		
		new Actions(driver)
			.dragAndDropBy(divDraggable, 765, 460)
			.perform();
		
	}
	
	@Test
	public void testDragAndDropMootools() throws InterruptedException {
		
		driver.get("https://sahitest.com/demo/dragDropMooTools.htm");
		
		WebElement origin = driver.findElement(By.id("dragger"));
		
		Point position = origin.getLocation();

		System.out.println("X: " + position.getX());
		System.out.println("Y: " + position.getY());
		
		WebElement item1 = driver.findElement(By.xpath("//div[2]"));
		WebElement item2 = driver.findElement(By.xpath("//div[3]"));
		WebElement item3 = driver.findElement(By.xpath("//div[4]"));
		WebElement item4 = driver.findElement(By.xpath("//div[5]"));
		
		assertEquals("Item 1", item1.getText());
		assertEquals("Item 2", item2.getText());
		assertEquals("Item 3", item3.getText());
		assertEquals("Item 4", item4.getText());
		
		new Actions(driver)
			.dragAndDrop(origin, item1)
			.perform();
		
		//TODO rever esperas por coordenada de tela
		Thread.sleep(500);
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		wait.until(driver -> {
//		    WebElement element = driver.findElement(By.id("meuElemento"));
//
//		    Point location = element.getLocation();
//
//		    int x = location.getX();
//		    int y = location.getY();
//
//		    return x >= 295 && x <= 305 &&
//		           y >= 495 && y <= 505;
//		});
		
		new Actions(driver)
			.dragAndDrop(origin, item3)
			.perform();
		
		assertEquals("dropped", item1.getText());
		assertEquals("Item 2", item2.getText());
		assertEquals("dropped", item3.getText());
		assertEquals("Item 4", item4.getText());
	}
	

}
