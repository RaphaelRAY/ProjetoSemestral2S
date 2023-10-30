/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import DTO.ConnFactory;
import DTO.GeneralData;

import java.sql.DriverManager;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class CommandsDB extends GeneralData {


    /// inserir usuario
    public void insertUser(Connection conn){
        String sqlInsert = "INSERT INTO USER_LOG(username, email, password) VALUES(?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = ConnFactory.getConn().prepareStatement(sqlInsert);
            stmt.setString(1, getUsername());
            stmt.setString(2, getEmail());
            stmt.setString(3, getPassword());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.setAutoCommit(false);
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Erro ao incluir os dados " + e1.toString());
                throw new RuntimeException(e1);
            }
        }
    }
    
    /// excluir usuario
    public void deleteUser(Connection conn){
        String sqlDelete = "DELETE FROM USER_LOG WHERE email = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, getEmail());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.setAutoCommit(false);
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Erro ao excluir os dados " + e1.toString());
                throw new RuntimeException(e1);
            }
        }
    }
    
    /// validar email
    public static String emailVerify(Connection conn, String uEmail) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String check = null;
        try {
            String sqlSelect = "SELECT email FROM USER_LOG WHERE email = ?";
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, uEmail);

            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    check = resultado.getString("email");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar os dados " + e.toString());
            throw new RuntimeException(e);
        }
        return check;
    }
    
    
    /// inserir audio (((certo)))
    public static void uploadAudio(Connection conn, String audioname) throws IOException {
        FileInputStream stream = null;
        try {
            JFileChooser fc = new JFileChooser();
            int res = fc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File arq = fc.getSelectedFile();
                //String audioname = arq.getName();
                stream = new FileInputStream(arq);
                String sql = "INSERT INTO audio_files (audioname, audiofile) VALUES (?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, audioname);
                stmt.setBinaryStream(2, stream, (int) arq.length());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Arquivo de audio inserido com sucesso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /// tocar audio
    public void playAudio(Connection conn, String audioname) {
        try {
            String sql = "SELECT audiofile FROM audio_files WHERE audioname = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, audioname);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                byte[] audioBytes = rs.getBytes("audiofile");
                try (ByteArrayInputStream audioStream = new ByteArrayInputStream(audioBytes);
                     AudioInputStream stream = AudioSystem.getAudioInputStream(audioStream)) {
                    Clip clip = AudioSystem.getClip();
                    clip.addLineListener(new LineListener() {
                        @Override
                        public void update(LineEvent event) {
                            if (event.getType() == LineEvent.Type.STOP) {
                                clip.close();
                            }
                        }
                    });
                    clip.open(stream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Audio nao encontrado no banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /// excluir audio
    public void deleteAudio(Connection conn){
        String sqlDelete = "DELETE FROM audio_files WHERE audioname = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, getAudioname());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.setAutoCommit(false);
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Erro ao excluir os dados " + e1.toString());
                throw new RuntimeException(e1);
            }
        }
    }
    
    /*
    /// inserir audio ((((DANDO ERRO))) >> melhor usar o uploadAudio()
    public void insertAudio(Connection conn){
        String sqlInsert = "INSERT INTO USER_LOG(audioname, audiofile) VALUES(?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = ConnFactory.getConn().prepareStatement(sqlInsert);
            stmt.setString(1, getAudioname());
            stmt.setString(2, getAudiofile());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.setAutoCommit(false);
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Erro ao incluir os dados " + e1.toString());
                throw new RuntimeException(e1);
            }
        }
    }*/


    
    
}
