package com.juaracoding.apitest.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.juaracoding.apitest.DriverSingleton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class TestCase06CheckoutdanLogout {
    WebDriver driver;

    @Given("Pengguna login dan berada di halaman ringkasan pembelian")
    public void testStep10() throws InterruptedException {
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://www.saucedemo.com/v1/index.html");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);

        WebElement addToCartButton = driver.findElement(By.cssSelector(".inventory_item:nth-child(1) .btn_inventory"));
        addToCartButton.click();

        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        cartIcon.click();
        Thread.sleep(1000);

        WebElement checkoutButton = driver.findElement(By.className("checkout_button"));
        checkoutButton.click();
        Thread.sleep(1000);

        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        firstNameInput.clear();
        firstNameInput.sendKeys("Test");

        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        lastNameInput.clear();
        lastNameInput.sendKeys("User");

        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
        postalCodeInput.clear();
        postalCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.className("cart_button"));
        continueButton.click();
    }

    @When("Pengguna klik tombol Finish untuk menyelesaikan pembelian")
    public void testStep11() throws InterruptedException {
        WebElement finishButton = driver.findElement(By.className("cart_button"));
        finishButton.click();
        Thread.sleep(1000);
    }

    @Then("Muncul pesan THANK YOU FOR YOUR ORDER")
    public void testStep12() {
        WebElement thankYouMessage = driver.findElement(By.className("complete-header"));
        Assert.assertEquals(thankYouMessage.getText().trim(), "THANK YOU FOR YOUR ORDER");
    }

    @When("Pengguna klik ikon menu dan pilih Logout")
    public void testStep13() throws InterruptedException {
        WebElement menuIcon = driver.findElement(By.cssSelector(".bm-burger-button"));
        menuIcon.click();
        Thread.sleep(1000);

        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
        Thread.sleep(1000);
    }

    @Then("Pengguna berhasil logout dan berada di halaman login")
    public void testStep14() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginButton.isDisplayed(), "Login button is not displayed, user is not logged out.");
    }
}
