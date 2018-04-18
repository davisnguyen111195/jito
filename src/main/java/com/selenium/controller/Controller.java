package com.selenium.controller;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Controller {
    @FindBy(how = How.CSS, using = "#identifierId")
    static WebElement UserName;

    @FindBy(how = How.CSS, using = "#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")
    static WebElement PassWord;

    @FindBy(how = How.CSS, using = "#view_container > form > div.mbekbe.bxPAYd > div > div > div > ul > li:nth-child(1) > div > div.vdE7Oc")
    static WebElement ClickRecoveryMail;

    @FindBy(how = How.CSS, using = "#knowledge-preregistered-email-response")
    static WebElement RecoveryMail;

    @FindBy(how = How.XPATH, using = "//*[@id=\"view_container\"]/form/div[2]/div/div[3]/div[1]/div[1]/h2")
    static WebElement TwoStepVerification;

    @FindBy(how = How.CSS, using = "#\\3a vb > div > div")
    static WebElement Compose;

    @FindBy(how = How.XPATH, using = "//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a")
    static WebElement LoginSuccess;

    @FindBy(how = How.XPATH, using = "//*[@id=\"yDmH0d\"]/c-wiz[2]/c-wiz/div/div[1]/div/div/div/div[2]/div[1]/div[2]")
    static WebElement Protected;

    @FindBy(how = How.XPATH, using = "//*[@id=\"yDmH0d\"]/div[1]/div[2]/div[2]/form/content/div[4]/div[1]/label")
    static WebElement SMSPhone;

    @FindBy(how = How.XPATH, using = "//*[@id=\"view_container\"]/form/div[2]/div/div/a")
    static WebElement AccountDisable;

    @FindBy(how = How.CSS, using = "#ca")
    static WebElement Captcha;

    @FindBy(how = How.XPATH, using = "//*[@id=\"recoveryBumpPickerEntry\"]/div[2]")
    static WebElement RecoveryMailHelp;

    static int click(WebElement obj, String name1) throws InterruptedException {
        waitForElement(obj);
        if (obj.isEnabled()&&obj.isDisplayed()) {
            obj.click();
            System.out.println("Clicked on" + name1);
            return 1;
        } else {
            System.out.println(name1 + "Not found on screen");
            return 0;
        }
    }

    static int type(WebElement obj, String name2, String Data) throws InterruptedException {
        waitForElement(obj);
        if (obj.isDisplayed()&&obj.isEnabled()) {
            obj.sendKeys(Data);
            System.out.println("Input: " + Data +": to "+ name2);
            return 1;
        } else {
            System.out.println(name2 + "Not found on screen");
            return 0;
        }
    }

    static int type(WebElement obj, String name3, Keys Data) throws InterruptedException {
        waitForElement(obj);
        if (obj.isDisplayed()&&obj.isEnabled()) {
            obj.sendKeys(Data);
            System.out.println("Input: " + Data +": to "+ name3);
            return 1;
        } else {
            System.out.println(name3 + "Not found on screen");
            return 0;
        }
    }


    static int waitForElement(WebElement obj) throws InterruptedException {
        int counter = 0;
        while(counter!=5) {
            try {
                if (obj.isDisplayed()&&obj.isEnabled()) {
                    System.out.println("Object is displayed");
                    return 1;
                }
            } catch (Exception e) {
                counter++;
                try {
                    System.out.println("Object isn't display");
                    Thread.sleep(1000L);
                } catch(InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return 0;
    }


}
