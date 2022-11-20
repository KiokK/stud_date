package com.kiok.Panels;
import com.kiok.MainApp;
import com.kiok.Panels.newPanel.NewGroupPanel;
import com.kiok.Panels.newPanel.NewLessonsPanel;
import com.kiok.Panels.newPanel.NewStudentPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//@Component
public class AdminPage extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;

    //private ConfigurableApplicationContext applicationContext;
    public static final short WAGE = 100;

    public static final String NAME = "Registration System";

    /**
     * AdminPanel window width
     */
    public static int W_FRAME = 960;

    /**
     * AdminPanel window height
     */
    public static int H_FRAME = 2 * W_FRAME / 3;

    /**
     * frame edge
     */
    public static Insets INSETS;

    private JMenuBar menuBar_menubar;
    private JMenu homePage_menu, record_menu, view_menu, bill_menu, system_menu;
    private JMenuItem addStudent_item, addGroup_item, addLesson_item, viewRecord_item;
    private JMenuItem billEmployer_item, billWorker_item;
    private JMenuItem settings_item, reset_item, logout_item;
    private HomePage homePage;
    private ArrayList<JPanel> components = new ArrayList<>();
    /**
     * Текущий компонент
     */
    private int currentComponent;

    public AdminPage() {
        this(0);
    }

    public AdminPage(int component) {

        super("Registration System");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\icons\\admin_user.png"));
        setSize(W_FRAME, H_FRAME);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setLocation(getX() - 40, getY() - 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        this.currentComponent = component;

        INSETS = getInsets();

        GUI();

    }


    private void GUI() {

        createMenus();
        createComponents();
        init();

    }


    /**
     * Метод создания верхнего меню
     */
    private void createMenus() {

        menuBar_menubar = new JMenuBar();

        homePage_menu = new JMenu("Главная");
        homePage_menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentComponent = 0;
                init();

            }
        });

        record_menu = new JMenu("Запись");
        view_menu = new JMenu("View");
        bill_menu = new JMenu("Bill");
        system_menu = new JMenu("System");

        addGroup_item = new JMenuItem("Новая группа");
        record_menu.add(addGroup_item);
        addGroup_item.addActionListener(this);

        addLesson_item = new JMenuItem("Новый урок");
        record_menu.add(addLesson_item);
        addLesson_item.addActionListener(this);

        addStudent_item = new JMenuItem("Новый студент");
        record_menu.add(addStudent_item);
        addStudent_item.addActionListener(this);

        viewRecord_item = new JMenuItem("View record");
        view_menu.add(viewRecord_item);
        viewRecord_item.addActionListener(this);

        billWorker_item = new JMenuItem("Worker payment");
        bill_menu.add(billWorker_item);
        billWorker_item.addActionListener(this);

        billEmployer_item = new JMenuItem("Employer payment");
        bill_menu.add(billEmployer_item);
        billEmployer_item.addActionListener(this);

        settings_item = new JMenuItem("Settings");
        system_menu.add(settings_item);
        settings_item.addActionListener(this);

        reset_item = new JMenuItem("Reset");
        system_menu.add(reset_item);
        reset_item.addActionListener(this);

        logout_item = new JMenuItem("Logout");
        system_menu.add(logout_item);
        logout_item.addActionListener(this);

        menuBar_menubar.add(homePage_menu);
        menuBar_menubar.add(record_menu);
        menuBar_menubar.add(view_menu);
        menuBar_menubar.add(bill_menu);
        menuBar_menubar.add(system_menu);


        setJMenuBar(menuBar_menubar);

    }


    private void createComponents() {

        components.add(new HomePage());
        components.add(MainApp.ctx.getBean(NewGroupPanel.class));
        System.out.println("ere:"  + MainApp.ctx);
        System.out.println("ere:"  + MainApp.ctx.getBean(NewLessonsPanel.class));
        components.add(MainApp.ctx.getBean(NewLessonsPanel.class));
        components.add(MainApp.ctx.getBean(NewStudentPanel.class));
        //components.add(new NewStudentPanel());
        //components.add(new ViewRecord());
        //components.add(new WorkerPayment());
        //components.add(new EmployerPayment());

    }


    private void init() {

        setContentPane(components.get(currentComponent));
        this.setTitle(NAME + " - " + components.get(currentComponent).toString());
        this.revalidate();
        this.repaint();

    }

    /**
     * Происходит при нажатии на элементы меню верхней панели
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if( ((JMenuItem)e.getSource()).getText().equals(addGroup_item.getText())) {

            if(currentComponent == 1) {
                components.set(currentComponent, new NewGroupPanel());
            } else {
                currentComponent = 1;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(addLesson_item.getText())) {

            if(currentComponent == 2) {
                components.set(currentComponent, new NewLessonsPanel());
            } else {
                currentComponent = 2;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(addStudent_item.getText())) {
            if(currentComponent == 3) {
                components.set(currentComponent, new NewStudentPanel());
            } else {
                currentComponent = 3;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(viewRecord_item.getText())) {

            if(currentComponent == 4) {
                components.set(currentComponent, new ViewRecord());
            } else {
                currentComponent = 4;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(billWorker_item.getText())) {

            if(currentComponent == 5) {
                components.set(currentComponent, new WorkerPayment());
            } else {
                currentComponent = 5;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(billEmployer_item.getText())) {

            if(currentComponent == 6) {
                components.set(currentComponent, new EmployerPayment());
            } else {
                currentComponent = 6;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(settings_item.getText())) {

            // Settings pane .. .
            JOptionPane.showMessageDialog(this, "Not available");
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(reset_item.getText())) {

            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    AdminPage.this.dispose();
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    new AdminPage(currentComponent);
                }
            });
        }
        if( ((JMenuItem)e.getSource()).getText().equals(logout_item.getText())) {

            this.dispose();
            return;
        }

    }

}