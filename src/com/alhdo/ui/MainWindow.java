package com.alhdo.ui;

import com.alhdo.entity.Adherent;
import com.alhdo.entity.Emprunt;
import com.alhdo.entity.Exemplaire;
import com.alhdo.entity.Livre;
import com.alhdo.ui.dialog.*;
import com.alhdo.util.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

/*
 * Created by Castro Alhdo on 5/2/16.
 * File created af 1:03 PM
 * _____________________________________
 * < Don't copy my code without my chmod >
 * ------------------------------------
 * \   ^__^
 * \  (oo)\_______
 *    (__)\       )\/\
 *        ||----w |
 *        ||     ||
 */
public class MainWindow extends JFrame implements ActionListener,DialogLivre.OnRegisterClickListener,
        DialogExemplaire.OnExemplaireClickListener,DialogEmprunt.OnEmpruntDoneListener,
        DialogAdherent.OnAdherentAddListener,AdherentPane.OnModifierClick,LivrePane.OnLivreModifDone,EmpruntPane.OnEmpruntClick{

    private JToolBar toolBar=new JToolBar();
    private JSplitPane splitPane;

    //Jutton toolbar
    private JButton addAdhe=new JButton(new ImageIcon(getClass().getResource("images/user.png")));
    private JButton addBook = new JButton(new ImageIcon(getClass().getResource("images/book.png")));
    private JButton addExemp = new JButton(new ImageIcon(getClass().getResource("images/exemplaire.png")));
    private JButton addEmprunt = new JButton(new ImageIcon(getClass().getResource("images/emprunt.png")));

    private JTextField search=new JTextField(5);
    private JComboBox searchCombo = new JComboBox();
    private JButton searchButton= new JButton("Rechercher");


    ImageIcon bg;
    //Menu
    JMenuItem adherentMenu,livresMenu,exemplaireMenu,empreuntMenu,ouvrirMenu,preferenceMenu;
    private JMenuBar menuBar=new JMenuBar();
    JMenu files=new JMenu("File");
    JMenu ajouter=new JMenu("Ajouter");
    JMenu edition=new JMenu("Edition");
    JMenu preferences=new JMenu("Preferences");
    JMenu help=new JMenu("Aide");


    //Menu Item

    JMenuItem itemQuit=new JMenuItem("Quitter");

    private static final String[] comboS={"Adherent","Livres","Exemplaire","Emprunt"};


    //Jmenu Item Help
    JMenuItem itemApropos=new JMenuItem("Apropos");
    JMenuItem itemHelp=new JMenuItem("Aide");

    LeftPane pan2;
    AdherentPane pan;

    JPanel mainJPanel=new JPanel();

    public MainWindow(){
        super("Bibliotheque");
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension d=tk.getScreenSize();

        int hScreen=(int)d.getHeight();
        int lScreen=(int)d.getWidth();
        //Creation de deux JPANEL
         pan=new AdherentPane(this);
        pan.setMinimumSize(new Dimension(800,600));
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.add(pan);
        JPanel pna=new JPanel();
      bg=   new ImageIcon(getClass().getResource("images/BG.jpg"));

         pan2=new LeftPane();
        pan2.setMinimumSize(new Dimension(220,600));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pna,mainJPanel);


        initInfoPane("");

      //  System.out.println(getClass().getResource("user.png").toString()
       // );
        setSize((int)(lScreen*0.6),(int)(hScreen*0.8));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initToolbar();
        initMenu();

        this.getContentPane().add(splitPane,BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        g.drawImage(bg.getImage(),0,0,null);
    }

    public void initMenu(){
        ouvrirMenu = new JMenuItem("Ouvrir");
        adherentMenu=new JMenuItem("Adherent");
        livresMenu = new JMenuItem("Livre");
        exemplaireMenu = new JMenuItem("Exemplaire");
        empreuntMenu = new JMenuItem("Emprunt");

        JMenuItem adherentMenu1=new JMenuItem("Adherent");
                    adherentMenu1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            launchDialogAdherent();
                        }
                    });
        JMenuItem livresMenu1 = new JMenuItem("Livre");
                    livresMenu1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            launchDialogLivre();
                        }
                    });
        JMenuItem exemplaireMenu1 = new JMenuItem("Exemplaire");

                    exemplaireMenu1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            launchExemplaireDialog();
                        }
                    });
        JMenuItem empreuntMenu1 = new JMenuItem("Emprunt");

                    empreuntMenu1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            launchEmpruntDialog();
                        }
                    });

        itemQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        itemApropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane about=new JOptionPane();
                String message = "+++++++++++++++++++ Bibiliotheque ++++++++++++++++++++";
                       message +="\n++++++++++++++++++       V1.0      +++++++++++++++++++";
                       message +="\n++++++++++++++++++     Creer par   +++++++++++++++++++";
                       message +="\n++++++++++++ Projet fin de session L2 ++++++++++++++++";
                       message +="\n++++++++++++++++++       JAVA      +++++++++++++++++++\n\n\n";
