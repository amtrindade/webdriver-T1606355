package com.core;

import static com.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class BaseTest {
	
	@AfterEach
	public void tearDown(TestInfo testInfo) throws InterruptedException, IOException {
		String nameTest = testInfo.getTestMethod().map(method -> method.getName()).orElse("test-desconhecido");
		
		File screenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("target" + File.separator + nameTest + ".jpg"));
				
		DriverFactory.killDriver();
	}

}
