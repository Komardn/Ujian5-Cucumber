package com.juaracoding.apitest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class BrowserManager {
  public static WebDriver build() {
    EdgeOptions options = new EdgeOptions();
    options.addArguments("--start-maximized");

    return new EdgeDriver(options);
  }
}
