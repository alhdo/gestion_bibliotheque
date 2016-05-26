package com.alhdo.ui;

import com.alhdo.dao.AdherentDAO;
import com.alhdo.dao.DAO;
import com.alhdo.dao.LivreDAO;
import com.alhdo.database.BiblioConnection;
import com.alhdo.entity.Adherent;
import com.alhdo.entity.Livre;
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
 * Created by Castro Alhdo on 5/3/16.
 * File created af 6:29 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class LivrePane extends JPanel {
    JTable table;
    DefaultTableModel tableModel;

    JPopupMenu popupMenu;
    DAO dao= new LivreDAO(BiblioConnection.getInstance());

    OnLivreModifDone mCallBack;

    public LivrePane(MainWindow m){
        initPopUpMenu();
        mCallBack = (OnLivreModifDone)m;
        String title[]={"ISBN","Titre","Genre","Pages"};
        tableModel = new DefaultTableModel(getData(),title);
        table=new JTable(tableModel);
        table.setComponentPopupMenu(popupMenu);

        table.setPreferredSize(new Dimension(800,600));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(250);

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
                Log.d("Mouse clicked at rw "+row+ " and column "+coloum);
            }


            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row =  table.getSelectedRow();
                String isbn =(String) table.getValueAt(row,0);
                String titre=(String) table.getValueAt(row,1);
                String genre=(String) table.getValueAt(row,2);
                int nbrPages=Integer.parseInt((String) table.getValueAt(row,3));



                Livre livre=new Livre(isbn,titre,genre,nbrPages);
                Info.showLivreInfo(livre);
                int coloum = table.columnAtPoint(e.getPoint());
                Log.d("Mouse pressed at rw "+row+ " and column "+coloum);
            }
        });
        effacer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row =  table.getSelectedRow();
                String isbn =(String) table.getValueAt(row,0);
                String titre=(String) table.getValueAt(row,1);
                String genre=(String) table.getValueAt(row,2);
                int nbrPages=Integer.parseInt((String) table.getValueAt(row,3));



                Livre livre=new Livre(isbn,titre,genre,nbrPages);

                dao.delete(livre);
                refresh();
            }
        });
        modifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row =  table.getSelectedRow();
                String isbn =(String) table.getValueAt(row,0);
                String titre=(String) table.getValueAt(row,1);
                String genre=(String) table.getValueAt(row,2);
                int nbrPages=Integer.parseInt((String) table.getValueAt(row,3));



                Livre livre=new Livre(isbn,titre,genre,nbrPages);



                mCallBack.onLivreModifDone(livre);
            }
        });
    }

    public String[][] getData(){



        ArrayList<Livre> livres=dao.getList();
        String[][] data=new String[livres.size()][4];
        for(int i=0;i<livres.size();i++){
            data[i][0]=livres.get(i).getIBSN()+"";
            data[i][1]=livres.get(i).getTitre()+"";
            data[i][2]=livres.get(i).getGenre()+"";
            data[i][3]=livres.get(i).getNbrPages()+"";

        }
        return  data;
    }
    public void refresh(){
        tableModel.fireTableDataChanged();
    }

    public interface OnLivreModifDone{
        public void onLivreModifDone(Livre livre);
    }
}
