package com.alhdo.entity;


import com.alhdo.dao.DAOFactory;
import com.alhdo.dao.LivreDAO;
import com.alhdo.database.BiblioConnection;

import java.sql.Date;

/*
 * Created by Castro Alhdo on 4/26/16.
 * File created af 10:06 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */

/**
 * Classe de l'Entite exemplaire dans la base de donnee
 * @author Castro Alhdo
 * @version 1.0
 */
public class Exemplaire {
    /**
     * Numero de l'exemplaire l'identifiant unique
     */
    int numero;

    /**
     * L'etat du livre
     * <ul>
     *     <li>Bon</li>
     *     <li>Moyen</li>
     *     <li>Mauvais</li>
     * </ul>
     */
    String etat;

    /**
     * La date d'achat du livre
     */
    Date dateAchat;

    /**
     * l'ISBN du livre identifiant unique
     */
    String ISBN;





    boolean dispo;
    /**
     * L'objet livre qu'on doit mettre un exemplaire
     */
    Livre livre;

    public Exemplaire(int numero, String etat, Date dateAchat, String ISBN) {
        this.numero = numero;
        this.etat = etat;
        this.dateAchat = dateAchat;
        this.ISBN = ISBN;
        this.livre = new LivreDAO(BiblioConnection.getInstance()).getLivreWithISBN(ISBN);
    }

    public Exemplaire(int numero, String etat, Date dateAchat, Livre livre) {
        this.numero = numero;
        this.etat = etat;
        this.dateAchat = dateAchat;
        this.livre = livre;
        this.ISBN=livre.getIBSN();
    }

    public Exemplaire(int numero, String etat, Date dateAchat, String ISBN, boolean dispo) {
        this.numero = numero;
        this.etat = etat;
        this.dateAchat = dateAchat;
        this.ISBN = ISBN;
        this.dispo = dispo;

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }
    @Override
    public String toString() {
        return "Exemplaire{" +
                "numero=" + numero +
                ", etat='" + etat + '\'' +
                ", dateAchat=" + dateAchat +
                ", ISBN='" + ISBN + '\'' +
                ", livre=" + livre +
                '}';
    }

}
