package com.alhdo.ui.dialog;

import com.alhdo.dao.DAO;
import com.alhdo.dao.DAOFactory;
import com.alhdo.entity.Adherent;
import com.alhdo.entity.Exemplaire;
import com.alhdo.ui.Info;
import com.alhdo.ui.MainWindow;
import com.alhdo.util.NameDocument;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/*
 * Created by Castro Alhdo on 5/2/16.
 * File created af 6:17 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class DialogAdherent extends JDialog{

    JLabel nomLabel,prenomLabel,codeLabel,telephoneLabel,adressLabel;
    JButton okButton,cancelButton;

    JTextField fieldNom,fieldPrenom,fieldCode,fieldTelephone,fieldAdress;
    OnAdherentAddListener mCallBack;

    public DialogAdherent(JFrame parent){
        super(parent);
        mCallBack = (OnAdherentAddListener)parent;
        initComponent();
        this.setTitle("Ajouter un adherent");
        this.setSize(500,400);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    public DialogAdherent(MainWindow parent, Adherent adherent){
        super(parent);
        mCallBack = (OnAdherentAddListener)parent;
        initModifierComponent(adherent);
        this.setTitle("Ajouter un adherent");
        this.setSize(500,400);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initComponent(){

        //init des Jlabel
        nomLabel = new JLabel("Nom     ");
        prenomLabel = new JLabel("Prenom");
        codeLabel = new JLabel("Code      ");
        telephoneLabel = new JLabel("Telephone");
        adressLabel = new JLabel("Adresse");

        //Init des JtextField
        fieldNom =new JTextField(40);
        fieldNom.setDocument(new PlainDocument());

       // fieldNom.setPreferredSize(new Dimension(450,20));
        fieldPrenom=new JTextField(40);



        fieldCode = new JTextField(40);
        fieldCode.setEnabled(false);
        try {
            MaskFormatter formatter = new MaskFormatter("###-####-####");
            formatter.setPlaceholderCharacter('-');
            fieldTelephone = new JFormattedTextField(formatter);
            fieldTelephone.setColumns(40);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        fieldAdress = new JTextField(40);

//Buton
        okButton = new JButton("OK");
        cancelButton = new JButton("Annuler");

        JPanel butonPane = new JPanel();

        butonPane.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        butonPane.add(okButton);
        butonPane.add(cancelButton);
        //Adding things
//        JPanel p1 = new JPanel();
//        p1.setLayout(new BoxLayout(p1,BoxLayout.LINE_AXIS));
//        p1.add(nomLabel);
//        p1.add(fieldNom);

//        JPanel p2 = new JPanel();
//        p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
//        p2.add(fieldNom);

        JPanel content = new JPanel();
        content.setBorder(new TitledBorder(new EtchedBorder(),"Adherent Details"));
        content.setLayout(new FlowLayout(FlowLayout.LEFT));
        content.setBackground(Color.white);

        content.add(codeLabel);
        content.add(fieldCode);

        content.add(nomLabel);
        content.add(fieldNom);

       // content.add(p1);
//        content.add(p2);

        content.add(prenomLabel);
        content.add(fieldPrenom);

        content.add(telephoneLabel);
        content.add(fieldTelephone);

        content.add(adressLabel);
        content.add(fieldAdress);

        //content.add(butonPane);


        getContentPane().add(content,BorderLayout.CENTER);
        getContentPane().add(butonPane,BorderLayout.SOUTH);
        registerAdherent();
    }

    private void initModifierComponent(Adherent adherent){

        //init des Jlabel
        nomLabel = new JLabel("Nom     ");
        prenomLabel = new JLabel("Prenom");
        codeLabel = new JLabel("Code      ");
        telephoneLabel = new JLabel("Telephone");
        adressLabel = new JLabel("Adresse");

        //Init des JtextField
        fieldNom =new JTextField(40);
        fieldNom.setText(adherent.getNom());
        //fieldNom.setDocument(new PlainDocument());
        // fieldNom.setPreferredSize(new Dimension(450,20));
        fieldPrenom=new JTextField(40);
        fieldPrenom.setText(adherent.getPrenom());



        fieldCode = new JTextField(40);
        fieldCode.setText(adherent.getCode()+"");
        fieldCode.setEnabled(false);
        try {
            MaskFormatter formatter = new MaskFormatter("###-####-####");
            formatter.setPlaceholderCharacter('-');
            fieldTelephone = new JFormattedTextField(formatter);
            fieldTelephone.setText(adherent.getTelephone());
            fieldTelephone.setColumns(40);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        fieldAdress = new JTextField(40);
        fieldAdress.setText(adherent.getAdresse());

//Buton
        okButton = new JButton("OK");
        cancelButton = new JButton("Annuler");

        JPanel butonPane = new JPanel();

        butonPane.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        butonPane.add(okButton);
        butonPane.add(cancelButton);
        //Adding things
//        JPanel p1 = new JPanel();
//        p1.setLayout(new BoxLayout(p1,BoxLayout.LINE_AXIS));
//        p1.add(nomLabel);
//        p1.add(fieldNom);

//        JPanel p2 = new JPanel();
//        p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
//        p2.add(fieldNom);

        JPanel content = new JPanel();
        content.setBorder(new TitledBorder(new EtchedBorder(),"Adherent Details"));
        content.setLayout(new FlowLayout(FlowLayout.LEFT));
        content.setBackground(Color.white);

        content.add(codeLabel);
        content.add(fieldCode);

        content.add(nomLabel);
        content.add(fieldNom);

        // content.add(p1);
//        content.add(p2);

        content.add(prenomLabel);
        content.add(fieldPrenom);

        content.add(telephoneLabel);
        content.add(fieldTelephone);

        content.add(adressLabel);
        content.add(fieldAdress);

        //content.add(butonPane);


        getContentPane().add(content,BorderLayout.CENTER);
        getContentPane().add(butonPane,BorderLayout.SOUTH);
        updateAdherent();
    }
    private boolean registerAdherent(){

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Adherent adherent=new Adherent();
                adherent.setCode(0);
                adherent.setNom(fieldNom.getText());
                adherent.setPrenom(fieldPrenom.getText());
                adherent.setTelephone(fieldTelephone.getText());
                adherent.setAdresse(fieldAdress.getText());

                if(!fieldNom.getText().equals("") &&  !fieldPrenom.getText().equals("") && !fieldTelephone.getText().equals("") && !fieldAdress.getText().equals("")){
                    DAO dao= DAOFactory.getAdherentDAO();
                    dao.create(adherent);
                    mCallBack.onAdherentAddListener(adherent);
                    dispose();

                }
                else {
                    Info.showErrorInput();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return true;
    }
    private boolean updateAdherent(){

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Adherent adherent=new Adherent();
                adherent.setCode(Integer.parseInt(fieldCode.getText()));
                adherent.setNom(fieldNom.getText());
                adherent.setPrenom(fieldPrenom.getText());
                adherent.setTelephone(fieldTelephone.getText());
                adherent.setAdresse(fieldAdress.getText());

                if(!fieldNom.getText().equals("") &&  !fieldPrenom.getText().equals("") && !fieldTelephone.getText().equals("") && !fieldAdress.getText().equals("")){
                    DAO dao= DAOFactory.getAdherentDAO();
                    dao.update(adherent);
                    mCallBack.onAdherentAddListener(adherent);
                    dispose();


                }
                else {
                    Info.showErrorInput();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return true;
    }
    public interface OnAdherentAddListener{
        public void onAdherentAddListener(Adherent adherent);

    }
}
