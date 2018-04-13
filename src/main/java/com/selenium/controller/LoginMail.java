package com.selenium.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

public class LoginMail {
    //String nodeUrl = "http://172.19.0.3:5555/wd/hub/";
    //String userName = "Lethanhto0765";
    //String passWord = "gyhcbnghrg";
    //String recoveryMail = "Hoangvantuong0864@gmail.com";
    static String pageSource;
    public static Boolean TF = null;
    public static Boolean CheckAcc(String userName, String passWord, String recoveryMail, Integer i) throws InterruptedException {

        String userPC = System.getProperty("user.name");
        System.out.println(userPC);
        String pathChromDriver = "/home/" + userPC + "/Documents/GmailAutoSend/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathChromDriver);
        ChromeOptions options = new ChromeOptions();
        String profileChrome = "user-data-dir=/home/" + userPC + "/.config/google-chrome/Profile " + i.toString();
        options.addArguments(profileChrome);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.navigate().to("https://accounts.google.com/signin");
        //Thread.sleep(50000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        pageSource = driver.getPageSource();
        System.out.println("GetPageSource1");
        if (pageSource.contains("My Account gives you") || pageSource.contains("Tài khoản của tôi cho phép")) {
            driver.quit();
            TF = true;
            System.out.println("Account Actived!");

        } else if (pageSource.contains("Đăng nhập - Tài khoản Google") || pageSource.contains("Sign in - Google Accounts")) {
            System.out.println("Enter the username");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#identifierId"))).sendKeys(userName);
            // findElement(By.cssSelector("#identifierId")).sendKeys(userName);
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#identifierNext > content > span"))).click();
            //driver.findElement(By.cssSelector("#identifierNext > content > span")).click();
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            pageSource = driver.getPageSource();
            System.out.println("GetPageSource2");
            if (pageSource.contains("Nhập mật khẩu của bạn") || pageSource.contains("Enter your password")) {
                System.out.println("Enter the password");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#password > div.aCsJod.oJeWuf >" +
                        " div > div.Xb9hP > input"))).sendKeys(passWord);
                //driver.findElement(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input"))
                //.sendKeys(passWord);
                Thread.sleep(1000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#passwordNext > content > span"))).click();
                //driver.findElement(By.cssSelector("#passwordNext > content > span")).click();
                Thread.sleep(1000);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                pageSource = driver.getPageSource();
                System.out.println("GetPageSource3");
                if (pageSource.contains("Confirm your recovery email") || pageSource.contains("Xác nhận email khôi phục của bạn")) {
                    System.out.println("Enter the recovery Mail");
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#view_container > form > div.mbekbe.bxPAYd > div >" +
                            " div > div > ul > li:nth-child(1) > div > div.vdE7Oc"))).click();
                    //driver.findElement(By.cssSelector("#view_container > form > div.mbekbe.bxPAYd > div >" +
                    //        " div > div > ul > li:nth-child(1) > div > div.vdE7Oc")).click();
                    Thread.sleep(1000);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#knowledge-preregis" +
                            "tered-email-response"))).sendKeys(recoveryMail);
                    //driver.findElement(By.cssSelector("#knowledge-preregis" +
                    //        "tered-email-response")).sendKeys(recoveryMail);
                    Thread.sleep(1000);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#next > content > span"))).click();
                    //driver.findElement(By.cssSelector("#next > content > span")).click();
                    Thread.sleep(1000);
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    pageSource = driver.getPageSource();
                    System.out.println("Get PageSource4");
                    if (CheckActive(pageSource)) {
                        driver.quit();
                    } else {
                        driver.quit();
                    }

                } else {
                    if (CheckActive(pageSource)) {
                        driver.quit();
                        //
                        //
                    } else {
                        driver.quit();
                        //
                        //
                    }
                }
            } else if (pageSource.contains("Mật khẩu của bạn đã được thay đổi ") ||
                    pageSource.contains("Your password was changed")) {
                System.out.println("Password was changed!");
                driver.quit();
                TF = false;
            }
        } else {
            System.out.println("Error username");
            driver.quit();
            TF = false;
        }
        return TF;
    }

    public static Boolean CheckActive(String pageSource) {
        if (pageSource.contains("Sai mật khẩu. Hãy thử lại hoặc nhấp") || pageSource.contains("Wrong password. Try ")) {
            System.out.println("Error Password");
            TF = false;
        } else if (pageSource.contains("Đã vô hiệu hóa tài khoản") || pageSource.contains("Account disabled")) {
            System.out.println("Account Disable!");
            TF = false;
        } else if (pageSource.contains("Bảo vệ tài khoản của bạn") || pageSource.contains("Protect your account")) {
            System.out.println("Actived!");
            TF = true;
        } else if (pageSource.contains("Verify your identity")||pageSource.contains("Xác minh danh tính của bạn")) {
            System.out.println("Verify your identity!");
            TF = false;
        } else if (pageSource.contains("2-Step Verification")||pageSource.contains("Xác minh 2 bước")) {
            System.out.println("2-Step Verification");
            TF = false;
        } else if (pageSource.contains("Email bạn đã nhập không chính xác") || pageSource.contains("The email you entered is incorrect.")) {
            System.out.println("Error Recovery Mail");
            TF = false;
        } else if (pageSource.contains("My Account gives you") || pageSource.contains("Tài khoản của tôi cho phép")) {
            System.out.println("Actived!");
            TF = true;
        }
        return TF;
    }
}

