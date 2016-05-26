package com.alhdo.dao;

import com.alhdo.database.BiblioConnection;

import java.sql.Connection;

/*
 * Created by Castro Alhdo on 5/1/16.
 * File created af 1:54 AM
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
 * Classe factory qui retoure une instance de la classe en question sans passer de parametre
 * @author Castro Alhdo
 * @version 1.0
 * @see DAO
 */
public class DAOFactory {
    /**
     * Une instance de de BiblioConnection
     * @see BiblioConnection
     */
    protected static final Connection conn= BiblioConnection.getInstance();

    /**
     * Retourne un objet Adherent intereagissant avec la base de donnees
     * @return Adherent
     */
    public static DAO getAdherentDAO(){
        return new AdherentDAO(conn);
    }

    /**
     * Retourne un objet de type LivreDAO
     * @return LivreDAO
     * @see LivreDAO
     */
    public static DAO getLivreDAO(){
        return new LivreDAO(conn);
    }

    /**
     * Retourne un objet de type ExemplaireDAO
     * @return ExemplaireDAO
     * @see ExemplaireDAO
     */
    public static DAO getExemplaireDAO(){
        return new ExemplaireDAO(conn);
    }

    /**
     * Retourne un objet de type EmpruntDAO
     *
     * @return EmpruntDAO
     * @see EmpruntDAO
     */
    public static DAO getEmpruntDAO(){
        return new EmpruntDAO(conn);
    }
}
