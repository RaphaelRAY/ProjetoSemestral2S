package main;

import java.util.Locale;
import java.util.ResourceBundle;

import InterfacePrincipal.TelaPrincipal;
import Interfaceentrada.TelaLogIn;

public class Main {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        //new TelaPrincipal(ResourceBundle.getBundle("messages", new Locale("pt", "BR"))).setVisible(true);;
        new TelaLogIn().setVisible(true);;
    }
}