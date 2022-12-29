import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC02LoginAndBuyingProductsLogout extends CommonStrings{

    @Test
    public void LoginAndBuyingProducts() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        System.out.println("@Test - LoginAndBuyingProducts");

        String baseUrl = "https://www.saucedemo.com/";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get(baseUrl);

        Thread.sleep(3000);

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Pronalazenje polja forme Username
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys(STANDARD_USERNAME);

        // Pronalazenje polja forme Password
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(PASSWORD);

        // Definisanje dugmeta Login i njegovo verifikovanje
        WebElement logInButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        logInButton.click();

        String actualUrl = INVENTORY_PAGE_URL;
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);


        // Pronalazenje i klik na "Sauce Labs backpack"
        WebElement sauceLabsBackpack = driver.findElement(By.id("item_4_img_link"));
        sauceLabsBackpack.click();

        // Pronalazenje i verifikovanje title
        String titleOfBackpack = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']")).getText();
        Assert.assertTrue(true, titleOfBackpack);

        // Pronalazenje i verifikovanje description
        String descriptionOfBackpack = driver.findElement(By.xpath("//div[@class='inventory_details_desc large_size']")).getText();
        Assert.assertTrue(true, descriptionOfBackpack);

        // Pronalazenje i verifikovanje price
        String priceOfBackpack = driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText();
        Assert.assertTrue(true, priceOfBackpack);


        // Pronalazenje, provera klikabilnosti dugmeta Add to cart
        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
        }
        catch (TimeoutException e) {
            System.out.println(e);
        }


        // Pronalazenje, provera klikabilnosti dugmeta
        WebElement backToProducts = driver.findElement(By.id("back-to-products"));
        backToProducts.click();
        String actualBackUrl = INVENTORY_PAGE_URL;
        String expectedBackUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualBackUrl, expectedBackUrl);


        // Pronalazanje i klik dugmeta Add to cart proizvoda Sauce Labs Fleece Jacket
        WebElement addToCartNew = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        addToCartNew.click();
        Assert.assertTrue(true);


        // Odlazak na link Shopping cart
        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        shoppingCart.click();
        String actualCartUrl = CART_PAGE_URL;
        String expectedCartUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualCartUrl, expectedCartUrl);

        // Klik i verifikacija dugmeta Checkout
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
        Assert.assertTrue(true);

        String checkoutActualUrl = CHECKOUT_FIRST_STEP_PAGE_URL;
        String checkoutExpectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(checkoutActualUrl, checkoutExpectedUrl);

        // Popunjavanje forme First Name
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys(FIRTS_NAME);

        // Popunjavanje forme Last Name
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys(LAST_NAME);

        // Popunjavanje forme Postal Code
        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys(POSTAL_CODE);

        // Klik na Continue
        WebElement continueClick = driver.findElement(By.xpath("//input[@id='continue']"));
        continueClick.click();
        Assert.assertTrue(true);

        String continueActualUrl = COMPLETE_PAGE_URL;
        String continueExpectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(continueActualUrl, continueExpectedUrl);

        // Klik na dugme Finish
        WebElement finishClick = driver.findElement(By.id("finish"));
        finishClick.click();
        Assert.assertTrue(true);

        String finishActualUrl = CHECKOUT_COMPLETE_PAGE_URL;
        String finishExpectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(finishActualUrl, finishExpectedUrl);

        // Verifikacija "THANK YOU FOR YOUR ORDER" poruke
        String confirmOrder = driver.findElement(By.className("complete-header")).getText();
        Assert.assertTrue(true, confirmOrder);

        // Odjavljivanje
        // Pronalazenje i verifikovanje logout
        WebElement burgerButtonLogout = driver.findElement(By.id("react-burger-menu-btn"));
        burgerButtonLogout.click();
        // explicit wait
        //WebDriverWait wtlogout = new WebDriverWait(driver, Duration.ofSeconds(3));
        // elementToBeClickable
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        WebElement burgerMenuLogout = driver.findElement(By.className("bm-menu"));
        Assert.assertEquals(true, burgerMenuLogout.isDisplayed());

        WebElement logoutPress = driver.findElement(By.id("logout_sidebar_link"));
        logoutPress.click();
        String logoutUrl = baseUrl;
        Assert.assertEquals(logoutUrl, baseUrl);

        driver.quit();
    }
}
