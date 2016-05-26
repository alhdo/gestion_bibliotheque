package com.alhdo.ui.dialog;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Created by Castro Alhdo on 5/4/16.
 * File created af 12:51 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class DialogPreference  extends JDialog{
    JTabbedPane onglet;
    JPanel general,database;

    JButton okButton,cancelButton,mConnectButton;

    JPanel mainPan;

    JLabel language,mAdressLabel,mPortLabel,mUserLabel,mDatabaseLabel;
    JComboBox languageCB;

    JCheckBox lo= new JCheckBox("Afficher les logs");
    JCheckBox ex = new JCheckBox("Demander avant de sortir");

    JFormattedTextField mAdressDB,mPort,mUser,mDatabase;


    public DialogPreference(JFrame parent){
        super(parent);

        initComponent();
        this.setTitle("Parametres de l'application");
        this.setSize(600,450);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);


    }
    public void initComponent(){
        mainPan = new JPanel();
        onglet = new JTabbedPane(JTabbedPane.TOP);

        general = new JPanel();
        database = new JPanel();

        mConnectButton = new JButton("Connect");

        mAdressDB      = new JFormattedTextField("127.0.0.1");
                        mAdressDB.setColumns(50);
        mPort          = new JFormattedTextField("3306");
                         mPort.setColumns(50);
        mUser          = new JFormattedTextField("root");
                            mUser.setColumns(50);
        mDatabase      = new JFormattedTextField();
                            mDatabase.setColumns(50);

        mAdressLabel   = new JLabel("Adresse");
        mPortLabel     = new JLabel("Port");
        mUserLabel     = new JLabel("User");
        mDatabaseLabel = new JLabel("Database");

        database.setBorder(new TitledBorder(new EtchedBorder(),"Database Settings"));
        database.setLayout(new FlowLayout(FlowLayout.LEFT));
        database.setBackground(Color.white);

        database.add(mAdressLabel);
        database.add(mAdressDB);

        database.add(mPortLabel);
        database.add(mPort);

        database.add(mUserLabel);
        database.add(mUser);

        database.add(mDatabaseLabel);
        database.add(mDatabase);

        general.setBorder(new TitledBorder(new EtchedBorder(),"Applications Settings"));
        general.setLayout(new FlowLayout(FlowLayout.LEFT));
        general.setBackground(Color.white);

        language = new JLabel("Langue application");
        languageCB = new JComboBox();


        languageCB.addItem("English");
        languageCB.addItem("Francais");
        languageCB.setPrototypeDisplayValue("##########################################");

        general.add(language);
        general.add(languageCB);

        lo.setPreferredSize(new Dimension(450,25));
        ex.setPreferredSize(new Dimension(450,25));
        general.add(lo);
        general.add(ex);

        onglet.add("General",general);
        onglet.add("Base de Donnee",database);
        mainPan.add(onglet);


        okButton = new JButton("OK");
        cancelButton = new JButton("Annuler");

        actionButton();

        JPanel butonPane = new JPanel();

        butonPane.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        butonPane.add(okButton);
        butonPane.add(cancelButton);
        this.getContentPane().add(onglet,BorderLayout.CENTER);
        this.getContentPane().add(butonPane,BorderLayout.SOUTH);

    }

    public void actionButton(){
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
