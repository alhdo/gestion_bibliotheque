package com.alhdo.entity;


import com.alhdo.dao.AdherentDAO;
import com.alhdo.database.BiblioConnection;

import java.sql.Date;

/*
 * Created by Castro Alhdo on 4/26/16.
 * File created af 10:11 PM
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
 * Emprunt est la classe de l'entite emprunt de la bibliotheque
 * Cette classe permet d'effectuer des emprunts et retours
 * Son constructeur prend en parametre numeroTransaction,adherent,exemplaire,dateEmprunt, dateRetour
 *
 * @author Castro Alhdo
 * @version 1.0
 */
public class Emprunt {

    /**
     * Le numero de transaction identifiant unique de chaque transaction
     */
    int numeroTransaction;

    /**
     * Instance d'adherent qui veut faire l'emprunt
     * @see Adherent
     */
    Adherent adherent;

    /**
     * L'exemplaire qu'on veut emprunter
     * @see Exemplaire
     */
    Exemplaire exemplaire;

    /**
     * Le code de l'adherent
     * @see Adherent
     */
    int adherentCode;

    /**
     * Le numero unique de l'exemplaire
     * @see Exemplaire
     */
    int exemplaireNumero;

    /**
     * La date de l'emprunt du livre
     */
    Date dateEmprunt;

    /**
     * La date de retour du livre
     */
    Date dateRetour;

    /**
     * Constructeur d'un emprunt pour creer un emprunt dans la base de donnee
     * @param numeroTransaction numero unique de la transaction
     * @param adherent L'adherent qui veut faire l'emprunt
     * @param exemplaire L'exemplaire qu'on veut preter a l'adherent
     * @param dateEmprunt La date l'emprunt
     * @param dateRetour La date de retour
     */
    public Emprunt(int numeroTransaction, Adherent adherent, Exemplaire exemplaire, Date dateEmprunt, Date dateRetour) {
        this.numeroTransaction = numeroTransaction;
        this.adherent = adherent;
        this.exemplaire = exemplaire;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.adherentCode= adherent.getCode();
        this.exemplaireNumero = exemplaire.getNumero();


    }

    public int getCode() {
        return adherent.getCode();
    }

    public void setCode(int code) {
        adherent.setCode(code);
    }

    public String getPrenom() {
        return adherent.getPrenom();
    }

    public String getNom() {
        return adherent.getNom();
    }

    public String getTelephone() {
        return adherent.getTelephone();
    }

    public void setNom(String nom) {
        adherent.setNom(nom);
    }

    public String getAdresse() {
        return adherent.getAdresse();
    }

    public void setAdresse(String adresse) {
        adherent.setAdresse(adresse);
    }

    public void setTelephone(String telephone) {
        adherent.setTelephone(telephone);
    }

    public void setPrenom(String prenom) {
        adherent.setPrenom(prenom);
    }

    public Emprunt(int numeroTransaction, int adherentCode, int exemplaireNumero, Date dateEmprunt, Date dateRetour) {
        this.numeroTransaction = numeroTransaction;
        this.adherentCode = adherentCode;
        this.exemplaireNumero = exemplaireNumero;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.adherent=new AdherentDAO(BiblioConnection.getInstance()).getAdherentByCode(adherentCode);
    }

    /**
     * Retourne le code de l'adhrent qui a fait l'emprunt
     * @return adherentCode
     */
    public int getAdherentCode() {
        return adherentCode;
    }

    /**
     * Mise a jour du code de l'adherent
     * @param adherentCode Le code unique de l'adherent
     */
    public void setAdherentCode(int adherentCode) {
        this.adherentCode = adherentCode;
    }

    /**
     * Retourne le numero de l'exemplaire preter
     * @return exemplaireNumero Le numero unique de l'exemplaire
     */
    public int getExemplaireNumero() {
        return exemplaireNumero;
    }

    /**
     * Mise a jour du numero de l'exemplaire
     * @param exemplaireNumero numero unique de l'exemplaire
     * @see Exemplaire
     */
    public void setExemplaireNumero(int exemplaireNumero) {
        this.exemplaireNumero = exemplaireNumero;
    }

    /**
     * Retourne le numero unique de transaction
     * @return numeroTransaction numerqo unique de la transaction
     */
    public int getNumeroTransaction() {
        return numeroTransaction;
    }

    /**
     * Mise a jour du numero de transaction
     * @param numeroTransaction numerqo unique de transaction
     */
    public void setNumeroTransaction(int numeroTransaction) {
        this.numeroTransaction = numeroTransaction;
    }

    /**
     * Retourne l'entite adherent qui fait le pret
     * @see Adherent
     * @return adherent Entite de l'adherent
     */
    public Adherent getAdherent() {
        return adherent;
    }

    /**
     * Mise a jour de l'adherent qui fait le pret
     * @see Adherent
     * @param adherent Entite adherent
     */
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }


    /**
     * Retourne l'entite de l'exemplaire qui fait le pret
     * @see Exemplaire
     * @return exemplaire Entite Exemplaire
     */
    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    /**
     * Mise a jour de l'exemplaire qu'on veut preter
     * @param exemplaire objet de l'entite Exemplaire
     *                   @see Exemplaire
     */
    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    /**
     * Retourne la date de l'emprunt
     * @return dateEmprunt date ou on avait fait l'emprunt
     */
    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    /**
     * Mise a jour de la date d'emprunt
     * @param dateEmprunt date ou on emprunte le livre
     */
    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    /**
     * Retour la date de retout
     * @return dateRetour date ou on remet l'exemplaire preter
     * @see Exemplaire
     */
    public Date getDateRetour() {
        return dateRetour;
    }

    /**
     * Mise a jour de la date de retour pour la remise
     * @see com.alhdo.dao.EmpruntDAO
     * @param dateRetour
     */
    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
}
