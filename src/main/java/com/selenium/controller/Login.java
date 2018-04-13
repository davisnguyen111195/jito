package com.selenium.controller;

import static com.selenium.controller.LoginMail.CheckAcc;


public class Login {
    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println(CheckAcc("Letuanhung0752", "fw345fv43", "Lakimviet07426@gmail.com", 1).toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
