package com.alhdo.entity;

/*
 * Created by root on 4/26/16.
 */

/**
 * Entite livre de la base de donnee
 * @author Castro Alhdo
 * @version V1.0
 *
 */
public class Livre {
    /**
     * IBSN identite unique d'un livre
     * @see Livre#getIBSN()
     */
    String IBSN;
    /**
     * Titre d'un livre
     * @see Livre#getTitre()
     */
    String titre;

    /**
     * Le genre d'un livre
     * @see Livre#getGenre()
     */
    String genre;
    /**
     * Le nombre de page du livre
     * @see Livre#getNbrPages()
     */
    int nbrPages;

    /**
     * Le constructeur de l'entite livre qui prend les parametre suivant
     * @param IBSN
     * @param titre
     * @param genre
     * @param nbrPages
     */
    public Livre(String IBSN, String titre, String genre, int nbrPages) {
        this.IBSN = IBSN;
        this.titre = titre;
        this.genre = genre;
        this.nbrPages = nbrPages;
    }

    public Livre() {

    }

    /**
     * Retourne l'ISBN du livre
     * @return IBSN
     */
    public String getIBSN() {
        return IBSN;
    }


    /**
     * Mise a jor de l'ISBN
     * @param IBSN
     */
    public void setIBSN(String IBSN) {
        this.IBSN = IBSN;
    }

    /**
     * Retourn le titre du livre
     * @return titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Mise a jor du titre
     * @param titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Retourn le genre du livre
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Mise a jour du genre
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Retourn le nombre de page du livre
     * @return titre
     */
    public int getNbrPages() {
        return nbrPages;
    }

    /**
     * Mise a jor du nombre de pages
     * @param nbrPages
     */
    public void setNbrPages(int nbrPages) {
        this.nbrPages = nbrPages;
    }

    @Override
    /**
     * Methode toString pour afficher l'entite creer en console
     */
    public String toString() {
        return "Livre{" +
                "IBSN='" + IBSN + '\'' +
                ", titre='" + titre + '\'' +
                ", genre='" + genre + '\'' +
                ", nbrPages=" + nbrPages +
                '}';
    }
}
