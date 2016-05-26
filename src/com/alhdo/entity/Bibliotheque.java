package com.alhdo.entity;

import java.util.HashSet;
import java.util.Set;

/*
 * Created by Castro Alhdo on 5/1/16.
 * File created af 1:05 AM
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
 * Entite Bibliotheque Contenant la liste de tous les biblitheques
 * @author Castro Alhdo
 * @version v 1.0
 *
 */
public class Bibliotheque {
    private int id=0;

    //List des Adherents
    /**
     * Liste ordonnee des adherents de la biliotheque
     * @see Adherent
     */
    private Set<Adherent> listAdherent=new HashSet<Adherent>();
    //List des Exemplaires
    /**
     * Liste ordonnee des exemplaires de la bibliotheque
     * @see Exemplaire
     */
    private Set<Exemplaire> listExemplaire=new HashSet<Exemplaire>();

    public Bibliotheque(){

    }

    /**
     * Retourne la liste des adherents
     * @return Set Adherent
     * @see Adherent
     */
    public Set<Adherent> getListAdherent() {
        return listAdherent;
    }

    /**
     * Mise a jour de la liste des adherents
     * @param listAdherent
     */
    public void setListAdherent(Set<Adherent> listAdherent) {
        this.listAdherent = listAdherent;
    }

    /**
     * Retourne la liste des exemplaires de la bibliotheque
     * @return listExemplaire
     * @see Exemplaire
     */
    public Set<Exemplaire> getListExemplaire() {
        return listExemplaire;
    }


    /**
     * Mise a jour de la liste des exemplaires de la bibliotheque
     * @param listExemplaire
     * @see Exemplaire
     */
    public void setListExemplaire(Set<Exemplaire> listExemplaire) {
        this.listExemplaire = listExemplaire;
    }

    /**
     * Ajouter un Exemplaire dans la liste des exemlaire existant dans la base de donnee
     * @param exemplaire Un objet de l'entite exemplaire
     * @see Exemplaire
     */
    public void addExemplaire(Exemplaire exemplaire){
        if(!this.listExemplaire.contains(exemplaire)){
            this.listExemplaire.add(exemplaire);
        }
    }

    /**
     * Ajouter un Adherent dans la liste des Adherents existant dans la base de donnee
     * @param adherent Un objet de l'entite Adherent
     * @see Adherent
     */
    public void addAdherent(Adherent adherent){
        if(!this.listAdherent.contains(adherent)){
            this.listAdherent.add(adherent);
        }
    }
}
