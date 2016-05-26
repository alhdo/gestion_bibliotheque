package com.alhdo.dao;

import com.alhdo.database.Insert;
import com.alhdo.entity.Livre;
import com.alhdo.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Created by Castro Alhdo on 5/1/16.
 * File created af 10:45 PM
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
 * Classe gerant les operations sur les livres dans la base de donnee
 * <ul>
 *     <li>Mise a jour</li>
 *     <li>Ajout</li>
 *     <li>Delete</li>
 * </ul>
 */
public class LivreDAO extends DAO<Livre>{
    /**
     * Constructeur de LivreDAO prenant en parametre une instance de biblioconnection
     * @see com.alhdo.database.BiblioConnection
     * @param connect instance de Biblioconnection
     * @return LivreDAO Une instance de Connection
     */
    public LivreDAO(Connection connect) {
        super(connect);
    }

    @Override
    /**
     * Methode pour inserer un livre dans la base de donnee en utilisant l'entite Livre
     * @see Livre
     * @return true or false tout depend du resultat de la requete
     */
    public boolean create(Livre obj) {
        new Insert(obj);
        return false;
    }

    @Override
    /**
     * Methode pour supprimer un livre de la base de donnee
     * @param livre
     */
    public boolean delete(Livre livre) {
        try {
            String request = "DELETE  FROM livres WHERE ISBN= ?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(request);
            preparedStatement.setString(1,livre.getIBSN());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    /**
     * Methode pour la mise d'un d'un livre dans la base de donnee en utilisant l'entite livre
     * @see Livre
     * @param livre
     * @return true or false en fonction du resultat de la requete
     */
    public boolean update(Livre obj) {
        String request = "UPDATE livres SET titre = ?, genre = ?, nbrPages = ? WHERE ISBN =?";
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement(request);
            preparedStatement.setString(1,obj.getTitre());
            preparedStatement.setString(2,obj.getGenre());
            preparedStatement.setInt(3,obj.getNbrPages());
            preparedStatement.setString(4,obj.getIBSN());

            int i=preparedStatement.executeUpdate();
            Log.d("Update "+obj.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    /**
     * Methode pour faire des recherches dans la base de donnee
     * retourne une arraylist qui contient les objets de type livre
     * @see Livre
     * @return ArrayList
     */
    public ArrayList<Livre> rechercher(String recherche) {
        Livre livre ;
        ArrayList<Livre> livres = new ArrayList<>();
        try {
            String sq = "SELECT *" +" FROM livres WHERE titre = ? OR genre =?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(sq);
            //String prenom = Integer.parseInt(recherche);
            preparedStatement.setString(1, recherche);
            preparedStatement.setString(2, recherche);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                livre = new Livre(rs.getString("ISBN"),rs.getString("titre"),rs.getString("genre"),rs.getInt("nbrPages"));
                livres.add(livre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livres;
    }
    public Livre getLivreWithISBN(String isbn){
        Livre livre=null ;


        try {
            String sq = "SELECT *" +" FROM livres WHERE ISBN = ?";
            PreparedStatement preparedStatement = this.connect.prepareStatement(sq);
            //String prenom = Integer.parseInt(recherche);
            preparedStatement.setString(1, isbn);


            ResultSet rs = preparedStatement.executeQuery();
            if (rs.first()) {
                livre = new Livre(rs.getString("ISBN"),rs.getString("titre"),rs.getString("genre"),rs.getInt("nbrPages"));
                Log.i("Search from livres");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livre;

    }

    @Override
    /**
     * Methode pour recuperer les livres dans la base de donnee
     * retourne une arraylist qui contient les objets de type livre
     * @see Livre
     * @return ArrayList
     */
    public ArrayList<Livre> getList() {
        ArrayList<Livre> result=new ArrayList<>();
        try {
            String request= "SELECT * FROM livres";
            PreparedStatement preparedStatement =this.connect.prepareStatement(request);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                result.add(new Livre(resultSet.getString("ISBN"),
                                     resultSet.getString("titre"),
                                     resultSet.getString("genre"),
                                     resultSet.getInt("nbrPages")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Failed too get List of book");
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.e("Erreur de variable");
        }
        return result;
    }
}
