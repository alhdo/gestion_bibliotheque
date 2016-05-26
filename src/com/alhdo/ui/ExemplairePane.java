package com.alhdo.ui;

import com.alhdo.dao.DAO;
import com.alhdo.dao.DAOFactory;
import com.alhdo.dao.LivreDAO;
import com.alhdo.database.BiblioConnection;
import com.alhdo.entity.Exemplaire;
import com.alhdo.entity.Livre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

/*
 * Created by Castro Alhdo on 5/4/16.
 * File created af 1:55 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class ExemplairePane extends JPanel {
    JTable table;
    DefaultTableModel tableModel;
    public ExemplairePane(){
        String title[]={"Numero","ISBN","etat","Data Achat","Disponible"};
        tableModel = new DefaultTableModel(getData(),title);
        table=new JTable(tableModel);

        table.setPreferredSize(new Dimension(800,600));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(250);
        columnModel.getColumn(4).setPreferredWidth(100);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table),BorderLayout.CENTER);

    }

    public String[][] getData(){

        DAO dao= DAOFactory.getExemplaireDAO();

        ArrayList<Exemplaire> exemplaires=dao.getList();
        String[][] data=new String[exemplaires.size()][5];
        for(int i=0;i<exemplaires.size();i++){
            data[i][0]=exemplaires.get(i).getNumero()+"";
            data[i][1]=exemplaires.get(i).getISBN()+"";
            data[i][2]=exemplaires.get(i).getEtat()+"";
            data[i][3]=exemplaires.get(i).getDateAchat()+"";
            if(exemplaires.get(i).isDispo()){
                data[i][4]="Oui";
            }else{
                data[i][4]="Non";
            }


        }
        return  data;
    }
    public void refresh(){
        tableModel.fireTableDataChanged();
    }
}