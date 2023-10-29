package Interface;

import java.util.Arrays;

public class Usuario {
    private String username;
    private char[]  password;
    private String email;


    public Usuario(String username, char[] cs){
        this.username = username;
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
        return Arrays.equals(password, password1);
    }

    public boolean verificar_usuario(){
        //verificar se o usuario existe
        return true;
    }

    public boolean verificar_senha(){
        //verificar se a senha bate com banco de dados
        return true;
    }
    
    public void salvar_usuario(){
        //salvar o usuario no banco de dados
    }
}
