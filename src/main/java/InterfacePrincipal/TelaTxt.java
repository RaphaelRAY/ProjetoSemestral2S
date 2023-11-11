package InterfacePrincipal;

import javax.swing.JButton;
import javax.swing.JPanel;

import Interfaceentrada.TelaLogIn;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

class TelaTxt extends javax.swing.JFrame{
    
    private JButton UploadButton ;
    private JButton DowloadButton;
    private JButton ViewButton ;
    private JButton BackButton ;
    private JButton DeleteButton ;
    

    public static void main(String[] args){
        new TelaTxt().setVisible(true);
    }

    TelaTxt(){
        super("Tela de Texto");
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
                
            }
        });

        DowloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        ViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
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
    
}