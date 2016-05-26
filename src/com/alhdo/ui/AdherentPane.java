package com.alhdo.ui;

import com.alhdo.dao.AdherentDAO;
import com.alhdo.database.BiblioConnection;
import com.alhdo.entity.Adherent;
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
 * Created by Castro Alhdo on 5/2/16.
 * File created af 4:58 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class AdherentPane extends JPanel{
    JTable table;
    DefaultTableModel tableModel;
    AdherentDAO dao= new AdherentDAO(BiblioConnection.getInstance());
    JPopupMenu popupMenu;
    OnModifierClick modifierClick;
    ImageIcon bg;
    public AdherentPane(MainWindow m){

        initPopUpMenu();
        modifierClick=(OnModifierClick)m;
        String title[]={"Code","Nom","Prenom","Telephone","Adresse"};
        tableModel = new DefaultTableModel(getData(),title);
            table=new JTable(tableModel);
            table.setComponentPopupMenu(popupMenu);
        bg=   new ImageIcon(getClass().getResource("images/BG.jpg"));

        table.setPreferredSize(new Dimension(800,600));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(250);
        columnModel.getColumn(4).setPreferredWidth(250);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table),BorderLayout.CENTER);

    }
    public AdherentPane(JFrame parent){

        initPopUpMenu();
        modifierClick=(OnModifierClick)parent;
        String title[]={"Code","Nom","Prenom","Telephone","Adresse"};
        tableModel = new DefaultTableModel(getData(),title);
        table=new JTable(tableModel);
        table.setComponentPopupMenu(popupMenu);

        table.setPreferredSize(new Dimension(800,600));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
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
        JMenuItem modifier = new JMenuItem("Modifier");
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
                String nom=(String) table.getValueAt(row,1);
                String prenom=(String) table.getValueAt(row,2);
                String telephone=(String) table.getValueAt(row,3);
                String adresse=(String) table.getValueAt(row,4);

                Adherent adherent=new Adherent(code,nom,prenom,telephone,adresse);
                Info.showAdherentInfo(adherent);
                int coloum = table.columnAtPoint(e.getPoint());
                Log.d("Mouse pressed at row "+row+ " and column "+coloum);
            }
        });
        effacer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row =  table.getSelectedRow();
                int code =Integer.parseInt((String) table.getValueAt(row,0));
                String nom=(String) table.getValueAt(row,1);
                String prenom=(String) table.getValueAt(row,2);
                String telephone=(String) table.getValueAt(row,3);
                String adresse=(String) table.getValueAt(row,4);

                Adherent adherent=new Adherent(code,nom,prenom,telephone,adresse);

                dao.delete(adherent);
                refresh();
            }
        });
        modifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row =  table.getSelectedRow();
                int code =Integer.parseInt((String) table.getValueAt(row,0));
                String nom=(String) table.getValueAt(row,1);
                String prenom=(String) table.getValueAt(row,2);
                String telephone=(String) table.getValueAt(row,3);
                String adresse=(String) table.getValueAt(row,4);

                Adherent adherent=new Adherent(code,nom,prenom,telephone,adresse);

                modifierClick.OnModifClickListener(adherent);
            }
        });
    }

    public AdherentPane(String search){
        String title[]={"Code","Nom","Prenom","Telephone","Adresse"};
        tableModel = new DefaultTableModel(getData(search),title);
        table=new JTable(tableModel);

        table.setPreferredSize(new Dimension(800,600));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(250);
        columnModel.getColumn(4).setPreferredWidth(250);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table),BorderLayout.CENTER);

    }
    @Override
    public void paintComponents(Graphics g) {
        g.drawImage(bg.getImage(),0,0,null);
    }

    private String[][] getData(String search) {

        ArrayList<Adherent> adherents=dao.rechercher(search);
        String[][] data=new String[adherents.size()][5];
        for(int i=0;i<adherents.size();i++){
            data[i][0]=adherents.get(i).getCode()+"";
            data[i][1]=adherents.get(i).getNom()+"";
            data[i][2]=adherents.get(i).getPrenom()+"";
            data[i][3]=adherents.get(i).getTelephone()+"";
            data[i][4]=adherents.get(i).getAdresse()+"";
        }
        return  data;
    }

    public String[][] getData(){

       AdherentDAO dao= new AdherentDAO(BiblioConnection.getInstance());

        ArrayList<Adherent> adherents=dao.getList();
        String[][] data=new String[adherents.size()][5];
        for(int i=0;i<adherents.size();i++){
            data[i][0]=adherents.get(i).getCode()+"";
            data[i][1]=adherents.get(i).getNom()+"";
            data[i][2]=adherents.get(i).getPrenom()+"";
            data[i][3]=adherents.get(i).getTelephone()+"";
            data[i][4]=adherents.get(i).getAdresse()+"";
        }
        return  data;
    }
    public void refresh(){
     tableModel.fireTableDataChanged();
    }

    public interface OnModifierClick{
        public void OnModifClickListener(Adherent adhrerent);
    }
}
