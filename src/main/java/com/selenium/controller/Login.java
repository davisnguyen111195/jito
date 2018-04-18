package com.selenium.controller;


import org.openqa.selenium.WebDriver;


import java.io.*;




public class Login {
    static String line = null;
    static Integer i = 1;

    public static void main(String[] args) throws InterruptedException {
        try {
            FileReader fileReader = new FileReader("/home/"+ AutoG.userPC +"/Documents/GmailAutoSend/200mail.csv");
            FileWriter fileWriter = new FileWriter("/home/"+ AutoG.userPC +"/Documents/GmailAutoSend/success.txt");
            BufferedReader br = new BufferedReader(fileReader);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            line = br.readLine();
            while(line != null) {
                String[] dataRow = line.split(",");
                Boolean check = AutoG.AutoLogin(dataRow[0],dataRow[1],dataRow[2],i);
                if(check) {
                    bw.write(line);
                    bw.write("\n");
                    bw.flush();
                }
                i++;
                line = br.readLine();
                System.out.println(check.toString());
                Thread.sleep(5000);
            }
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
