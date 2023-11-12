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

class TelaTxt extends javax.swing.JFrame{
    
    private JButton UploadButton ;
    private JButton DowloadButton;
    private JButton ViewButton ;
    private JButton BackButton ;
    private JButton DeleteButton ;

    ResourceBundle bundle;
    

    TelaTxt(ResourceBundle bundle){
        super("Tela de Texto");
        this.bundle = bundle;
        initComponents();
    }

    private void initComponents(){
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        UploadButton = new JButton(bundle.getString("telaPrincipal.upload"));     
        DowloadButton =  new JButton(bundle.getString("telaPrincipal.download"));    
        ViewButton =   new JButton(bundle.getString("telaPrincipal.view"));
        DeleteButton =  new JButton(bundle.getString("telaPrincipal.delete"));


        //painel de botões
        JPanel panelbotao = new JPanel(new GridLayout(3,1,5,10));
        panelbotao.add(UploadButton);
        panelbotao.add(DowloadButton);
        panelbotao.add(ViewButton);
        panelbotao.add(DeleteButton);

        UploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPLOADTXT();
            }
        });

        DowloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOADTXT();
            }
        });

        ViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETETXT();
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
    ConnFactory bd = new ConnFactory();
    Connection conn = ConnFactory.getConn();
    String tName;
    
    public void UPLOADTXT(){
        tName = JOptionPane.showInputDialog(null, bundle.getString("telaPrincipal.name"));
        cBD.setTextname(tName);
        cBD.uploadTxt(conn);
    }
    
    public void DELETETXT(){
        tName = JOptionPane.showInputDialog(null, bundle.getString("telaPrincipal.name"));
        cBD.setTextname(tName);
        cBD.deleteTxt(conn);
        JOptionPane.showMessageDialog(null, "telaPrincipal.SucessDelete");
    }
    
    public void LOADTXT(){
        tName = JOptionPane.showInputDialog(null, bundle.getString(("telaPrincipal.name")));
        cBD.setTextname(tName);
        cBD.loadTxt(conn);
    }
    
}