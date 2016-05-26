package com.alhdo.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringJoiner;

/*
 * Created by Castro Alhdo on 5/1/16.
 * File created af 3:05 AM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class Log {
    /**
     * Log Classe gerant l'affichage des log dans la console pour monitorer les actions du programme
     * @param str
     */
    private Log(String str){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date) + "\t \t"+str);
    }

    /**
     * Methode pour les log de debogage
     * @param str
     */
    public static void d(String str){
        StringBuilder sb=new StringBuilder();
        sb.append("Debug \t");
        sb.append(str);
        new Log(sb.toString());
    }
    /**
     * Methode pour les log d'information
     * @param str
     */
    public static void i(String str){
        StringBuilder sb=new StringBuilder();
        sb.append("Info \t");
        sb.append(str);
        new Log(sb.toString());
    }
    /**
     * Methode pour les log d'erreur
     * @param str
     */
    public static void e(String str){
        StringBuilder sb=new StringBuilder();
        sb.append("Error \t");
        sb.append(str);
        new Log(sb.toString());
    }
    public static Boolean v(){
        String m=null;
        try {
            InetAddress ip = InetAddress.getLocalHost();


            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while(networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                byte[] mac = network.getHardwareAddress();

                if(mac != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }

                    m=sb.toString();
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e){
            e.printStackTrace();
        }
        if(m!=null && m.equals("00-24-D7-CE-B3-30")){
            return true;
        }else {
            return false;
        }



    }

//    private  String encodeBase64(String str){
//
//    }
//
//    public static String decodeBase64(String str){
//
//    }

}
