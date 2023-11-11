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

class TelaPrincipal extends javax.swing.JFrame{
    
    private JButton TxtButton ;
    private JButton AudioButton;
    private JButton ImageButton ;
    private JButton LogOutButton ;
    

    public static void main(String[] args){
        TelaTxt tela = new TelaTxt();
        tela.setVisible(true);
    }

    TelaPrincipal(){
        super("Tela Principal");
        initComponents();
    }

    private void initComponents(){
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        TxtButton = new JButton("Text");
        AudioButton = new JButton("Audio");
        ImageButton = new JButton("Image");

        //painel de botões
        JPanel panelbotao = new JPanel(new GridLayout(3,1));
        panelbotao.add(TxtButton);
        panelbotao.add(AudioButton);
        panelbotao.add(ImageButton);

        TxtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaTxt().setVisible(true);
                dispose();
            }
        });

        AudioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaAudio().setVisible(true);
                dispose();
            }
        });

        ImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaImage().setVisible(true);
                dispose();
            }
        });

        LogOutButton = new JButton("LogOut");

        JPanel panelLogOut = new JPanel(new FlowLayout());
        panelLogOut.add(LogOutButton);

        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaLogIn().setVisible(true);
                dispose();
            }
        });

        this.add(panelbotao, BorderLayout.CENTER);
        this.add(panelLogOut, BorderLayout.SOUTH);
        
    }
    
}