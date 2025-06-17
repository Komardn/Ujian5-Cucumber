package com.juaracoding.apitest.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.juaracoding.apitest.DriverSingleton;
import com.juaracoding.apitest.pages.SignInPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestCase01AuthValid {
  WebDriver driver;
  SignInPage signInPage;

  @Given("Buka halaman login untuk pengujian login valid")
  public void testStep01() {
    driver = DriverSingleton.createOrGetDriver();
    driver.get("https://www.saucedemo.com/v1/index.html");
    signInPage = new SignInPage(driver);
  }

  @When("Masukkan username {string} dan password {string} valid")
  public void testStep02(String username, String password) throws InterruptedException {
    Thread.sleep(1000);
    signInPage.setUsername(username);
    Thread.sleep(1000);
    signInPage.setPassword(password);
  }

  @And("Klik tombol login untuk login valid")
  public void testStep03() {
    signInPage.onClick();
  }

  @Then("Pengguna berhasil masuk ke halaman produk")
  public void testStep04() {
    String expected = "https://www.saucedemo.com/v1/inventory.html";
    String actual = driver.getCurrentUrl();
    Assert.assertEquals(actual, expected);
  }
}
