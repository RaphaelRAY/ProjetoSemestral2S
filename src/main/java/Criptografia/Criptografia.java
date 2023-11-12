package Criptografia;

import java.io.File;

public class Criptografia {
    public static String Criptografar(String texto) {
        try {
            String   sMsgClara = texto;
            String   sMsgCifrada = null;
            byte[]   bMsgClara = null;
            byte[]   bMsgCifrada = null;
            bMsgClara = sMsgClara.getBytes("ISO-8859-1");
            
            CryptoAES   caes = new CryptoAES();

            caes.geraCifra(bMsgClara, new File ("chave.simetrica"));
            bMsgCifrada = caes.getTextoCifrado();
            sMsgCifrada = (new String (bMsgCifrada, "ISO-8859-1"));
            return sMsgCifrada;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public static String Descriptografar(String texto) {
        
        try{
            String   sMsgDecifrada = null;
            byte[]   bMsgDecifrada = null;
            CryptoAES   caes = new CryptoAES();

            caes.geraDecifra(texto.getBytes("ISO-8859-1"), new File ("chave.simetrica"));
            bMsgDecifrada = caes.getTextoDecifrado();
            sMsgDecifrada = (new String (bMsgDecifrada, "ISO-8859-1"));
            return sMsgDecifrada;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
}
