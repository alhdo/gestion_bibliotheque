package com.alhdo.ui;

import com.alhdo.dao.AdherentDAO;
import com.alhdo.dao.DAO;
import com.alhdo.dao.DAOFactory;
import com.alhdo.dao.EmpruntDAO;
import com.alhdo.database.BiblioConnection;
import com.alhdo.entity.Adherent;
import com.alhdo.entity.Emprunt;
import com.alhdo.entity.Exemplaire;
import com.alhdo.util.DateUtil;
import com.alhdo.util.Log;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*
 * Created by Castro Alhdo on 5/4/16.
 * File created af 2:06 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class EmpruntPane  extends JPanel {
    JTable table;
    DefaultTableModel tableModel;
    JPopupMenu popupMenu;
    OnEmpruntClick empruntClick;
    EmpruntDAO empruntDAO = new EmpruntDAO(BiblioConnection.getInstance());
    public EmpruntPane(MainWindow m){
        initPopUpMenu();
        empruntClick=(OnEmpruntClick)m;
        String title[]={"Numero T","Adherent","Exemplaire","Date Emprunt","Date Retour"};
        tableModel = new DefaultTableModel(getData(),title);
        table=new JTable(tableModel);
        table.setComponentPopupMenu(popupMenu);
        table.setPreferredSize(new Dimension(800,600));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(250);
        columnModel.getColumn(4).setPreferredWidth(250);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table),BorderLayout.CENTER);

    }

    public void initPopUpMenu(){

        popupMenu=new JPopupMenu();
        JMenuItem view=new JMenuItem("Voir");
        JMenuItem modifier = new JMenuItem("Recuperer");
        JMenuItem effacer = new JMenuItem("Effacer");
        popupMenu.add(view);
        popupMenu.add(modifier);
        popupMenu.add(effacer);
        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), table));
                        if (rowAtPoint > -1) {
                            table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int coloum = table.columnAtPoint(e.getPoint());
                Log.d("Mouse clicked at row "+row+ " and column "+coloum);
            }


            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row =  table.getSelectedRow();
                int code =Integer.parseInt((String) table.getValueAt(row,0));
                String adh=(String) table.getValueAt(row,1);

                String examplaire=(String) table.getValueAt(row,2);
                String dateEmprunt=(String) table.getValueAt(row,3);
                String dateRetour=(String) table.getValueAt(row,4);

                Emprunt emprunt=empruntDAO.getEmpruntByNumero(code);
                Info.showEmpruntInfo(emprunt);
                int coloum = table.columnAtPoint(e.getPoint());
                Log.d("Mouse pressed at row "+row+ " and column "+coloum);
            }
        });
        effacer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row =  table.getSelectedRow();
                int numeroT =Integer.parseInt((String) table.getValueAt(row,0));
                String numero=(String) table.getValueAt(row,1);
                String adh=(String) table.getValueAt(row,2);


                Emprunt emprunt=empruntDAO.getEmpruntByNumero(numeroT);
                //Adherent adherent=new Adherent(code,nom,prenom,telephone,adresse);

               // dao.delete(adherent);
                empruntDAO.delete(emprunt);
                refresh();
            }
        });
        modifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row =  table.getSelectedRow();
                int numeroT =Integer.parseInt((String) table.getValueAt(row,0));


                Emprunt emprunt=empruntDAO.getEmpruntByNumero(numeroT);

              //  modifierClick.OnModifClickListener(adherent);
                empruntClick.OnEmpruntClickListener(emprunt);
            }
        });
    }
    public String[][] getData(){

        DAO dao= DAOFactory.getEmpruntDAO();
        AdherentDAO adherentDAO=new AdherentDAO(BiblioConnection.getInstance());

        ArrayList<Emprunt> emprunts=dao.getList();
        String[][] data=new String[emprunts.size()][5];
        for(int i=0;i<emprunts.size();i++){
            data[i][0]=emprunts.get(i).getNumeroTransaction()+"";
            data[i][1]=emprunts.get(i).getAdherent().getNom()+" "+emprunts.get(i).getAdherent().getPrenom();
            data[i][2]=emprunts.get(i).getExemplaireNumero()+"";
            data[i][3]=emprunts.get(i).getDateEmprunt()+"";
            data[i][4]=emprunts.get(i).getDateRetour()+"";

        }
        return  data;
    }
    public void refresh(){
        tableModel.fireTableDataChanged();
    }
    public interface OnEmpruntClick{
        public void OnEmpruntClickListener(Emprunt emprunt);
    }
}