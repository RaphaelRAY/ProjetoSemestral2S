package InterfacePrincipal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Interfaceentrada.TelaLogIn;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ResourceBundle;

public class TelaPrincipal extends javax.swing.JFrame{
    
    private JButton TxtButton ;
    private JButton AudioButton;
    private JButton ImageButton ;
    private JButton LogOutButton ;
    private JComboBox<String> languageComboBox;
    ResourceBundle bundle;


    public TelaPrincipal(ResourceBundle bundle){
        super(bundle.getString("telaPrincipal"));
        this.bundle = bundle;
        initComponents();
    }

    private void initComponents(){
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        TxtButton = new JButton(bundle.getString("telaPrincipal.Text"));
        TxtButton.setSize(100, 100);
        AudioButton = new JButton(bundle.getString("telaPrincipal.Audio"));
        AudioButton.setSize(100, 100);
        ImageButton = new JButton(bundle.getString("telaPrincipal.Image"));
        ImageButton.setSize(100, 100);

        //painel de botões
        JPanel panelbotao = new JPanel(new GridLayout(3,1,5,10));
        panelbotao.add(TxtButton);
        panelbotao.add(AudioButton);
        panelbotao.add(ImageButton);

        TxtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaTxt(bundle).setVisible(true);
                dispose();
            }
        });

        AudioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaAudio(bundle).setVisible(true);
                dispose();
            }
        });

        ImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaImage(bundle).setVisible(true);
                dispose();
            }
        });

        LogOutButton = new JButton(bundle.getString("telaPrincipal.Logout"));

        JPanel panelLogOut = new JPanel(new FlowLayout());
        panelLogOut.add(LogOutButton);

        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new TelaLogIn().setVisible(true);
                dispose();
            }
        });
        
        String[] languages = {"Português", "English"};
        languageComboBox = new JComboBox<>(languages);
        languageComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setLanguage((String) languageComboBox.getSelectedItem());
            }
        });

        JLabel label = new JLabel(bundle.getString("telaEntra.language") + ": ");

        JPanel panelLanguage = new JPanel(new FlowLayout( FlowLayout.RIGHT));
        panelLanguage.add(label);
        panelLanguage.add(languageComboBox);
        
        

        this.add(panelLanguage, BorderLayout.NORTH);
        this.add(panelbotao, BorderLayout.CENTER);
        this.add(panelLogOut, BorderLayout.SOUTH);
        
    }

    private void setLanguage(String language){
        if(language.equals("Português")){
            bundle = ResourceBundle.getBundle("messages", new java.util.Locale("pt", "BR"));
        }else{
            bundle = ResourceBundle.getBundle("messages", new java.util.Locale("en", "US"));
        }
        this.setTitle(bundle.getString("telaPrincipal"));
        TxtButton.setText(bundle.getString("telaPrincipal.Text"));
        AudioButton.setText(bundle.getString("telaPrincipal.Audio"));
        ImageButton.setText(bundle.getString("telaPrincipal.Image"));
        LogOutButton.setText(bundle.getString("telaPrincipal.Logout"));
    }
    
}