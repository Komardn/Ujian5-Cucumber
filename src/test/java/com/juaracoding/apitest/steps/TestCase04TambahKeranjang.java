package com.juaracoding.apitest.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.juaracoding.apitest.DriverSingleton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestCase04TambahKeranjang {
    WebDriver driver;

    @Given("Login dengan user valid untuk tambah produk ke keranjang")
    public void testStep04() throws InterruptedException {
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
    }

    @When("Tambah produk ke keranjang dan buka halaman keranjang")
    public void testStep05() throws InterruptedException {
        WebElement addToCartButton = driver.findElement(By.cssSelector(".inventory_item:nth-child(1) .btn_inventory"));
        addToCartButton.click();
        Thread.sleep(1000);

        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        cartIcon.click();
        Thread.sleep(1000);
    }

    @Then("Produk muncul di halaman keranjang")
    public void testStep06() {
        boolean productPresent = driver.findElements(By.cssSelector(".cart_item")).size() > 0;
        Assert.assertTrue(productPresent, "Produk tidak ditemukan di halaman keranjang.");
    }
}
