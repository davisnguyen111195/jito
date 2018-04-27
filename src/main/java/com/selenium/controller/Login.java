package com.selenium.controller;

import java.io.*;




public class Login {
    static String line = null;
    static Integer i = 1;

    public static void main(String[] args) throws InterruptedException {
        try {
            //Windows: "C:\\Users\\" + AutoG.userPC + "\\Documents\\GmailAutoSend\\200mail.csv"
            //Linux: "/home/"+ AutoG.userPC +"/Documents/GmailAutoSend/200mail.csv"
            FileReader fileReader = new FileReader("C:\\Users\\" + AutoG.userPC + "\\Documents\\GmailAutoSend\\200mail.csv");
            FileWriter fileWriter = new FileWriter("C:\\Users\\" + AutoG.userPC + "\\Documents\\GmailAutoSend\\success.txt");
            FileWriter fileWriter1 = new FileWriter("C:\\Users\\" + AutoG.userPC + "\\Documents\\GmailAutoSend\\fail.txt");
            BufferedWriter bw1 = new BufferedWriter(fileWriter1);
            BufferedReader br = new BufferedReader(fileReader);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            line = br.readLine();
            while(line != null) {
                String[] dataRow = line.split(",");
                Boolean check = AutoG.AutoLogin(dataRow[0], dataRow[1], dataRow[2], i);
                if(check) {
                    bw.write(line);
                    bw.write("\r\n");
                    bw.flush();
                } else {
                    bw.write(line + AutoG.status);
                    bw.write("\r\n");
                    bw1.flush();
                }
                i++;
                line = br.readLine();
                System.out.println(check.toString());
                Thread.sleep(20000);
            }
            br.close();
            bw.close();
            bw1.close();
            fileWriter1.close();
            fileReader.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
