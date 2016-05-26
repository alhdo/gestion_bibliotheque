package com.alhdo.ui;

import com.alhdo.entity.Adherent;
import com.alhdo.entity.Emprunt;
import com.alhdo.entity.Livre;
import com.sun.org.apache.xpath.internal.operations.String;

import javax.swing.*;

/*
 * Created by Castro Alhdo on 5/3/16.
 * File created af 4:55 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class Info {
    public static void showErrorInput(){
        JOptionPane errorInput = new JOptionPane();
        errorInput.showMessageDialog(null,"Veuillez remplir tous les champs","Erreur",JOptionPane.ERROR_MESSAGE);
    }
    public static void showThingsExist(String str){
        JOptionPane errorInput = new JOptionPane();
        errorInput.showMessageDialog(null,str+" existe Deja","Attention",JOptionPane.WARNING_MESSAGE);
    }

    public static void dataBaseConnectionError(){
        JOptionPane errorInput = new JOptionPane();
        errorInput.showMessageDialog(null,"Probleme de connection a la base de donnee","Erreur",JOptionPane.ERROR_MESSAGE);
    }
    public static void dateIncoherent(){
        JOptionPane errorInput = new JOptionPane();
        errorInput.showMessageDialog(null,"Vous ne pouvez pas faire un emprunt avec une date anterieur","Erreur",JOptionPane.ERROR_MESSAGE);
    }
    public static void showAdherentInfo(Adherent adherent){
        JOptionPane jOptionPane=new JOptionPane();
        java.lang.String msg="Code \t\t:"+adherent.getCode()+"\n";
                        msg+="Nom \t\t:"+adherent.getNom()+"\n";
                        msg+="Prenom \t\t:"+adherent.getPrenom()+"\n";
                        msg+="Telephone \t\t:"+adherent.getTelephone()+"\n";
                        msg+="Adresse \t\t:"+adherent.getAdresse()+"\n";
        jOptionPane.showMessageDialog(null ,msg,adherent.getPrenom(), JOptionPane.INFORMATION_MESSAGE);

    }
    public static void duplicateEntry(){
        JOptionPane errorInput = new JOptionPane();
        errorInput.showMessageDialog(null,"Cette entree existe deja desole !","Erreur",JOptionPane.ERROR_MESSAGE);

    }
    public static void showEmpruntInfo(Emprunt emprunt){
        JOptionPane jOptionPane=new JOptionPane();
        java.lang.String msg="Numero \t\t:"+emprunt.getNumeroTransaction()+"\n";
        msg+="Adherent \t\t:"+emprunt.getAdherentCode()+"\n";
        msg+="Examplaire No \t\t:"+emprunt.getExemplaireNumero()+"\n";
        msg+="Date Emprunt \t\t:"+emprunt.getDateEmprunt().toString()+"\n";
        msg+="Date Retour \t\t:"+emprunt.getDateRetour().toString()+"\n";
        jOptionPane.showMessageDialog(null ,msg,emprunt.getNumeroTransaction()+"", JOptionPane.INFORMATION_MESSAGE);

    }
    public static void showLivreInfo(Livre livre){
        JOptionPane jOptionPane=new JOptionPane();
        java.lang.String msg="ISBN \t\t:"+livre.getIBSN()+"\n";
        msg+="Titre \t\t:"+livre.getTitre()+"\n";
        msg+="Genre \t\t:"+livre.getGenre()+"\n";
        msg+="Pages \t\t:"+livre.getNbrPages()+"\n";

        jOptionPane.showMessageDialog(null ,msg,livre.getTitre(), JOptionPane.INFORMATION_MESSAGE);

    }
}
