package com.selenium.controller;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;


public class LoginMail {
    static String readyStateComplete;
    static WebDriver driver;
    public static Boolean TF = null;
    static String userPC;
    static String pathChromeDriver;
    static String profileChrome;
    static String pageSource1;
    static String pageSource2;
    static String pageSource3;
    static String pageSource4;
    public static Boolean CheckAcc(String userName, String passWord, String recoveryMail, Integer i) throws InterruptedException {

        userPC = System.getProperty("user.name");
        System.out.println(userPC);
        pathChromeDriver = "/home/" + userPC + "/Documents/GmailAutoSend/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathChromeDriver);
        ChromeOptions options = new ChromeOptions();
        profileChrome = "user-data-dir=/home/" + userPC + "/.config/google-chrome/Profile " + i.toString();
        options.addArguments(profileChrome);

        driver = new ChromeDriver(options);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://accounts.google.com/signin");

        Thread.sleep(300);

        ExpectedCondition<Boolean> pageLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                if (js.executeScript("return document.readyState")
                        .toString().equals("complete")) {
                    return true;
                } else return false;
            }
        };
        wait.until(pageLoad);
        //Thread.sleep(4000);
        pageSource1 = driver.getPageSource();
        System.out.println("GetPageSource1");
        //Thread.sleep(1000);
        if (pageSource1.contains("My Account gives you") || pageSource1.contains("Tài khoản của tôi cho phép")) {
            driver.quit();
            driver = null;
            pageSource1 = null;
            TF = true;
            System.out.println("Account Actived!");

        } else if (pageSource1.contains("Đăng nhập - Tài khoản Google") || pageSource1.contains("Sign in - Google Accounts")) {
            System.out.println("Enter the username");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#identifierId")));
            driver.findElement(By.cssSelector("#identifierId")).sendKeys(userName);
            driver.findElement(By.cssSelector("#identifierId")).sendKeys(Keys.ENTER);
            Thread.sleep(300);
            wait.until(pageLoad);
            pageSource1 = null;
            pageSource2 = driver.getPageSource();
            System.out.println("GetPageSource2");
            //Thread.sleep(1000);
            if (pageSource2.contains("Nhập mật khẩu của bạn") || pageSource2.contains("Enter your password")) {
                System.out.println("Enter the password");
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#password > div.aCsJod.oJeWuf >" +
                        " div > div.Xb9hP > input")));
                driver.findElement(By.cssSelector("#password > div.aCsJod.oJeWuf >" +
                        " div > div.Xb9hP > input")).sendKeys(passWord);
                driver.findElement(By.cssSelector("#password > div.aCsJod.oJeWuf >" +
                        " div > div.Xb9hP > input")).sendKeys(Keys.ENTER);
                Thread.sleep(300);
                wait.until(pageLoad);
                pageSource2 = null;
                pageSource3 = driver.getPageSource();
                System.out.println("GetPageSource3");
                //Thread.sleep(1000);
                if (pageSource3.contains("Confirm your recovery email") || pageSource3.contains("Xác nhận email khôi phục của bạn")) {
                    System.out.println("Enter the recovery Mail");
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#view_container > form > div.mbekbe.bxPAYd > div >" +
                            " div > div > ul > li:nth-child(1) > div > div.vdE7Oc")));
                    driver.findElement(By.cssSelector("#view_container > form > div.mbekbe.bxPAYd > div >" +
                            " div > div > ul > li:nth-child(1) > div > div.vdE7Oc")).click();

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#knowledge-preregis" +
                            "tered-email-response")));
                    driver.findElement(By.cssSelector("#knowledge-preregis" +
                            "tered-email-response")).sendKeys(recoveryMail);

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#next > content > span"))).click();
                    driver.findElement(By.cssSelector("#next > content > span")).click();
                    Thread.sleep(300);

                    wait.until(pageLoad);
                    pageSource3 = null;
                    pageSource4 = driver.getPageSource();
                    System.out.println("Get PageSource4");
                    //Thread.sleep(1000);
                    if (CheckActive(pageSource4)) {
                        //System.out.println("CheckActive page source");
                        driver.quit();
                        driver = null;
                        pageSource4 = null;
                    } else {
                        //System.out.println("CheckActive page source");
                        driver.quit();
                        driver = null;
                        pageSource4 = null;
                    }

                } else if (CheckActive(pageSource3)) {
                    //System.out.println("CheckActive page source");
                    driver.quit();
                    driver = null;
                    pageSource3 = null;
                    //
                    //
                } else if (!CheckActive(pageSource3)){
                    //System.out.println("!CheckActive page source");
                    driver.quit();
                    driver = null;
                    pageSource3 = null;
                }
            } else if (pageSource2.contains("Mật khẩu của bạn đã được thay đổi ") ||
                    pageSource2.contains("Your password was changed")) {
                System.out.println("Password was changed!");
                driver.quit();
                driver = null;
                pageSource2 = null;
                TF = false;
            } else if (pageSource2.contains("Couldn't find your Google Account")||
                    pageSource2.contains("Không thể tìm thấy Tài khoản Google của bạn")) {
                System.out.println("Error username");
                driver.quit();
                driver = null;
                pageSource2 = null;
                TF = false;
            }
        }
        return TF;
    }

    public static Boolean CheckActive(String pageSource) {
        if (pageSource.contains("Sai mật khẩu. Hãy thử lại hoặc nhấp")||pageSource.contains("Wrong password. Try again or click Forgot")) {
            System.out.println("Error Password");
            TF = false;
        } else if (pageSource.contains("Đã vô hiệu hóa tài khoản")||pageSource.contains("Account disabled")) {
            System.out.println("Account Disable!");
            TF = false;
        } else if (pageSource.contains("Bảo vệ tài khoản của bạn")||pageSource.contains("Protect your account")) {
            System.out.println("Actived!");
            TF = true;
        } else if (pageSource.contains("Verify your identity")||pageSource.contains("Xác minh danh tính của bạn")) {
            System.out.println("Verify your identity!");
            TF = false;
        } else if (pageSource.contains("2-Step Verification")||pageSource.contains("Xác minh 2 bước")) {
            System.out.println("2-Step Verification");
            TF = false;
        } else if (pageSource.contains("Email bạn đã nhập không chính xác")||pageSource.contains("The email you entered is incorrect.")) {
            System.out.println("Error Recovery Mail");
            TF = false;
        } else if (pageSource.contains("My Account gives you quick access")||pageSource.contains("Tài khoản của tôi cho phép")) {
            System.out.println("Actived!");
            TF = true;
        } else if (pageSource.contains("Your password was changed")||pageSource.contains("Mật khẩu của bạn đã được thay đổi")) {
            System.out.println("Password was changed");
            TF = false;
        }
        return TF;
    }
}

