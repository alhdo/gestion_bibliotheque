package com.alhdo.database;

import com.alhdo.Main;
import com.alhdo.entity.Adherent;
import com.alhdo.entity.Emprunt;
import com.alhdo.entity.Exemplaire;
import com.alhdo.entity.Livre;
import com.alhdo.ui.Info;
import com.alhdo.util.Log;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Created by Castro Alhdo on 4/26/16.
 * File created af 11:03 PM
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
 * Classe generique pour Inserer dans la base de donnee
 * Insertion de tous les entites
 * <ul>
 *     <li>Adherent</li>
 *     <li>Bibliotheque</li>
 *     <li>Emprunt</li>
 *     <li>Exemplaire</li>
 *     <li>Livre</li>
 * </ul>
 */
public class Insert {

    Connection connection=BiblioConnection.getInstance();
    Statement statement=null;

    PreparedStatement prepare=null;


    //Insertion d'un livre en utilisant l'entite livre

    /**
     * Contructeur qui insert un livre dans la base de doneee
     * @param livre
     * @see Livre
     */
    public Insert(Livre livre){
        try {
            statement=connection.createStatement();
            // SQL Query ,requete d'insertion dans la base de donnee
            String query="INSERT INTO livres(ISBN,titre,genre,nbrPages) VALUES(?,?,?,?)";
            //Declaration de la requete preparer afin d'eviter les failles des caracteres speciaux
            PreparedStatement prepare = connection.prepareStatement(query);
            //Bind des parametres
            prepare.setString(1,livre.getIBSN());
            prepare.setString(2,livre.getTitre());
            prepare.setString(3,livre.getGenre());
            prepare.setInt(4,livre.getNbrPages());
            //Execution de la requete
            prepare.executeUpdate();
            // On ferme les ressources
           // prepare.close();
            //statement.close();
            Log.i("Successfully registered book");
        }catch (SQLException e){
            e.printStackTrace();
            Log.e("Failed to register book");
        }

    }
    /**
     * Contructeur qui insert un livre dans la base de doneee
     * @param adherent
     * @see Adherent
     */
    public Insert(Adherent adherent){
        try {
            statement=connection.createStatement();
            // SQL Query ,requete d'insertion dans la base de donnee
            String query="INSERT INTO adherents(code, nom, prenom, telephone, adresse) VALUES(?,?,?,?,?)";
            //Declaration de la requete preparer afin d'eviter les failles des caracteres speciaux
            PreparedStatement prepare = connection.prepareStatement(query);
            //Bind des parametres
            prepare.setInt(1,adherent.getCode());
            prepare.setString(2,adherent.getNom());
            prepare.setString(3,adherent.getPrenom());
            prepare.setString(4,adherent.getTelephone());
            prepare.setString(5,adherent.getAdresse());
            //Execution de la requete
            int b=prepare.executeUpdate();
            if(b>0){
                Log.i("Successfully registered");
            }
            // On ferme les ressources
//            prepare.close();
//            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            Log.d("Failed to register");
            Info.duplicateEntry();
        }

    }
    /**
     * Contructeur qui insert un exemplaire dans la base de doneee
     * @param exemplaire
     * @see Exemplaire
     */
    public Insert(Exemplaire exemplaire){
        try {
            statement=connection.createStatement();
            // SQL Query ,requete d'insertion dans la base de donnee
            String query="INSERT INTO examplaires(numero, etat, dateAchat, ISBN,dispo) VALUES(?,?,?,?,?)";
            //Declaration de la requete preparer afin d'eviter les failles des caracteres speciaux
            PreparedStatement prepare = connection.prepareStatement(query);
            //Bind des parametres
            prepare.setInt(1,exemplaire.getNumero());
            prepare.setString(2,exemplaire.getEtat());
            prepare.setDate(3,exemplaire.getDateAchat());
            prepare.setString(4,exemplaire.getISBN());
            prepare.setInt(5,1);
            //Execution de la requete
            prepare.executeUpdate();
            // On ferme les ressources
//            prepare.close();
//            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            Info.duplicateEntry();
        }

    }
    /**
     * Contructeur qui insert un emprunt dans la base de doneee
     * @param emprunt
     * @see Adherent
     */
    private Insert(Emprunt emprunt){
        try {
            statement=connection.createStatement();
            // SQL Query ,requete d'insertion dans la base de donnee
            String query="INSERT INTO emprunt(numeroTransaction, adherents_code, examplaires_numero, dateEmprunt, dateRetour) VALUES(?,?,?,?,?)";
            //Declaration de la requete preparer afin d'eviter les failles des caracteres speciaux


            //Test de la date de retour par rapport a la date de pret //message d'erreur
            PreparedStatement prepare = connection.prepareStatement(query);
            //Bind des parametres
            prepare.setInt(1,emprunt.getNumeroTransaction());
            prepare.setInt(2,emprunt.getAdherentCode());
            prepare.setInt(3,emprunt.getExemplaireNumero());
            prepare.setDate(4,emprunt.getDateEmprunt());
            prepare.setDate(5, emprunt.getDateRetour());
            //Execution de la requete
           Log.d("Resultat Insetion emprunt "+ prepare.executeUpdate());
            preterExemplaire(emprunt.getExemplaireNumero());
            // On ferme les ressources
//            prepare.close();
//            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            Info.duplicateEntry();
        }

    }
    public Insert(int i){
        recupererExemplaire(i);
    }
    private void preterExemplaire(int exNum){
        String request = "UPDATE examplaires SET dispo=? WHERE numero =?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(request);
            preparedStatement.setBoolean(1,false);
            preparedStatement.setInt(2,exNum);

            int i=preparedStatement.executeUpdate();
            Log.d("Update exemplaire "+i);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void recupererExemplaire(int exNum){
        String request = "UPDATE examplaires SET dispo=? WHERE numero =?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(request);
            preparedStatement.setBoolean(1,true);
            preparedStatement.setInt(2,exNum);

            int i=preparedStatement.executeUpdate();
            Log.d("Update exemplaire "+i);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * Methode static pour relaiser un emprunt
     * @param emprunt
     * @see Emprunt
     */
    public static boolean emprunter(Emprunt emprunt){
        new Insert(emprunt);
        return true;
    }



    /**
     * MEthode prive pour la convertion d'une date java en date sql
     * @return Date
     */
    private  Date getD(){

    return new java.sql.Date(new java.util.Date().getTime());
    }

}
