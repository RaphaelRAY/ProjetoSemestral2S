package Interfaceentrada;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaSingUP extends javax.swing.JFrame{
    private JButton botaoVoltar = new JButton();
    private JButton botaoSingUp = new JButton();
    private JLabel  label = new JLabel() ;
    private JLabel  label2 = new JLabel();
    private JLabel  label3 = new JLabel();
    private JLabel  label4 = new JLabel();
    private JTextField text = new JTextField();
    private JTextField text1 = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField passwordField2 = new JPasswordField();;
    //ver caminho dinamico da imagem
    private ImageIcon icon = new ImageIcon("icone.png");
    private ImageIcon imageIcon = new ImageIcon("image.png");
    private JLabel imageLabel; 

    private ResourceBundle bundle;
    private Usuario usuario;

    public TelaSingUP(String username,ResourceBundle bundle) {
        super(bundle.getString("telaEntra.signin"));
        this.bundle = bundle;
        text.setText(username);
        
        initComponents();
    }

    private void initComponents() {
        att_componte();
        //configuração da tela
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //painel de botões
        JPanel panelbotao = new JPanel(new FlowLayout());
        panelbotao.add(botaoVoltar);
        panelbotao.add(botaoSingUp);
        this.add(panelbotao);

        //painel singUP
        JPanel panelSingUp = new JPanel();
        panelSingUp.setLayout(new GridLayout(4, 1));


        //painel de user
        JPanel panelUser = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
        flowLayout.setAlignOnBaseline(true);
        panelUser.setLayout(flowLayout);
        panelUser.add(label);
        text.setColumns(10);
        panelUser.add(text);

        //painel de email
        JPanel panelEmail = new JPanel();
        panelEmail.setLayout(flowLayout);
        panelEmail.add(label4);
        text1.setColumns(15);
        panelEmail.add(text1);


        //painel de senha
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(flowLayout);
        panelPassword.add(label2);
        passwordField.setColumns(10);
        panelPassword.add(passwordField);

        //painel de confirmar senha
        JPanel panelPassword2 = new JPanel();
        panelPassword2.setLayout(flowLayout);
        panelPassword2.add(label3);
        passwordField2.setColumns(10);
        panelPassword2.add(passwordField2);

        panelSingUp.add(panelUser);
        panelSingUp.add(panelEmail);
        panelSingUp.add(panelPassword);
        panelSingUp.add(panelPassword2);
        

        //adicionando eventos
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelaLogIn tela = new TelaLogIn(bundle);
                tela.setVisible(true);
                dispose();
            }
        });

        botaoSingUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //verificar se as senhas são iguais
                //se sim, criar o usuario
                //se não, mostrar mensagem de erro.
                usuario = new Usuario(text.getText(), passwordField.getPassword(), text1.getText());
                if (usuario.verificar_usuario_novo()) {
                    if (usuario.verficar_email_novo()) {
                        if (usuario.verificar_senhas(passwordField2.getPassword())) {
                            //criar o usuario
                            //usuario.salvar_usuario();;
                            //mostrar mensagem de sucesso com JOptionPane
                            JOptionPane.showMessageDialog(null, bundle.getString("telaEntra.SucessSing"), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            //voltar para tela de login
                            TelaLogIn tela = new TelaLogIn(bundle);
                            tela.setVisible(true);
                            dispose();
                        }else{
                            //mostrar mensagem de erro com JOptionPane
                            JOptionPane.showMessageDialog(null, bundle.getString("telaEntra.ErroPassSing"), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, bundle.getString("telaEntra.ErroEmail"), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                    //mostrar mensagem de erro com JOptionPane
                    JOptionPane.showMessageDialog(null, bundle.getString("telaEntra.ErroUserArledy"), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    

        //adicionando os paineis no principal
        this.add(panelbotao, BorderLayout.SOUTH);
        this.add(panelSingUp, BorderLayout.CENTER);


        //imagem
        this.setIconImage(icon.getImage());

        configScaleImage();
        imageLabel = new JLabel(imageIcon);
        this.add(imageLabel, BorderLayout.NORTH);
    }

    private void configScaleImage(){
        java.awt.Image img = imageIcon.getImage();
        java.awt.Image newimg = img.getScaledInstance(100, 54,  java.awt.Image.SCALE_SMOOTH);
        this.imageIcon =  new ImageIcon(newimg);
    }
    
    private void att_componte(){
        botaoVoltar.setText(bundle.getString("telaEntra.return"));
        botaoSingUp.setText(bundle.getString("telaEntra.signin"));
        label.setText(bundle.getString("telaEntra.username"));
        label2.setText(bundle.getString("telaEntra.password2"));
        label3.setText(bundle.getString("telaEntra.cornfirm"));
        label4.setText(bundle.getString("telaEntra.email"));
    }

}
