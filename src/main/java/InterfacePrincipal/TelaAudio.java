package InterfacePrincipal;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAO.CommandsDB;
import DTO.ConnFactory;


import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.Connection;


class TelaAudio extends javax.swing.JFrame{
    
    private JButton UploadButton ;
    private JButton DowloadButton;
    private JButton ViewButton ;
    private JButton BackButton ;
    private JButton DeleteButton ;
    

    public static void main(String[] args){
        new TelaAudio().setVisible(true);
    }

    TelaAudio(){
        super("Tela Audio");
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

        UploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            try {
                UPLOAD();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fazer upload do arquivo");
            }
            }
        });

        DowloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PLAYER();
            }
        });

        ViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETE();
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
    Connection conn = ConnFactory.getConn();
    ConnFactory bd = new ConnFactory();
    String aName;
    
    public void UPLOAD() throws IOException{

        System.out.println(">> Upload audio)");
        aName = JOptionPane.showInputDialog(null, "Salvar arquivo como: ");
        cBD.setAudioname(aName);
        CommandsDB.uploadAudio(conn, aName);
        JOptionPane.showMessageDialog(null, "Arquivo de audio inserido com sucesso");
    }
    
    public void PLAYER(){

        System.out.println(">> Play audio");
        aName = JOptionPane.showInputDialog(null, "Nome: ");
        cBD.setAudioname(aName);
        CommandsDB.playAudio(conn, aName);
    }
    
    public void DELETE(){
        System.out.println(">> Delete audio");
        aName = JOptionPane.showInputDialog(null, "Nome do arquivo a ser deletado: ");
        cBD.setAudioname(aName);
        cBD.deleteAudio(conn);
        JOptionPane.showMessageDialog(null, "Arquivo deletado com sucesso!");
        
    }
    
}