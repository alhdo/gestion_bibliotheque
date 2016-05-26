package com.alhdo.ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/*
 * Created by Castro Alhdo on 5/2/16.
 * File created af 3:11 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class LeftPane extends JPanel{
    JTabbedPane onglet;
    JPanel browse,search;

    JTextField searchTerm;
    JButton mSearchButton;
    JComboBox searchCB;
    private JTree arbre;
    public LeftPane(){
        onglet = new JTabbedPane(JTabbedPane.BOTTOM);
        browse=new JPanel();
        browse.setBackground(Color.black);
        search=new JPanel();
        initComponents();
        onglet.add("Parcourir",browse);
        onglet.add("Rechercher",search);


            buildTree();
        this.add(onglet);
    }
    private void initComponents(){
        search.setBorder(new TitledBorder(new EtchedBorder(),"Database Settings"));
        search.setLayout(new BoxLayout(search,BoxLayout.PAGE_AXIS));
        search.setBackground(Color.white);
       // String[] comboS={"Adherent","Livres","Exemplaire","Emprunt"};
        searchCB = new JComboBox();
        searchCB.addItem("Adherent");
        searchCB.addItem("Livres");
        searchCB.addItem("Exemplaire");
        searchCB.addItem("Emprunt");
        searchCB.setPrototypeDisplayValue("############################");
        searchTerm = new JTextField(25);

        mSearchButton = new JButton("Search");

        search.add(searchTerm);
        search.add(searchCB);
        search.add(mSearchButton);
    }
    public void buildTree(){
        //Création d'une racine

        DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Entite");



        //Nous allons ajouter des branches et des feuilles à notre racine





            DefaultMutableTreeNode node = new DefaultMutableTreeNode("Adherents");

            //On ajoute la feuille ou la branche à la racine

        racine.add(node);
        racine.add(new DefaultMutableTreeNode("Livres"));
        racine.add(new DefaultMutableTreeNode("Exemplaires"));
        racine.add(new DefaultMutableTreeNode("Emprunts"));



        //Nous créons, avec notre hiérarchie, un arbre

        arbre = new JTree(racine);
        arbre.setPreferredSize(new Dimension(300,500));


        //Que nous plaçons sur le ContentPane de notre JFrame à l'aide d'un scroll

        browse.add(new JScrollPane(arbre));
    }

}
