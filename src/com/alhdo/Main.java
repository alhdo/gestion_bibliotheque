package com.alhdo;

import com.alhdo.dao.AdherentDAO;
import com.alhdo.dao.DAO;
import com.alhdo.dao.DAOFactory;
import com.alhdo.database.BiblioConnection;
import com.alhdo.database.Insert;
import com.alhdo.entity.Adherent;
import com.alhdo.entity.Emprunt;
import com.alhdo.entity.Exemplaire;
import com.alhdo.entity.Livre;
import com.alhdo.ui.MainWindow;
import com.alhdo.util.Log;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static  Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
//        Log.v();
        new MainWindow();

    }

    public static int getNextInt(){
        Scanner scanner=new Scanner(System.in);
        while (!scanner.hasNextInt()){
            System.out.println("Please choose on of this number provided below");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    public static Date getD(){
        String maDate="09/02/2016";
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date finalDate=null;
        try {
             finalDate=format.parse(maDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert finalDate != null;
        return new java.sql.Date(finalDate.getTime());
    }

    public static boolean InsertAdherent(){
        System.out.println("Please enter the Firstname of Adherent");
        String nom=sc.nextLine();

        while (nom.matches(".*\\d+.*") || verifyCarac(nom) ){
            System.out.println("Please Enter a Valid name :");
            nom=sc.nextLine();
        }
        System.out.println("Please enter the Lastname of Adherent");
        String prenom=sc.nextLine();
        while (prenom.matches(".*\\d+.*") || verifyCarac(prenom) ){
            System.out.println("Please Enter a Valid name :");
            prenom=sc.nextLine();
        }

        System.out.println("Please enter the Phone Number of Adherent");
        String phone=sc.nextLine();
        while (verifyPhone(phone)){
            System.out.println("Please Enter a Valid phone number Ex : 50937929793 :");
            phone=sc.nextLine();
        }

        System.out.println("Please enter the Adress of Adherent");
        String adress=sc.nextLine();
        DAO adherentDAO=DAOFactory.getAdherentDAO();
        adherentDAO.create(new Adherent(0,nom,prenom,phone,adress));

        return false;
    }
    public static void InsertLivre(){
        System.out.println("Please enter the title of the book");
        String title=sc.nextLine();

        System.out.println("Please enter the genre of the book");
        String genre=sc.nextLine();
        while (genre.matches(".*\\d+.*") || verifyCarac(genre) ){
            System.out.println("Please Enter a Valid gnere :");
            genre=sc.nextLine();
        }
        System.out.println("Please enter the number of page of the book");
        int nbrPages=sc.nextInt();
        while (verifyPhone(""+nbrPages)){
            System.out.println("Please Enter a Valid phone number Ex : 213 :");
            nbrPages=sc.nextInt();
        }
        sc.nextLine();

        System.out.println("Please enter the ISBN of the book");
        String ISBN=sc.nextLine();
        while (verifyPhone(""+nbrPages)){
            System.out.println("Please Enter a Valid ISBN Ex : 978-2-212-12972-4 :");
            ISBN=sc.nextLine();
        }

        DAO livreDao=DAOFactory.getLivreDAO();
        livreDao.create(new Livre(ISBN,title,genre,nbrPages));


    }

    public static boolean verifyCarac(String str){
        Pattern p =Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m=p.matcher(str);
        return m.find();
    }
    public static boolean verifyPhone(String str){
        Pattern p =Pattern.compile("[^0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m=p.matcher(str);
        return m.find();
    }
}
