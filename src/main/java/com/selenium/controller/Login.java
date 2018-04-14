package com.selenium.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.selenium.controller.LoginMail.CheckAcc;


public class Login {
    public static void main(String[] args) throws InterruptedException {
        try {
            Boolean check = CheckAcc("Hoangthihang93626", "gthihang93626pp", "Nguyenthithao9264@gmail.com", 1);
            System.out.println(check.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
