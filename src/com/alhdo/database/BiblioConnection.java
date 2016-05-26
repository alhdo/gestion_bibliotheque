package com.alhdo.database;

import com.alhdo.ui.Info;
import com.alhdo.util.Log;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Created by Castro Alhdo on 4/26/16.
 * File created af 10:47 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class BiblioConnection {
    /**
    * Les variables contenant les identifiants de la base de donnees
    * url ="jdbc:mysql://localhost:3306/javadb" remplacer javadb par le nom de votre base de donnee
    * user = le nom d'utilisateur d'habitude root
    * password = le password de votre base de donnees laisser vide si pas de password
     */
    private static String url="jdbc:mysql://localhost:3306/javadb";
    private static String user="root";
    private static String password="";

    //Instance de la connection
    private static Connection connect;

    //Private Contructor

    /**
     * Constructeur privee qui retourne une instance de Biblioconnecton
     */
    private BiblioConnection(){
    //initiation de la connection avec la base de donnee try catch pour gerer les exeption
        try {
            connect= DriverManager.getConnection(url,user,password);
        } catch (CommunicationsException cm){
            Info.dataBaseConnectionError();
            Log.e("La connection a la base de donnees n'a pas pu etre etablie");
            System.exit(0);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     Le singleton qui donne un instance de la base
     a chaque appell on regarde si connect est null
     si c'est null on initie la connection avec l'appel au constructeur
     Si ce n'est pas null on retourne l instance en memoire
     comme ca on a qu'une instance faisant appel a la base de donnee
     */

    public static Connection getInstance(){
        if(connect==null){
            new BiblioConnection();
            Log.i("INSTANCIATION DE LA CONNEXION SQL ! ");
        }else{
            Log.d("CONNEXION SQL EXISTANTE ! ");
        }
        return connect;
    }
}
