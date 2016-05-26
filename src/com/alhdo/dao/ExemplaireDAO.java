package com.alhdo.dao;

import com.alhdo.database.Insert;
import com.alhdo.entity.Exemplaire;
import com.alhdo.util.Log;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*
 * Created by Castro Alhdo on 5/3/16.
 * File created af 10:49 PM
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
 * Classe ExemplaireDAO responsable de l'ajout supression et mise a jour des exemplaire de la base de donnee
 * @version 1.0
 * @author Castro Alhdo
 *
 * @see Exemplaire
 */
public class ExemplaireDAO extends DAO<Exemplaire> {
    /**
     * Contructeur de la classe ExemplaireDAO responsable des opereation d'emprunt
     * @param connect instance de biblioConnection
     */
    public ExemplaireDAO(Connection connect) {
        super(connect);
    }

    @Override
    /**
     * Methode pour inserer un exemplaire dans la base de donnee
     * @param emplaire instance de Exemplaire
     * @return vrai ou faux si la requete reussi ou pas
     */
    public boolean create(Exemplaire exemplaire) {
        new Insert(exemplaire);
        return true;
    }

    @Override
    /**
     * Methode pour la suppression d'un exemplaire dans la base de donnees
     * @param exemplaire instance d' Exemplaire
     * @return vrai ou faux tout depend de la reussite ou pas de la transaction
     */
    public boolean delete(Exemplaire exemplaire) {
        try {
            String request = "DELETE  FROM examplaires WHERE ISBN= ?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(request);
            preparedStatement.setString(1,exemplaire.getISBN());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    /**
     * Methode pour la mise a jour d'un exemplaire dans la base de donnee
     * @param exemplaire instance d' Exemplaire
     * @return vrai ou faux tout depend du resultat de la requete
     */
    public boolean update(Exemplaire exemplaire) {
        String request = "UPDATE examplaires SET etat = ?, dateAchat = ? WHERE ISBN =?";
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement(request);
            preparedStatement.setString(1,exemplaire.getEtat());
            preparedStatement.setDate(2,exemplaire.getDateAchat());
            preparedStatement.setString(3,exemplaire.getISBN());

            int i=preparedStatement.executeUpdate();
            Log.d("Update "+exemplaire.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    /**
     * Methode pour faire des recherches dans la base de donnee
     * retourne une arraylist qui contient les objets de type exemplaire
     * @see Exemplaire
     * @return ArrayList
     */
    public ArrayList<Exemplaire> rechercher(String recherche) {
        Exemplaire exemplaire ;
        ArrayList<Exemplaire> exemplaires = new ArrayList<>();
        try {
            String sq = "SELECT *" +" FROM examplaires WHERE ISBN = ? OR dateAchat =?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(sq);
            //String prenom = Integer.parseInt(recherche);
            preparedStatement.setString(1, recherche);
            preparedStatement.setDate(2, getD(recherche));

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                exemplaire = new Exemplaire(rs.getInt("numero"),rs.getString("etat"),rs.getDate("dateAchat"),rs.getString("ISBN"));
                exemplaires.add(exemplaire);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exemplaires;
    }

    /**
     * Retourne un array contenant les livre disponibles qui ne sont pas encore preter
     * @return ArrayList exemplaire disponible
     * @see Exemplaire
     */
    public ArrayList<Exemplaire> getLivreDispo(){
        ArrayList<Exemplaire> result=new ArrayList<>();
        try {
            String request= "SELECT * FROM examplaires WHERE dispo =1";
            PreparedStatement preparedStatement =this.connect.prepareStatement(request);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                result.add(new Exemplaire(resultSet.getInt("numero"),resultSet.getString("etat"),resultSet.getDate("dateAchat"),resultSet.getString("ISBN")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Failed too get List of Adherent");
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Erreur de varaible");
        }
        return result;
    }

    public ArrayList<Exemplaire> getLivreList(){
        ArrayList<Exemplaire> result=new ArrayList<>();
        try {
            String request= "SELECT * FROM examplaires";
            PreparedStatement preparedStatement =this.connect.prepareStatement(request);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                result.add(new Exemplaire(resultSet.getInt("numero"),resultSet.getString("etat"),resultSet.getDate("dateAchat"),resultSet.getString("ISBN")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Failed too get List of Adherent");
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Erreur de varaible");
        }
        return result;
    }

    @Override
    /**
     * Retourne tous les exemplaires disponibles dans la base de donnee
     * @return ArrayList  de tous les emprunts disponible
     * @see Exemplaire
     */
    public ArrayList<Exemplaire> getList() {
        ArrayList<Exemplaire> result=new ArrayList<>();
        try {
            String request= "SELECT * FROM examplaires";
            PreparedStatement preparedStatement =this.connect.prepareStatement(request);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                result.add(new Exemplaire(resultSet.getInt("numero"),resultSet.getString("etat"),resultSet.getDate("dateAchat"),resultSet.getString("ISBN"),resultSet.getBoolean("dispo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Failed too get List of Adherent");
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Erreur de varaible");
        }
        return result;
    }

    /**
     * Methode pour convertire un string en date (Exemple String recuperer d'un JTextField
     * @param s string a rechercher
     * @return Date
     */
    private  Date getD(String s){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date=null;
        try {

             date =  formatter.parse(s);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new java.sql.Date(date.getTime());
    }
}
