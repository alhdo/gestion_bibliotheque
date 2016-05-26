package com.alhdo.dao;

import com.alhdo.database.Insert;
import com.alhdo.entity.Emprunt;
import com.alhdo.entity.Exemplaire;
import com.alhdo.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Created by Castro Alhdo on 5/4/16.
 * File created af 2:00 PM
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
 * <b>EmpruntDAO est la classe qui gere les transactions des emprunts dans la base de donnee</b>
 * Cette classe utilise l'entite Emprunt
 * @see Emprunt
 *
 * @author Castro Alhdo
 *@version 1.0
 */
public class EmpruntDAO extends DAO<Emprunt>{
    /**
     * Contructeur par defaut qui prend en parametre une instance de connection
     * @see com.alhdo.database.BiblioConnection
     * @param connect
     */
    public EmpruntDAO(Connection connect) {
        super(connect);
    }

    @Override
    /**
     * Methode pour faire un emprunt
     * @param emprunt
     * @see Emprunt
     */
    public boolean create(Emprunt obj) {
        Insert.emprunter(obj);
        return false;
    }

    @Override
    public boolean delete(Emprunt obj) {


        try {
            String request = "DELETE  FROM emprunt WHERE numeroTransaction = ?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(request);
            preparedStatement.setInt(1,obj.getNumeroTransaction());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    /**
     * Methode pour faire une mise a jour d'un emprunt
     * @param emprunt
     * @see Emprunt
     */
    public boolean update(Emprunt obj) {
       new Insert(obj.getExemplaireNumero());
        return true;
    }

    @Override
    /**
     * Methode pour faire des recherches dans la base de donnee
     * retourne une arraylist qui contient les objets de type emprunt
     * @see Emprunt
     * @return ArrayList
     */
    public ArrayList<Emprunt> rechercher(String recherche) {
        return null;
    }

    @Override
    /**
     * Methode pour recuperer les emprunts dans la base de donnee
     * retourne une arraylist qui contient les objets de type emprunt
     * @see Emprunt
     * @return ArrayList
     */
    public ArrayList<Emprunt> getList() {
        ArrayList<Emprunt> result=new ArrayList<>();
        try {
            String request= "SELECT * FROM emprunt";
            PreparedStatement preparedStatement =this.connect.prepareStatement(request);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                result.add(new Emprunt(resultSet.getInt("numeroTransaction"),resultSet.getInt("adherents_code"),resultSet.getInt("examplaires_numero"),resultSet.getDate("dateEmprunt"),resultSet.getDate("dateRetour")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Failed too get List of Emprunt");
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Erreur de varaible");
        }
        return result;
    }
    public Emprunt getEmpruntByNumero(int num) {
        Emprunt emprunt=null;
        try {
            String request= "SELECT * FROM emprunt WHERE numeroTransaction = ?";
            PreparedStatement preparedStatement =this.connect.prepareStatement(request);
            preparedStatement.setInt(1,num);
            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.first()){
                emprunt=new Emprunt(resultSet.getInt("numeroTransaction"),resultSet.getInt("adherents_code"),resultSet.getInt("examplaires_numero"),resultSet.getDate("dateEmprunt"),resultSet.getDate("dateRetour"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Failed too get List of Emprunt");
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Erreur de varaible");
        }
        return emprunt;
    }
}
