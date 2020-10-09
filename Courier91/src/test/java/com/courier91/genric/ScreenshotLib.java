package com.courier91.genric;



import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ScreenshotLib {
	public void takeScreenshot(WebDriver driver, String fileName) {
		EventFiringWebDriver efd= new EventFiringWebDriver(driver);
		File srcFile = efd.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+fileName+".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
