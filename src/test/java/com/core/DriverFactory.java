package com.core;

import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		
		if (driver == null) {
			
			WebDriverManager.firefoxdriver().setup();			
			driver = new FirefoxDriver();
			
			//Abrir o browser no monitor auxiliar
			Point point = new Point(-1500, -150); 
			driver.manage().window().setPosition(point);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			
		}		
		return driver;
	}
	
	public static void killDriver() {
		
		if (driver != null) {
			driver.quit();
			driver = null;
		}	
	}
}
