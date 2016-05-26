package com.alhdo.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Set;

/*
 * Created by Castro Alhdo on 5/1/16.
 * File created af 1:13 AM
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
 * Classe Generique et abstraite qui gere les access a la base de donnee
 * @param <T>
 * @author Castro Alhdo
 * @version 1.0
 */
public abstract class DAO<T> {

    /**
     *Classe abstraitre de gestion de la base de donnee
     */
    protected Connection connect = null;

    public DAO(Connection connect){
        this.connect=connect;
    }

    /**
     * Methode a redefinire pour pour la creation ou l'insertion d'un objet
     * @param obj de la classe fille
     * @return boolean la reponse de la base de donnee
     */
    public abstract boolean create(T obj);

    /**
     * Methode de supression
     * @param obj de la classe fille
     * @return vrai si c'est ok et false dans le cas contraire
     */
    public abstract boolean delete(T obj);

    /**
     * Methode pour la mise a jour
     * @param obj de la classe fille
     * @return vrai si c'est ok et false dans le cas contraire
     */
    public abstract boolean update(T obj);

    /**
     * Methode pour la recherche
     * @param recherche String de recherche
     * @return vrai si c'est ok et false dans le cas contraire
     */
    public abstract ArrayList<T> rechercher(String recherche);

    /**
     * Avoir tout les objets de type T de la classe
     * @return ArrayList de l'objet
     */
    public abstract ArrayList<T> getList();
}
