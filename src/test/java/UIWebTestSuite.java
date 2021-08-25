import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UIWebTestSuite {

    @Test
    public void ErrorFormValidationTest(){
        WebDriver driver = new ChromeDriver();

        driver.get("https://d1iw6mb9di5l9r.cloudfront.net/#/");

        WebElement usersIcon = driver.findElement(By.className("users"));

        System.out.println(usersIcon.getText());

        usersIcon.click();

        WebElement loginDialog = driver.findElement(By.id("loginDialog"));

        new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOf(loginDialog));

        WebElement loginBtn = driver.findElement(By.cssSelector("div.v-card__actions>#loginButton"));

        loginBtn.click();

        List<WebElement> validationWarningText = driver.findElements(By.cssSelector("div.v-messages__message"));
        new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOf(validationWarningText.get(0)));

        for(var messageElement: validationWarningText){
            Assertions.assertEquals("Invalid user and password",messageElement.getText());
        }
    }

    @Test
    public void FormsPageFormSubmissionValidationTest() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://d1iw6mb9di5l9r.cloudfront.net/#/forms");

        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("Andy Jones");


        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("andy.jone@gmail.com");


//        WebElement stateDropDownIcon = driver.findElement(By.cssSelector(".v-input__icon>i"));
//        stateDropDownIcon.click();

        //WebElement stateListModal = driver.findElement(By.cssSelector("div#list-79"));
        //new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOf(stateListModal));

        WebElement stateItem = driver.findElement(By.id("state"));
        stateItem.sendKeys("NSW");

        WebElement agreeCheckbox = driver.findElement(By.cssSelector(".v-input__slot>.v-input--selection-controls__input"));
        agreeCheckbox.click();

        List<WebElement> formsButtons = driver.findElements(By.cssSelector("button.v-btn.v-btn--is-elevated.v-btn--has-bg.theme--light.v-size--default"));
        formsButtons.get(0).click();

        List<WebElement> errorMessages = driver.findElements(By.cssSelector(".form-error.mb-4"));
        //System.out.println(errorMessages.get(0).getText());
        for (WebElement errorMessage : errorMessages)
        {
            //System.out.println(errorMessage.getText());
            Assertions.assertEquals("",errorMessage.getText());
        }
    }

    
}
