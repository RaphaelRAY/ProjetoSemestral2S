/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Chambs
 */
public class GeneralData {
    private String username;
    private String email;
    private String password;
    
    private String audioname;
    private String audiofile;
    
    public GeneralData(){
    }

    public GeneralData(String username, char[] cs, String email){
        this.username = username;
        this.password = cs.toString();
        this.email = email;
    }

    public GeneralData(String email, char[] cs){
        this.password = cs.toString();
        this.email = email;
    }
    
    public GeneralData(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public GeneralData(String audioname, String audiofile){
        this.audioname = audioname;
        this.audiofile = audiofile;
    }
    
   
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAudioname() {
        return audioname;
    }
    public void setAudioname(String audioname) {
        this.audioname = audioname;
    }

    public String getAudiofile() {
        return audiofile;
    }
    public void setAudiofile(String audiofile) {
        this.audiofile = audiofile;
    }

    public boolean verificar_usuario_novo(){
        //verificar se o usuario é valido
        if (username.length() > 0) {
            //se não existe no banco de dados
            return !true;
        }else {
            return false;
        }
            
    }

    public boolean isValidEmail() {
        if (!(email == null)) {
            // Define a expressão regular para validar endereços de e-mail
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

            // Compila a expressão regular em um objeto Pattern
            Pattern pattern = Pattern.compile(regex);

            // Cria um objeto Matcher com o endereço de e-mail
            Matcher matcher = pattern.matcher(this.email);

            // Verifica se o endereço de e-mail corresponde à expressão regular
            return matcher.matches();
        } else {
            return false;
        }
    }   
    
    
    @Override
    public String toString() {
        return ">> Data: \nUser = " + getUsername() + " \nEmail = " + getEmail() + " \nSenha = " + getPassword();
    }
    
}
