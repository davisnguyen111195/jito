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
    static String status = null;
    public static Boolean AutoLogin(String user, String pass, String recoveryMail, Integer i) throws InterruptedException {
        //Setup Chrome

        System.out.println(userPC);
        //Windows: "C:\\Users\\" + userPC + "\\Documents\\GmailAutoSend\\chromedriver.exe"
        //Linux: "/home/" + userPC + "/Documents/GmailAutoSend/chromedriver"
        pathChromeDriver = "C:\\Users\\" + userPC + "\\Documents\\GmailAutoSend\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);
        ChromeOptions options = new ChromeOptions();
        //Windows: "user-data-dir=C:\\Users\\" + userPC + "\\AppData\\Local\\Google\\Chrome\\User Data\\Profile "
        //Linux: "user-data-dir=/home/" + userPC + "/.config/google-chrome/Profile "
        profileChrome = "user-data-dir=C:\\Users\\" + userPC + "\\AppData\\Local\\Google\\Chrome\\User Data\\Profile " + i.toString();
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
                    status = ",Verification";
                    System.out.println(status);
                } else if (waitForElement(Protected) == 1) {
                    driver.quit();
                    TF = true;
                    driver = null;
                    System.out.println("Account Actived");
                } else if (waitForElement(AccountDisable) == 1) {
                    driver.quit();
                    TF = false;
                    driver = null;
                    status = ",AccountDisable";
                    System.out.println(status);
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
                        status = ",Verification";
                        System.out.println(status);
                    } else if (waitForElement(Protected) == 1) {
                        driver.quit();
                        TF = true;
                        driver = null;
                        System.out.println("Account Actived");
                    } else if (waitForElement(AccountDisable) == 1) {
                        driver.quit();
                        TF = false;
                        driver = null;
                        status = ",AccountDisable";
                        System.out.println(status);
                    }
                } else if (waitForElement(Captcha) == 1) {
                    driver.quit();
                    TF = false;
                    driver = null;
                    status = ",Captcha";
                    System.out.println(status);

                } else {
                    driver.quit();
                    TF = false;
                    driver = null;
                    status = ",Error Password";
                    System.out.println(status);

                }
            } else {
                driver.quit();
                TF = false;
                driver = null;
                status = ",Error Username";
            }
        }
        return TF;
    }
}
