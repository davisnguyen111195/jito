package com.selenium.controller;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;


import java.util.concurrent.TimeUnit;

import static com.selenium.controller.Controller.*;

public class AutoG {
    static WebDriver driver = null;
    static Boolean TF = null;
    static String userPC = System.getProperty("user.name");
    static String pathChromeDriver = null;
    static String profileChrome = null;
    public static Boolean AutoLogin(String user, String pass, String recoveryMail, Integer i) throws InterruptedException {
        //Setup Chrome

        System.out.println(userPC);
        pathChromeDriver = "/home/" + userPC + "/Documents/GmailAutoSend/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);
        ChromeOptions options = new ChromeOptions();
        profileChrome = "user-data-dir=/home/" + userPC + "/.config/google-chrome/Profile " + i.toString();
        options.addArguments(profileChrome);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        driver.get("https://accounts.google.com/signin");

        //Init Object Controller
        //Thread.sleep(10000000);
        Controller controller = new Controller();
        PageFactory.initElements(driver, controller);
        if (waitForElement(LoginSuccess) == 1) {
            driver.quit();
            TF = true;
            driver = null;
            System.out.println("Account Actived");
        }else {
            //------------Script--------------
            //Step 1: Enter username
            type(UserName, "Username", user);
            type(UserName, "Enter", Keys.ENTER);
            //Step 2: Enter password
            if (waitForElement(PassWord) == 1) {
                type(PassWord, "Password", pass);
                type(PassWord, "Enter", Keys.ENTER);
                //Step 3: Check Login
                if (waitForElement(LoginSuccess) == 1) {
                    driver.quit();
                    TF = true;
                    driver = null;
                    System.out.println("Account Actived");
                } else if (waitForElement(TwoStepVerification) == 1||waitForElement(SMSPhone) == 1) {
                    driver.quit();
                    TF = false;
                    driver = null;
                    System.out.println("Verification");
                } else if (waitForElement(Protected) == 1) {
                    driver.quit();
                    TF = true;
                    driver = null;
                    System.out.println("Account Actived");
                } else if (waitForElement(AccountDisable) == 1) {
                    driver.quit();
                    TF = false;
                    driver = null;
                    System.out.println("AccountDisable");
                } else if (waitForElement(RecoveryMailHelp) == 1) {
                    click(ClickRecoveryMail, "Click recovery mail");
                    type(RecoveryMail, "Recovery Mail", recoveryMail);
                    type(RecoveryMail, "Enter", Keys.ENTER);
                    if (waitForElement(LoginSuccess) == 1) {
                        driver.quit();
                        TF = true;
                        driver = null;
                        System.out.println("Account Actived");
                    } else if (waitForElement(TwoStepVerification) == 1 || waitForElement(SMSPhone) == 1) {
                        driver.quit();
                        TF = false;
                        driver = null;
                        System.out.println("Verification");
                    } else if (waitForElement(Protected) == 1) {
                        driver.quit();
                        TF = true;
                        driver = null;
                        System.out.println("Account Actived");
                    } else if (waitForElement(AccountDisable) == 1) {
                        driver.quit();
                        TF = false;
                        driver = null;
                        System.out.println("Account Disable");
                    }
                } else if (waitForElement(Captcha) == 1) {
                    driver.quit();
                    TF = false;
                    driver = null;
                    System.out.println("Captcha");
                } else {
                    driver.quit();
                    TF = false;
                    driver = null;
                    System.out.println("Error Password");
                }
            } else {
                driver.quit();
                TF = false;
                driver = null;
                System.out.println("Error Username");
            }
        }
        return TF;
    }
}
