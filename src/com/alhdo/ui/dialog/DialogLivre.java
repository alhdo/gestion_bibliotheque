package com.alhdo.ui.dialog;

import com.alhdo.dao.DAO;
import com.alhdo.dao.DAOFactory;
import com.alhdo.entity.Livre;
import com.alhdo.ui.Info;
import com.alhdo.ui.MainWindow;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/*
 * Created by Castro Alhdo on 5/3/16.
 * File created af 2:37 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *         ||----w |
 *         ||     ||
 */
public class DialogLivre extends JDialog {

    JFormattedTextField isbn;
    JTextField titre,genre,nbrPages;

    JLabel mIsbnLabel,mTitreLabel,mGenreLabel,mNbrPagesLAbel;

    JButton mOkButton,mCancelButton;
    OnRegisterClickListener mCallback;

    public DialogLivre(JFrame parent){
        super(parent);
        this.setTitle("Ajouter un livre");
        initComponent();

        mCallback=(OnRegisterClickListener)parent;

        this.setSize(500,400);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    public DialogLivre(MainWindow parent,Livre livre){
        super(parent);
        this.setTitle("Ajouter un livre");
        initComponent(livre);

        mCallback=(OnRegisterClickListener)parent;

        this.setSize(500,400);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
    public void initComponent(Livre livre){
        Dimension d = new Dimension(300,20);
        //Init textfield
        isbn=new JFormattedTextField();
        isbn.setText(livre.getIBSN());
        isbn.setSize(d);
        isbn.setEnabled(false);
        titre = new JTextField(40);
        titre.setText(livre.getTitre());
        genre = new JTextField(40);
        genre.setText(livre.getGenre());
        nbrPages = new JTextField(40);
        nbrPages.setText(livre.getNbrPages()+"");

        //Init Label
        mIsbnLabel = new JLabel("ISBN");
        mTitreLabel = new JLabel("Titre");
        mGenreLabel  = new JLabel("Genre");
        mNbrPagesLAbel  = new JLabel("Nombre de pages");

        //init button
        mOkButton = new JButton("OK");

        mOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isbn.getText().equals("") && !titre.getText().equals("") && !genre.getText().equals("") && !nbrPages.getText().equals("")){
                    Livre l=new Livre(isbn.getText(),titre.getText(),genre.getText(),Integer.parseInt(nbrPages.getText()));
                    DAO livreDao= DAOFactory.getLivreDAO();
                    livreDao.update(l);
                    mCallback.onRegisterClickListener(l);
                    dispose();
                }else{
                    Info.showErrorInput();
                }

            }
        });
        mCancelButton = new JButton("Cancel");

        mCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Adding component

        JPanel mainPan=new JPanel();
        mainPan.setBorder(new TitledBorder(new EtchedBorder(),"Livre Details"));
        mainPan.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPan.setBackground(Color.white);

        mainPan.add(mIsbnLabel);

        try {
            MaskFormatter formatter = new MaskFormatter("###-#-###-#####-#");
            formatter.setPlaceholderCharacter('_');
            isbn = new JFormattedTextField(formatter);
            isbn.setColumns(40);
            isbn.setText(livre.getIBSN());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mainPan.add(isbn);

        mainPan.add(mTitreLabel);
        mainPan.add(titre);

        mainPan.add(mGenreLabel);
        mainPan.add(genre);

        mainPan.add(mNbrPagesLAbel);
        mainPan.add(nbrPages);

        JPanel butonPane = new JPanel();

        butonPane.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        butonPane.add(mOkButton);
        butonPane.add(mCancelButton);

        this.getContentPane().add(mainPan,BorderLayout.CENTER);
        this.getContentPane().add(butonPane,BorderLayout.SOUTH);

    }
    public void initComponent(){
        Dimension d = new Dimension(300,20);
        //Init textfield
        isbn=new JFormattedTextField();
        isbn.setSize(d);
        titre = new JTextField(40);
        genre = new JTextField(40);
        nbrPages = new JTextField(40);

        //Init Label
        mIsbnLabel = new JLabel("ISBN");
        mTitreLabel = new JLabel("Titre");
        mGenreLabel  = new JLabel("Genre");
        mNbrPagesLAbel  = new JLabel("Nombre de pages");

        //init button
        mOkButton = new JButton("OK");

        mOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isbn.getText().equals("") && !titre.getText().equals("") && !genre.getText().equals("") && !nbrPages.getText().equals("")){
                    Livre l=new Livre(isbn.getText(),titre.getText(),genre.getText(),Integer.parseInt(nbrPages.getText()));
                    DAO livreDao= DAOFactory.getLivreDAO();
                    livreDao.create(l);
                    mCallback.onRegisterClickListener(l);
                    dispose();
                }else{
                    Info.showErrorInput();
                }

            }
        });
        mCancelButton = new JButton("Cancel");

        mCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Adding component

        JPanel mainPan=new JPanel();
        mainPan.setBorder(new TitledBorder(new EtchedBorder(),"Livre Details"));
        mainPan.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainPan.setBackground(Color.white);

        mainPan.add(mIsbnLabel);

        try {
            MaskFormatter formatter = new MaskFormatter("###-#-###-#####-#");
            formatter.setPlaceholderCharacter('_');
            isbn = new JFormattedTextField(formatter);
            isbn.setColumns(40);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mainPan.add(isbn);

        mainPan.add(mTitreLabel);
        mainPan.add(titre);

        mainPan.add(mGenreLabel);
        mainPan.add(genre);

        mainPan.add(mNbrPagesLAbel);
        mainPan.add(nbrPages);

        JPanel butonPane = new JPanel();

        butonPane.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        butonPane.add(mOkButton);
        butonPane.add(mCancelButton);

        this.getContentPane().add(mainPan,BorderLayout.CENTER);
        this.getContentPane().add(butonPane,BorderLayout.SOUTH);

    }

    public interface OnRegisterClickListener{
        public void onRegisterClickListener(Livre livre);
    }
}
