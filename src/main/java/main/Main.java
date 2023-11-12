package main;

import java.util.ResourceBundle;

import InterfacePrincipal.TelaPrincipal;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        new TelaPrincipal(ResourceBundle.getBundle("messages", new Locale("pt", "BR"))).setVisible(true);
        //new TelaLogIn().setVisible(true);;
    }
}