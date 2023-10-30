package main;

import Interfaceentrada.TelaLogIn;

public class Main {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        new TelaLogIn().setVisible(true);;
    }
}