package com.core;

import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		
		String typeBrowser = GlobarProperty.getProperty("brownser");
		
		if (driver == null) {
			
			if (typeBrowser.equals("firefox")) {
			
				WebDriverManager.firefoxdriver().setup();			
				driver = new FirefoxDriver();
			}
			else if (typeBrowser.equals("firefox-headless")) {
			    FirefoxOptions options = new FirefoxOptions();
			    options.addArguments("--headless");
			    options.addArguments("--window-size=1200x860");
				
				WebDriverManager.firefoxdriver().setup();			
				driver = new FirefoxDriver(options);
			}
			else if (typeBrowser.equals("chrome")) {
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			}
			else if (typeBrowser.equals("chrome-headless")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
			    options.addArguments("--window-size=1200x860");
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				
			}
			else {
				//Gerar uma exception erro
				System.out.println("Browser inválido");
			}
									
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
