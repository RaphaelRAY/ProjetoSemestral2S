package Interfaceentrada;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ClienteServidor.Cliente;

import InterfacePrincipal.TelaPrincipal;

import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class TelaLogIn extends javax.swing.JFrame{
    private JButton botaoLogIn = new JButton();;
    private JButton botaoSingUp = new JButton();;
    private JLabel  label = new JLabel();
    private JLabel  label2 =new JLabel();
    private JTextField text = new JTextField();
    private JPasswordField text2 = new JPasswordField();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu languageMenu = new JMenu();
    private JMenuItem englishMenuItem = new JMenuItem();
    private JMenuItem portugueseMenuItem = new JMenuItem();
    //ver caminho dinamico da imagem
    private ImageIcon icon = new ImageIcon("icone.png");
    private ImageIcon imageIcon = new ImageIcon("image.png");
    private JLabel imageLabel = new JLabel();


    // bundle deflaut = en_US
    // ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale("pt", "BR"));
    ResourceBundle bundle ;

    Usuario usuario;


    public TelaLogIn() {
        super("Log In");
        bundle = ResourceBundle.getBundle("messages", Locale.US);
        initComponents();
    }
    
    public TelaLogIn(ResourceBundle bundle) {
        super("Log In");
        this.bundle = bundle;
        
        initComponents();
    }

    private void initComponents() {
        att_componte();
        
        //configuração da tela
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 225);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //painel de botões
        JPanel panelbotao = new JPanel(new BorderLayout());
        panelbotao.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelbotao.add(botaoLogIn);
        panelbotao.add(botaoSingUp);
        
        //painel de usuario e senha
        JPanel panelLogIn = new JPanel();
        panelLogIn.setLayout(new GridLayout(2,1));
        
        //painel de email
        JPanel panelUser = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        flowLayout.setAlignOnBaseline(true);
        panelUser.setLayout(flowLayout);
        panelUser.add(label);
        text.setColumns(15);
        panelUser.add(text);

        //painel de senha
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelPassword.add(label2);
        text2.setColumns(10);
        panelPassword.add(text2);

        panelLogIn.add(panelUser);
        panelLogIn.add(panelPassword);

        //configuração da imagem
        configScaleImage();

        //configuração do menu
        getComponentOrientation();
        menuBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


        //adicionando os eventos
        englishMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setLanguage("English");
            }
        });

        portugueseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setLanguage("Portuguese");
            }
        });

        botaoLogIn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuario = new Usuario(text.getText(), text2.getPassword());
                if (usuario.verificar_senha()) {
                    //abrir tela de usuario
                    JOptionPane.showMessageDialog(null, "Aprovado", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                     try {
                        Cliente cl = new Cliente();
                        cl.mandar_recorrencia(usuario.getEmail());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    new TelaPrincipal(bundle).setVisible(true);
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null, bundle.getString("telaEntra.Errologin"), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botaoSingUp.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelaSingUP tela = new TelaSingUP(text.getText(),bundle);
                tela.setVisible(true);
                dispose();
            }
        });


        //adicionando os itens no menu
        languageMenu.add(englishMenuItem);
        languageMenu.add(portugueseMenuItem);
        menuBar.add(languageMenu);
        this.imageLabel = new JLabel(imageIcon);

        //adicionando o menuBar
        this.setJMenuBar(menuBar);

        //adicionando os paineis no principal
        this.add(panelLogIn, BorderLayout.CENTER);
        this.add(panelbotao, BorderLayout.SOUTH);
        this.add(imageLabel, BorderLayout.NORTH);
        this.setIconImage(icon.getImage());
    }

    private void configScaleImage(){
        java.awt.Image img = imageIcon.getImage();
        java.awt.Image newimg = img.getScaledInstance(250, 75,  java.awt.Image.SCALE_SMOOTH);
        this.imageIcon =  new ImageIcon(newimg);
    }

    private void setLanguage(String language){
        if(language.equals("English")){
            bundle = ResourceBundle.getBundle("messages", Locale.US);
        }else if(language.equals("Portuguese")){
            bundle = ResourceBundle.getBundle("messages", new Locale("pt", "BR"));
        }
        att_componte();
    }

    private void att_componte(){
        botaoLogIn.setText(bundle.getString("telaEntra.login"));
        botaoSingUp.setText(bundle.getString("telaEntra.signin"));
        label.setText(bundle.getString("telaEntra.email"));
        label2.setText(bundle.getString("telaEntra.password"));
        languageMenu.setText(bundle.getString("telaEntra.language"));
        englishMenuItem.setText(bundle.getString("telaEntra.english"));
        portugueseMenuItem.setText(bundle.getString("telaEntra.portuguese"));
    }
}
