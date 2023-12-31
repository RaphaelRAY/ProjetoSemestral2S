package Interfaceentrada;

import java.sql.Connection;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.CommandsDB;
import DTO.ConnFactory;


public class Usuario {
    private String username;
    private char[]  password;
    private String email;
    private CommandsDB Db = new CommandsDB();
    Connection conn = ConnFactory.getConn();

    public Usuario(String username, char[] cs, String email){

        this.username = username;
        this.password = cs;
        this.email = email;
    }

    public Usuario(String email, char[] cs){
        this.email = email;
        this.password = cs;
    }

    public Usuario(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public char[] getPassword(){
        return this.password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(char[]  password){
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean verificar_senhas(char [] password1){
        return Arrays.equals(password, password1) && password.length > 0;
    }

    public boolean verificar_usuario(){
        //verificar se o usuario existe
        return true;
    }


    public boolean verificar_senha(){
        //verificar se a senha bate com banco de dados
        System.out.println("Senhas");
        System.out.println(CommandsDB.passVerify(conn, email) + "=" + String.valueOf(password) );
        System.out.println(CommandsDB.passVerify(conn, email).equals(String.valueOf(password)));
        return CommandsDB.passVerify(conn, email).equals(String.valueOf(password));
    }
    
    public void salvar_usuario(){
        //salvar o usuario no banco de dados
        Db.setUsername(username);
        Db.setEmail(email);
        Db.setPassword(String.valueOf(password));  
        Db.insertUser(conn); 
    }

    public boolean verficar_email(){
        //verificar se o email existe no banco de dados
        String mail = CommandsDB.emailVerify(conn, email);
        return (mail != null);
    }

    public boolean verficar_email_novo(){
        //verificar se o email é valido 
        if (isValidEmail()) {
            //se não existe no banco de dados
            return !verficar_email();
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
    

}
