package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class DragAndDropTest extends BaseTest {
	
	
	@Test
	public void testDragAndDropAction() throws InterruptedException {
		getDriver().get("https://jqueryui.com/resources/demos/droppable/default.html");
		
		WebElement divDraggable = getDriver().findElement(By.id("draggable"));
		WebElement divDroppable = getDriver().findElement(By.id("droppable"));
		
		assertEquals("Drag me to my target", divDraggable.getText());
		assertEquals("Drop here", divDroppable.getText());
		
		new Actions(getDriver())
			.dragAndDrop(divDraggable, divDroppable)
			.perform();
		
		assertEquals("Dropped!", divDroppable.getText());
		
		Thread.sleep(3000);
		
		new Actions(getDriver())
			.dragAndDropBy(divDraggable, 765, 460)
			.perform();
		
	}
	
	@Test
	public void testDragAndDropMootools() throws InterruptedException {
		
		getDriver().get("https://sahitest.com/demo/dragDropMooTools.htm");
		
		WebElement origin = getDriver().findElement(By.id("dragger"));
		
		Point position = origin.getLocation();

//		System.out.println("X: " + position.getX());
//		System.out.println("Y: " + position.getY());
		
		WebElement item1 = getDriver().findElement(By.xpath("//div[.='Item 1']"));
		WebElement item2 = getDriver().findElement(By.xpath("//div[.='Item 2']"));
		WebElement item3 = getDriver().findElement(By.xpath("//div[.='Item 3']"));
		WebElement item4 = getDriver().findElement(By.xpath("//div[.='Item 4']"));
		
		assertEquals("Item 1", item1.getText());
		assertEquals("Item 2", item2.getText());
		assertEquals("Item 3", item3.getText());
		assertEquals("Item 4", item4.getText());
		
		new Actions(getDriver())
			.dragAndDrop(origin, item1)
			.perform();
		
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

		wait.until(driver -> {
		    int x = position.getX();
		    int y = position.getY();

		    return x >= 7 && x <= 9 && y >= 114 && y <= 116;
		});
		
		new Actions(getDriver())
			.dragAndDrop(origin, item3)
			.perform();
		
		assertEquals("dropped", item1.getText());
		assertEquals("Item 2", item2.getText());
		assertEquals("dropped", item3.getText());
		assertEquals("Item 4", item4.getText());
	}

}
