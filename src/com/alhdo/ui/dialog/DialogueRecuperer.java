package com.alhdo.ui.dialog;

import com.alhdo.dao.DAO;
import com.alhdo.dao.DAOFactory;
import com.alhdo.dao.EmpruntDAO;
import com.alhdo.dao.ExemplaireDAO;
import com.alhdo.database.BiblioConnection;
import com.alhdo.entity.Adherent;
import com.alhdo.entity.Emprunt;
import com.alhdo.entity.Exemplaire;
import com.alhdo.ui.Info;
import com.alhdo.ui.MainWindow;
import com.alhdo.util.DateUtil;
import com.alhdo.util.Log;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Castro Alhdo on 5/15/16.
 * File created af 2:44 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 * (__)\       )\/\
 * ||----w |
 * ||     ||
 */
public class DialogueRecuperer extends JDialog {
    JComboBox adherentCB,exemplaireCB;
    JFormattedTextField dateEmprunt,dateRetour;

    JLabel mAdherentLabel,mExemplaireLabel,mDateELabel,mDateRLabel;

    JButton mOkButton,mCancelButton;

    OnEmpruntDoneListener mCallBack;
    ArrayList<Adherent> adherentArrayList=new ArrayList<>();
    ArrayList<Exemplaire> exemplaireArrayList = new ArrayList<>();

    public DialogueRecuperer(MainWindow owner, Emprunt emprunt) {
        super(owner);
        initComponent(emprunt);
      // mCallBack = (OnEmpruntDoneListener)owner;
        initComponent(emprunt);
        this.setTitle("Recuperer un livre");
        this.setSize(500,400);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    public void initComponent(Emprunt emprunt){
        adherentCB       = new JComboBox();
        fillAdherentCombo(emprunt);
        exemplaireCB     = new JComboBox();
        fillExemplaireCOmbo(emprunt);

        DateFormat df=new SimpleDateFormat("dd/MM/yyyy");

        dateEmprunt      = new JFormattedTextField(df);
        dateEmprunt.setValue(emprunt.getDateEmprunt());
        dateEmprunt.setColumns(40);
        dateRetour       = new JFormattedTextField(df);
        dateRetour.setValue(emprunt.getDateRetour());
        dateRetour.setColumns(40);

        //init label
        mAdherentLabel   = new JLabel("Adherents");
        mExemplaireLabel = new JLabel("Exemplaire");
        mDateELabel      = new JLabel("Date Emprunt");
        mDateRLabel      = new JLabel("Date Retour");

        //init buton
        mOkButton = new JButton("Recuperer");
        mCancelButton = new JButton("Cancel");


        JPanel mainPan = new JPanel();
        mainPan.setBorder(new TitledBorder(new EtchedBorder(),"Recuperer un emprunt"));
        mainPan.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPan.setBackground(Color.white);

        mainPan.add(mAdherentLabel);
        mainPan.add(adherentCB);


        mainPan.add(mExemplaireLabel);
        mainPan.add(exemplaireCB);

        mainPan.add(mDateELabel);
        mainPan.add(dateEmprunt);

        mainPan.add(mDateRLabel);
        mainPan.add(dateRetour);

        JPanel butonPane = new JPanel();

        butonPane.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        butonPane.add(mOkButton);
        butonPane.add(mCancelButton);

        buttonAction(emprunt);

        this.getContentPane().add(mainPan,BorderLayout.CENTER);
        this.getContentPane().add(butonPane,BorderLayout.SOUTH);
    }

    private void fillAdherentCombo(Emprunt emprunt){
        DAO dao= DAOFactory.getAdherentDAO();
        adherentArrayList = dao.getList();

        for(int i=0;i<adherentArrayList.size();i++){
            adherentCB.addItem(adherentArrayList.get(i).getNom()+ " "+adherentArrayList.get(i).getPrenom());
            if(adherentArrayList.get(i).getCode()==emprunt.getAdherentCode()){
//                adherentCB.addItem(adherentArrayList.get(i).getNom()+ " "+adherentArrayList.get(i).getPrenom());
                adherentCB.setSelectedIndex(i);

            }
        }
        adherentCB.setPrototypeDisplayValue("##########################################");
    }
    private void fillAdherentModCombo(Emprunt emprunt){
        DAO dao= DAOFactory.getAdherentDAO();
        adherentArrayList = dao.getList();

        for(int i=0;i<adherentArrayList.size();i++){
            adherentCB.addItem(adherentArrayList.get(i).getNom()+ " "+adherentArrayList.get(i).getPrenom());
            if(adherentArrayList.get(i).getCode()==emprunt.getAdherentCode()){
//                adherentCB.addItem(adherentArrayList.get(i).getNom()+ " "+adherentArrayList.get(i).getPrenom());
                adherentCB.setSelectedIndex(i);

            }

        }
        adherentCB.setPrototypeDisplayValue("##########################################");
    }
    private void fillExemplaireCOmbo(Emprunt emprunt){
        ExemplaireDAO dao= new ExemplaireDAO(BiblioConnection.getInstance());
        exemplaireArrayList = dao.getLivreList();
        for (int i=0;i<exemplaireArrayList.size();i++){
            exemplaireCB.addItem(exemplaireArrayList.get(i).getNumero()+ " - "+exemplaireArrayList.get(i).getLivre().getTitre() +" - ("+exemplaireArrayList.get(i).getEtat()+")");

            if(adherentArrayList.get(i).getCode()==emprunt.getAdherentCode()){
//                adherentCB.addItem(adherentArrayList.get(i).getNom()+ " "+adherentArrayList.get(i).getPrenom());
                adherentCB.setSelectedIndex(i);

            }
        }
        exemplaireCB.setPrototypeDisplayValue("##########################################");
    }
    private void fillExemplaireModCombo(Emprunt emprunt){
        ExemplaireDAO dao= new ExemplaireDAO(BiblioConnection.getInstance());
        exemplaireArrayList = dao.getList();
        for (int i=0;i<exemplaireArrayList.size();i++){
            exemplaireCB.addItem(exemplaireArrayList.get(i).getNumero()+ " - "+exemplaireArrayList.get(i).getLivre().getTitre() +" - ("+exemplaireArrayList.get(i).getEtat()+")");

            if(exemplaireArrayList.get(i).getNumero()==emprunt.getExemplaireNumero()){
                exemplaireCB.setSelectedIndex(i-1);
            }
        }
        exemplaireCB.setPrototypeDisplayValue("##########################################");
    }
    private void buttonAction(Emprunt emprunt){
        mOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dateEmprunt.getText()!=null && dateEmprunt.getText().length()>9 && dateRetour.getText()!=null && dateRetour.getText().length()>9){
                    Log.d("Date after click "+dateRetour.getText());
                    if(!DateUtil.compareDate(dateEmprunt.getText(),dateRetour.getText()) ){
                        Info.dateIncoherent();
                    }else {
                        new EmpruntDAO(BiblioConnection.getInstance()).update(emprunt);
                        dispose();
                    }
                }
            }
        });

        mCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public interface OnEmpruntDoneListener{
        public void onEmpruntDoneListener(Emprunt emprunt);
    }
}
