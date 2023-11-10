package DAO;

import DTO.*;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    check = res.getString("email");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar os dados " + e.toString());
            throw new RuntimeException(e);
        }
        return check;
    }
    
    /// validar senha
    public static String passVerify(Connection conn, String uEmail){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String check = null;
        try {
            String sqlSelect = "SELECT password FROM USER_LOG WHERE email = ?";
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, uEmail);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    check = res.getString("password");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar os dados " + e.toString());
            throw new RuntimeException(e);
        }
        return check;
    }
    
    /// inserir audio 
    public static void uploadAudio(Connection conn, String audioname) throws IOException {
        FileInputStream stream = null;
        try {
            JFileChooser fc = new JFileChooser();
            int res = fc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File arq = fc.getSelectedFile();
                //String audioname = arq.getName();
                stream = new FileInputStream(arq);
                String sqlInsert = "INSERT INTO audio_files (audioname, audiofile) VALUES (?,?)";
                PreparedStatement stmt = conn.prepareStatement(sqlInsert);
                stmt.setString(1, audioname);
                stmt.setBinaryStream(2, stream, (int) arq.length());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    /// tocar audio
    public static void playAudio(Connection conn, String audioname) {
        try {
            String sqlSelect = "SELECT audiofile FROM audio_files WHERE audioname = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, audioname);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                byte[] audioBytes = rs.getBytes("audiofile");
                InputStream stream = new ByteArrayInputStream(audioBytes);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(stream);
                AudioFormat format = audioInputStream.getFormat();
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    }
                });
                clip.start();
                Thread.sleep(clip.getMicrosecondLength()/1000);
                clip.close();
            } else {
                JOptionPane.showMessageDialog(null, "Audio nao encontrado!");
            }
        } catch (Exception e) {
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
    
    /// table de texto
    public ArrayList<GeneralData> listAud(){
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<GeneralData> lista = new ArrayList<>();
        String sqlSelect = "SELECT * FROM audio_files";
        conn = new ConnFactory().getConn();
        try {
         ps = conn.prepareStatement(sqlSelect);
         rs = ps.executeQuery();
         while(rs.next()){
             GeneralData gd = new GeneralData();
             gd.setAudioname(rs.getString("audioname"));
             gd.setAutor(rs.getString("audioautor"));
             lista.add(gd);
         }
        } catch (SQLException e) {
            e.printStackTrace();
        }return lista;
    } 
    
    /// inserir texto
    public void uploadTxt(Connection connection) {
        JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(new JFrame());

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fc.getSelectedFile().getAbsolutePath();
                BufferedReader reader = new BufferedReader(new FileReader(path));
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null) {
                    conteudo.append(linha).append("\n");
                }
                String sqlInsert = "INSERT INTO text_files (textname, textfile) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, getTextname());
                preparedStatement.setString(2, conteudo.toString());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Arquivo de texto inserido com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao adicionar dados " + e.toString());
                e.printStackTrace();
            }
        }
    }
    
    /// excluir texto
    public void deleteTxt(Connection conn){
        String sqlDelete = "DELETE FROM text_files WHERE textname = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, getTextname());
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
    
    /// carregar texto
    public void loadTxt(Connection conn) {
        try {
            String sqlSelect = "SELECT textfile FROM text_files WHERE textname = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, getTextname());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String conteudo = rs.getString("textfile");

                String ntemp = "openfile.txt";
                File temp = new File(ntemp);
                FileWriter writer = new FileWriter(temp);
                writer.write(conteudo);
                writer.close();

                Desktop.getDesktop().open(temp);
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo nao encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /// table de texto
    public ArrayList<GeneralData> listTxt(){
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<GeneralData> lista = new ArrayList<>();
        String sqlSelect = "SELECT * FROM text_files";
        conn = new ConnFactory().getConn();
        try {
         ps = conn.prepareStatement(sqlSelect);
         rs = ps.executeQuery();
         while(rs.next()){
             GeneralData gd = new GeneralData();
             gd.setTextname(rs.getString("textname"));
             gd.setAutor(rs.getString("textautor"));
             lista.add(gd);
         }
        } catch (SQLException e) {
            e.printStackTrace();
        }return lista;
    } 

    /// inserir imagem
    public void uploadImg(Connection conn, String imagename) {
        try {
            JFileChooser fc = new JFileChooser();
            int res = fc.showOpenDialog(new JFrame());

            if (res == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();

                File imageFile = new File(imagePath);
                FileInputStream inputStream = new FileInputStream(imageFile);

                String insertSQL = "INSERT INTO image_files (imagename, imagefile, imageautor) VALUES (?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(insertSQL);
                stmt.setString(1, imagename);
                stmt.setBinaryStream(2, inputStream, (int) imageFile.length());
                stmt.setString(3, getAutor());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Imagem inserida com sucesso!");
                } else {
                    System.out.println("Falha ao inserir imagem!");
                }
                stmt.close();
                inputStream.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /// excluir imagem
    public void deleteImg(Connection conn){
        String sqlDelete = "DELETE FROM image_files WHERE imagename = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, getImagename());
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

    /// carregar imagem
    public void loadImg(Connection conn, String imagename) {
        try {
            String selectSQL = "SELECT imagefile FROM image_files WHERE imagename = ?";
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            stmt.setString(1, imagename);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("imagefile");
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
                JFrame frame = new JFrame(imagename);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel label = new JLabel(new ImageIcon(image));
                frame.add(label);
                frame.pack();
                frame.setVisible(true);
            } else {
                System.out.println("Imagem nao encontrada!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /// table de imagens
    public ArrayList<GeneralData> listImg(){
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<GeneralData> lista = new ArrayList<>();
        String sql = "SELECT * FROM image_files";
        
        conn = new ConnFactory().getConn();
        
        try {
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()){
             GeneralData gd = new GeneralData();
             gd.setImagename(rs.getString("imagename"));
             gd.setAutor(rs.getString("imageautor"));
             
             lista.add(gd);
         }
         
        } catch (SQLException e) {
            e.printStackTrace();
        }return lista;
    } 
    
    
}