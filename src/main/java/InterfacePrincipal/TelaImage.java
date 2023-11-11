package InterfacePrincipal;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAO.CommandsDB;
import DTO.ConnFactory;
import Interfaceentrada.TelaLogIn;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

class TelaImage extends javax.swing.JFrame{
    
    private JButton UploadButton ;
    private JButton DowloadButton;
    private JButton ViewButton ;
    private JButton BackButton ;
    private JButton DeleteButton ;
    

    public static void main(String[] args){
        TelaTxt tela = new TelaTxt();
        tela.setVisible(true);
    }

    TelaImage(){
        super("Tela Imagem");
        initComponents();
    }

    private void initComponents(){
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        UploadButton = new JButton("Upload");
        DowloadButton = new JButton("Dowload");
        ViewButton = new JButton("View");
        DeleteButton = new JButton("Delete");


        //painel de botões
        JPanel panelbotao = new JPanel(new GridLayout(3,1));
        panelbotao.add(UploadButton);
        panelbotao.add(DowloadButton);
        panelbotao.add(ViewButton);
        panelbotao.add(DeleteButton);

        conn = bd.getConn();

        UploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String iAutor = JOptionPane.showInputDialog(null, "Autor: "); //// O AUTOR SERA AQUELE QUE TIVER FEITO LOGIN
                cBD.setAutor(iAutor);
                String iName = JOptionPane.showInputDialog(null, "Salvar como: ");
                cBD.setImagename(iName);
                cBD.uploadImg(conn, iName);
                JOptionPane.showMessageDialog(null, "Imagem inserida com sucesso!");
            }
        });

        DowloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String iName = JOptionPane.showInputDialog(null, "Nome: ");
                cBD.setImagename(iName);
                cBD.loadImg(conn, iName);
            }
        });

        ViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String iName = JOptionPane.showInputDialog(null, "Nome: ");
                cBD.setImagename(iName);
                cBD.deleteImg(conn);
                JOptionPane.showMessageDialog(null, "Imagem excluida com sucesso!");
            }
        });

        BackButton = new JButton("Back");

        JPanel panelLogOut = new JPanel(new FlowLayout());
        panelLogOut.add(BackButton);

        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaPrincipal().setVisible(true);
                dispose();
            }
        });

        this.add(panelbotao, BorderLayout.CENTER);
        this.add(panelLogOut, BorderLayout.SOUTH);
        
    }

    CommandsDB cBD = new CommandsDB();
    Connection conn = null;
    ConnFactory bd = new ConnFactory();
    
}