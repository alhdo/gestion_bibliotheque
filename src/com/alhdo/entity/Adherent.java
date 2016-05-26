package com.alhdo.entity;

/*
 * Created by root on 4/26/16.
 */

/**
 * Adherent est la classe de l'entite adherent de la bibliotheque
 * Son constructeur prend en parametre code,nom,prenom,telephone,adresse
 *
 * @author Castro Alhdo
 * @version 1.0
 */
public class Adherent {
    /**
     * Le code de l'adherent ce code est inchageable
     * @see Adherent#getCode()
     */
    int code;

    /**
     * Le nom de l'adherent Ce nom peut etre changer
     * @see Adherent#getNom()
     */
    String nom;

    /**
     * Le prenom de l'adherent
     * @see Adherent#getPrenom()
     */
    String prenom;

    /**
     * Le numero de telephone de l'adherent
     * @see Adherent#getTelephone()
     */
    String telephone;

    /**
     * L'adresse de l'adherent pour contact
     * @see Adherent#getAdresse()
     */
    String adresse;

    public Adherent() {
    }

    /**
     * Constructeur de l'adherent
     * @param code
     * Code de l'adhrent
     * @param nom
     *          nom de l'adherent
     * @param prenom
     *          prenom de l'adherent
     * @param telephone
     * @param adresse
     */
    public Adherent(int code, String nom, String prenom, String telephone, String adresse) {
        /**
         * Code de l'adherent identifiant unique
         */
        this.code = code;

        /**
         * le nom de l'adherent
         */
        this.nom = nom;

        /**
         * Le prenom de l'adherent
         */
        this.prenom = prenom;

        /**
         * Le telephone de l'adhrent
         */
        this.telephone = telephone;

        /**
         * L'adresse de l'adhrent
         */
        this.adresse = adresse;
    }

    /**
     * Retourne le code de l'adhent
     * @return l'identifiant unique de l'adherent
     */
    public int getCode() {
        return code;
    }

    /**
     * Met a jour le code de l'adherent
     * @param code
     * @see Adherent
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Retourne le nom de l'adherent
     * @return le nom de l'adherent
     *
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met a jour le nom de l'adherent
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * Retourn le nom de l'adherent
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Met a jour le prenom de l'adherent
     * @param prenom
     * @see Adherent
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourn le numero de telephone de l'adherent
     * @return prenom
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Met a jour le numero de telephone de l'adherent
     * @param telephone
     * @see Adherent
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Retourn l'adresse  de l'adherent
     * @return prenom
     */
    public String getAdresse() {
        return adresse;
    }


    /**
     * Met a jour l'adresse de l'adherent
     * @param adresse
     * @see Adherent
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "code=" + code +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
