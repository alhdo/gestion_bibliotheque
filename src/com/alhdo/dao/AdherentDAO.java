package com.alhdo.dao;

import com.alhdo.database.Insert;
import com.alhdo.entity.Adherent;
import com.alhdo.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Created by Castro Alhdo on 5/1/16.
 * File created af 1:17 AM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *         ||----w |
 *         ||     ||
 */

/**
 * Classe DAO gerant les operations des adherents dans la base de donnee
 * @version 1.0
 * @author Castro Alhdo
 * @see Adherent
 */
public class AdherentDAO extends DAO<Adherent> {


    public AdherentDAO(Connection connect) {
        super(connect);
    }

    @Override
    /**
     *@param adherent
     *      L'objet adherent creer avec les informations de l'utilisateur
     *@return boolean
     *        retourne vrai si l'adherent est bien inscrit false dans le cas contraire
     */
    public boolean create(Adherent obj) {
        new Insert(obj);
        return false;
    }

    @Override
    /**
     * @param adherent
     * Methode pour effacer un adherent dans la base de donnee avec une requete preparer
     * @return vrai ou faux si la transaction reussi ou pas
     */
    public boolean delete(Adherent obj) {
        try {
            String request = "DELETE  FROM adherents WHERE code= ?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(request);
            preparedStatement.setInt(1,obj.getCode());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    /**
     * @param adherent
     * @see Adherent
     * Methode pour faire la mise a jour d'un adherent de la base de donnee
     *Utilisation d'une requete preparer
     * @return vrai ou faux si la transaction reussi ou pos
     */
    public boolean update(Adherent obj) {

        String request = "UPDATE adherents SET nom = ?, prenom = ?, telephone = ?, adresse = ? WHERE code =?";
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement(request);
            preparedStatement.setString(1,obj.getNom());
            preparedStatement.setString(2,obj.getPrenom());
            preparedStatement.setString(3,obj.getTelephone());
            preparedStatement.setString(4,obj.getAdresse());
            preparedStatement.setInt(5,obj.getCode());
            int i=preparedStatement.executeUpdate();
            Log.d("Update "+obj.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    /**
     * @return ArrayList des adherent
     * Methode pour faire une recherche d'un adherent de la base de donnee
     * @see Adherent
     * @param recherche
     */
    public ArrayList<Adherent> rechercher(String recherche) {
        Adherent adherent = new Adherent();
        ArrayList<Adherent> adherents = new ArrayList<>();
        try {
            String sq = "SELECT *" +" FROM adherents WHERE prenom = ? OR nom =?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(sq);
            //String prenom = Integer.parseInt(recherche);
            preparedStatement.setString(1, recherche);
            preparedStatement.setString(2, recherche);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                adherent = new Adherent(rs.getInt("code"), rs.getString("nom"), rs.getString("prenom"), rs.getString("telephone"), rs.getString("adresse"));
                adherents.add(adherent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adherents;
    }
    public Adherent getAdherentByCode(int code){
        Adherent adherent = new Adherent();

        try {
            String sq = "SELECT *" +" FROM adherents WHERE code=?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(sq);
            //String prenom = Integer.parseInt(recherche);
            preparedStatement.setInt(1, code);


            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                adherent = new Adherent(rs.getInt("code"), rs.getString("nom"), rs.getString("prenom"), rs.getString("telephone"), rs.getString("adresse"));
              return adherent;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return adherent;
    }

    @Override
    /**
     * @return ArrayList des adherent
     * Methode de pour recuperer la liste des adherent dans la base de donnee
     * @see Adherent
     */
    public ArrayList<Adherent> getList() {
        ArrayList<Adherent> result=new ArrayList<>();
        try {
            String request= "SELECT * FROM adherents";
            PreparedStatement preparedStatement =this.connect.prepareStatement(request);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                result.add(new Adherent(resultSet.getInt("code"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("telephone"), resultSet.getString("adresse")));
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


}
