package com.alhdo.ui.dialog;

import com.alhdo.dao.DAOFactory;
import com.alhdo.dao.EmpruntDAO;
import com.alhdo.dao.LivreDAO;
import com.alhdo.database.BiblioConnection;
import com.alhdo.entity.Emprunt;
import com.alhdo.entity.Exemplaire;
import com.alhdo.entity.Livre;
import com.alhdo.util.DateUtil;
import com.alhdo.util.Log;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Created by Castro Alhdo on 5/3/16.
 * File created af 2:39 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class DialogExemplaire extends JDialog{
    JFormattedTextField dateAchat;
    JComboBox etat;
    JTextField numero;

    JComboBox isbn;

    JLabel mNumeroLabel,mEtatLabel,mDateLabel,mISBNLAbel;

    JButton mOkButton,mCancelButton;
    OnExemplaireClickListener mCallback;
    LivreDAO list=new LivreDAO(BiblioConnection.getInstance());
    public DialogExemplaire(JFrame parent){

        super(parent);

        mCallback=(OnExemplaireClickListener) parent;
        initComponent();

        this.setTitle("Ajouter un exemplaire");
        this.setSize(500,400);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    private void buttonAction(){
        mOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        mCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void initComponent(){
        isbn      = new JComboBox();
        isbn.setPrototypeDisplayValue("##########################################");
        setComboIsbnValue();
        etat      = new JComboBox();
                    etat.addItem("Neuf");
                    etat.addItem("Use");
                    etat.addItem("Endomage");
                    etat.setPrototypeDisplayValue("##########################################");

        DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        dateAchat = new JFormattedTextField(df);
        dateAchat.setValue(new Date());
        dateAchat.setColumns(40);



        numero    = new JTextField(40);

        //init label
        mNumeroLabel = new JLabel("Numero");
        mEtatLabel   = new JLabel("Etat de L'exemplaire");
        mDateLabel   = new JLabel("Date Achat");
        mISBNLAbel   = new JLabel("ISBN");


        //init buton
        mOkButton = new JButton("OK");
        mCancelButton = new JButton("Cancel");


        buttonAction();


        JPanel mainPan = new JPanel();
        mainPan.setBorder(new TitledBorder(new EtchedBorder(),"Livre Details"));
        mainPan.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPan.setBackground(Color.white);




        mainPan.add(mNumeroLabel);
        mainPan.add(numero);

        mainPan.add(mEtatLabel);
        mainPan.add(etat);

        mainPan.add(mDateLabel);
        mainPan.add(dateAchat);

        mainPan.add(mISBNLAbel);
        mainPan.add(isbn);





        JPanel butonPane = new JPanel();

        butonPane.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        butonPane.add(mOkButton);
        mOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dateAchat!=null){
                    Exemplaire exemplaire=new Exemplaire(0,(String) etat.getSelectedItem(), DateUtil.getD(dateAchat.getText()),getISBN());
                    if(DAOFactory.getExemplaireDAO().create(exemplaire)){
                        dispose();
                    }else{
                        Log.e("Exemplaire wrong");
                    }
                }
            }
        });
        butonPane.add(mCancelButton);

        this.getContentPane().add(mainPan,BorderLayout.CENTER);
        this.getContentPane().add(butonPane,BorderLayout.SOUTH);
    }

    public interface OnExemplaireClickListener{
        public void onExemplaireClickListener(Exemplaire e);
    }
    private void setComboIsbnValue(){


        for (Livre livre : list.getList()) {
            isbn.addItem(livre.getTitre() + " - ("+ livre.getGenre()+")");
        }
    }
    private String getISBN(){
        return list.getList().get(isbn.getSelectedIndex()).getIBSN();

    }
}
