package com.alhdo.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/*
 * Created by Castro Alhdo on 5/11/16.
 * File created af 2:24 AM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class DateUtil {
    /**
     * Methode pour la convertion d'une chaine de caractere en date SQL
     * @param s la date en string
     * @return la date en format SQL pour les requetes
     */
    public static Date getD(String s){
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
    public static Date getDFormat(String s){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
    public static java.util.Date getDa(String s){
        Log.d(s);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date=null;
        try {

            date =  formatter.parse(s);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * Methode pour comparer deux date representer par des string
     * @param starting date du debut
     * @param ending date de retour
     * @return vrai si la date de retour est apres la date d'emprunt
     */
    public static boolean compareDate(String starting, String ending){
        String startDate = starting;
        String endDate = ending;

        try {
            java.util.Date start = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
                    .parse(startDate);
            java.util.Date end = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
                    .parse(endDate);

            System.out.println(start);
            System.out.println(end);

            if (start.compareTo(end) > 0) {
                return false;
            } else if (start.compareTo(end) < 0) {
                return true;
            } else if (start.compareTo(end) == 0) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }
}
