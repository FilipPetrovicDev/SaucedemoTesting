import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC01LoginVerifyElementsLogout extends CommonStrings{

    @Test
    public void SuccesfullLoginElementVerifyAndLogout() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        System.out.println("@Test - SuccesfullLoginElementVerifyAndLogout");

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


        // Pronalazenje i verifikovanje heder-a Products
        String productHeader = driver.findElement(By.className("title")).getText();
        Assert.assertTrue(true, productHeader);


        // Pronalazenje i verifikovanje Shopping cart
        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        shoppingCart.click();
        String actualCartUrl = CART_PAGE_URL;
        String expectedCartUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualCartUrl, expectedCartUrl);


        // Izlazak sa your cart strane
        WebElement continueShopping = driver.findElement(By.id("continue-shopping"));
        continueShopping.click();
        String actualBackToProductsPageUrl = INVENTORY_PAGE_URL;
        String expectedContinueShoppingUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualBackToProductsPageUrl, expectedContinueShoppingUrl);


        // Pronalazenje i verifikovanje dugmeta Burger menu
        WebElement burgerButton = driver.findElement(By.id("react-burger-menu-btn"));
        burgerButton.click();
        // explicit wait
        // elementToBeClickable
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));

        WebElement burgerMenu = driver.findElement(By.className("bm-menu"));
        Assert.assertEquals(true, burgerMenu.isDisplayed());


        // Izlazenje iz burger menu
        WebElement exitBurgerButton = driver.findElement(By.id("react-burger-cross-btn"));
        exitBurgerButton.click();
        Assert.assertTrue(true);

        Thread.sleep(3);

        // Pronalazenje i verifikovanje twitter linka
        WebElement twitterHref = driver.findElement(By.xpath("//a[@href='https://twitter.com/saucelabs']"));
        twitterHref.click();
        String actualTwitterUrl = "https://twitter.com/saucelabs";
        String expectedTwitterUrl = TWITTER_PAGE_URL;
        Assert.assertEquals(actualTwitterUrl, expectedTwitterUrl);


        // Pronalazenje i verifikovanje facebook linka
        WebElement facebookHref = driver.findElement(By.xpath("//a[@href='https://www.facebook.com/saucelabs']"));
        facebookHref.click();
        String actualFacebookUrl = "https://www.facebook.com/saucelabs";
        String expectedFacebookUrl = FACEBOOK_PAGE_URL;
        Assert.assertEquals(actualFacebookUrl, expectedFacebookUrl);


        // Pronalazenje i verifikovanje linkedin linka
        WebElement linkedinHref = driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/sauce-labs/']"));
        linkedinHref.click();
        String actualLinkedinUrl = "https://www.linkedin.com/company/sauce-labs/";
        String expectedLinkedinUrl = LINKEDIN_PAGE_URL;
        Assert.assertEquals(actualLinkedinUrl, expectedLinkedinUrl);


        // Pronalazenje i verifikovanje logout
        WebElement burgerButtonLogout = driver.findElement(By.id("react-burger-menu-btn"));
        burgerButtonLogout.click();
        // explicit wait
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