//                       message +="* \t OSIAS Alessandro\n";
//                       message +="* \t LOUIS Rolensky\n";

                message +="* \t JOASSAINT Scoch Nico\n";
                message +="* \t Augustin WoodJerry\n";
                message +="* \t WILLIAM Fabrice \t  \n";
                about.showMessageDialog(null,message,"Apropos",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        preferenceMenu = new JMenuItem("Preferences");

        preferenceMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogPreference preference=new DialogPreference(MainWindow.this);
            }
        });

        //Action Lister
        adherentMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchDialogAdherent();
            }
        });


        //Menu add
        this.ajouter.add(adherentMenu1);
        this.ajouter.add(livresMenu1);
        this.ajouter.add(exemplaireMenu1);
        this.ajouter.add(empreuntMenu1);

        //Menu Edition
        this.edition.add(adherentMenu);
        this.edition.add(livresMenu);
        this.edition.add(exemplaireMenu);
        this.edition.addSeparator();
        this.edition.add(empreuntMenu);

        this.edition.setMnemonic('E');

        //Menu File
        this.files.add(ouvrirMenu);
        this.files.add(ajouter);
        this.files.addSeparator();
        this.files.add(itemQuit);

        this.files.setMnemonic('F');





        //Menu Aide
        help.add(itemApropos);
        help.add(itemHelp);

        this.help.setMnemonic('A');

        //Preferences menu
        preferences.add(preferenceMenu);

        menuBar.add(files);
        menuBar.add(edition);
       // menuBar.add(preferences);
        menuBar.add(help);


        this.setJMenuBar(menuBar);
    }
    public void initInfoPane(String str){
        JPanel panel=null;
        if(str.equals("") || str.equals("Adherent")){
            Log.i("Init "+str);
            panel = new AdherentPane(this);
        }else if(str.equals("Livres")){
            Log.i("Init "+str);
            panel = new LivrePane(this);
        }else if(str.equals("Exemplaire")){
            Log.i("Init "+str);
            panel = new ExemplairePane();
        }else if(str.equals("Emprunt")){
            Log.i("Init "+str);
            panel = new EmpruntPane(this);
        }
//        if(!Log.v()){
//            JOptionPane errorInput = new JOptionPane();
//            errorInput.showMessageDialog(null,"WARING Invalid licence please contact me \n (509) 3792-9793","Ou panse w k bon sou mw",JOptionPane.ERROR_MESSAGE);
//            System.exit(0);
//        }

        mainJPanel.removeAll();
        mainJPanel.add(panel);

        mainJPanel.repaint();
        mainJPanel.revalidate();


    }
    public void searchPane(String str,String section){
        JPanel panel=null;
        if(str.equals("") || str.equals("Adherent")){
            Log.i("Init "+str);
            panel = new AdherentPane(section);
        }else if(str.equals("Livres")){
            Log.i("Init "+str);
            panel = new LivrePane(this);
        }else if(str.equals("Exemplaire")){
            Log.i("Init "+str);
            panel = new ExemplairePane();
        }else if(str.equals("Emprunt")){
            Log.i("Init "+str);
            panel = new EmpruntPane(this);
        }

        mainJPanel.removeAll();
        mainJPanel.add(panel);
        mainJPanel.repaint();
        mainJPanel.revalidate();


    }
    public void initToolbar(){

        for(int i=0;i<4;i++){
            searchCombo.addItem(comboS[i]);
        }
        searchCombo.addActionListener(this);
        addAdhe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               launchDialogAdherent();
            }
        });
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               launchDialogLivre();
            }
        });
        addExemp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               launchExemplaireDialog();
            }
        });

        addEmprunt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchEmpruntDialog();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!search.getText().equals("")){
                    String searchWord=search.getText();
                    String section = (String)searchCombo.getSelectedItem();
                    switch (section){
                        case "Adherent":{
                            searchPane(section,searchWord);
                        }break;
                    }
                }
            }
        });
        this.toolBar.add(search);
        this.toolBar.add(searchCombo);
        this.toolBar.add(searchButton);
        this.toolBar.add(addAdhe);
        this.toolBar.add(addBook);
        this.toolBar.add(addExemp);
        this.toolBar.add(addEmprunt);

        this.add(toolBar,BorderLayout.NORTH);
    }


    private void launchDialogAdherent(){
        DialogAdherent dialog=new DialogAdherent(MainWindow.this);
    }
    private void launchDialogLivre(){
        DialogLivre livre=new DialogLivre(MainWindow.this);
    }
    private void launchExemplaireDialog(){
        DialogExemplaire exemplaire=new DialogExemplaire(MainWindow.this);
    }
    private void launchEmpruntDialog(){
        DialogEmprunt emprunt = new DialogEmprunt(MainWindow.this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb=(JComboBox)e.getSource();
        String selection=cb.getSelectedItem().toString();
        initInfoPane(selection);
        Log.i("Selected : "+selection);
    }

    @Override
    public void onRegisterClickListener(Livre livre) {
        initInfoPane("Livres");
        Log.d("Register Clicked");
    }

    @Override
    public void onExemplaireClickListener(Exemplaire e) {

    }

    @Override
    public void onEmpruntDoneListener(Emprunt emprunt) {

    }

    @Override
    public void onAdherentAddListener(Adherent adherent) {
        initInfoPane("Adherent");
        Log.d("Register Adherent Clicked");
    }


    @Override
    public void OnModifClickListener(Adherent adhrerent) {
        new DialogAdherent(MainWindow.this,adhrerent);
    }

    @Override
    public void onLivreModifDone(Livre livre) {
        new DialogLivre(MainWindow.this,livre);
    }

    @Override
    public void OnEmpruntClickListener(Emprunt emprunt) {
        new DialogueRecuperer(MainWindow.this,emprunt);
    }
}
