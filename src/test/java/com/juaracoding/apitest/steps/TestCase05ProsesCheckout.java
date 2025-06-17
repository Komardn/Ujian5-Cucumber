package com.juaracoding.apitest.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.juaracoding.apitest.DriverSingleton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class TestCase05ProsesCheckout {
    WebDriver driver;

    @Given("Produk sudah ditambahkan ke keranjang dan berada di halaman keranjang")
    public void testStep07() throws InterruptedException {
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://www.saucedemo.com/v1/index.html");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.clear();
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        Thread.sleep(1000);

        WebElement addToCartButton = driver.findElement(By.cssSelector(".inventory_item:nth-child(1) .btn_inventory"));
        addToCartButton.click();

        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        cartIcon.click();
        Thread.sleep(1000);
    }

    @When("Pengguna klik tombol Checkout dan mengisi informasi pelanggan")
    public void testStep08() throws InterruptedException {
        WebElement checkoutButton = driver.findElement(By.className("checkout_button"));
        checkoutButton.click();
        Thread.sleep(1000);

        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        firstNameInput.clear();
        firstNameInput.sendKeys("Test");

        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        lastNameInput.clear();
        lastNameInput.sendKeys("User ");

        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
        postalCodeInput.clear();
        postalCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.className("cart_button"));
        continueButton.click();
        Thread.sleep(1000);
    }

    @Then("Pengguna diarahkan ke halaman ringkasan pembelian")
    public void testStep09() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two.html"), "Pengguna tidak berada di halaman ringkasan pembelian");

        WebElement summaryElement = driver.findElement(By.className("summary_info"));
        Assert.assertTrue(summaryElement.isDisplayed(), "Halaman ringkasan pembelian tidak ditampilkan");
    }
}
