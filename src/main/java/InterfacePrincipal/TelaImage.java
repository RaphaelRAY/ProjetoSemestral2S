package InterfacePrincipal;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAO.CommandsDB;
import DTO.ConnFactory;


import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ResourceBundle;

class TelaImage extends javax.swing.JFrame{
    
    private JButton UploadButton ;
    private JButton DowloadButton;
    private JButton ViewButton ;
    private JButton BackButton ;
    private JButton DeleteButton ;

    ResourceBundle bundle;
    

    TelaImage(ResourceBundle bundle){
        super("Tela Imagem");
        this.bundle = bundle;
        initComponents();
    }

    private void initComponents(){
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        UploadButton =  new JButton(bundle.getString("telaPrincipal.upload"));
        DowloadButton = new JButton(bundle.getString("telaPrincipal.download"));
        ViewButton =    new JButton(bundle.getString("telaPrincipal.view"));
        DeleteButton =  new JButton(bundle.getString("telaPrincipal.delete"));


        //painel de botões
        JPanel panelbotao = new JPanel(new GridLayout(3,1));
        panelbotao.add(UploadButton);
        panelbotao.add(DowloadButton);
        panelbotao.add(ViewButton);
        panelbotao.add(DeleteButton);

        conn = ConnFactory.getConn();

        UploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String iAutor = JOptionPane.showInputDialog(null, bundle.getString("telaPrincipal.Autor")); //// O AUTOR SERA AQUELE QUE TIVER FEITO LOGIN
                cBD.setAutor(iAutor);
                String iName = JOptionPane.showInputDialog(null, bundle.getString("telaPrincipal.name"));
                cBD.setImagename(iName);
                cBD.uploadImg(conn, iName);
                JOptionPane.showMessageDialog(null, bundle.getString("telaPrincipal.Sucesssave"));
            }
        });

        DowloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String iName = JOptionPane.showInputDialog(null, bundle.getString("telaPrincipal.name"));
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
                String iName = JOptionPane.showInputDialog(null, bundle.getString("telaPrincipal.name"));
                cBD.setImagename(iName);
                cBD.deleteImg(conn);
                JOptionPane.showMessageDialog(null, bundle.getString("telaPrincipal.SucessDelete"));
            }
        });

        BackButton = new JButton(bundle.getString("telaPrincipal.back"));

        JPanel panelLogOut = new JPanel(new FlowLayout());
        panelLogOut.add(BackButton);

        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaPrincipal(bundle).setVisible(true);
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