package com.kiok.Panels;
import com.kiok.MainApp;
import com.kiok.Panels.newPanel.NewGroupPanel;
import com.kiok.Panels.newPanel.NewLessonsPanel;
import com.kiok.Panels.newPanel.NewStudentPanel;

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
import javax.swing.JPanel;

/**
 * Класс главного окна, которое будет содержать все панели с графическим интерфейсом
 * @author Кихтенко О.Ю. 10702120
 */
public class AdminPage extends JFrame implements ActionListener{
    /** Переменная для подписи в заголовке окна при изменении рабочей области */
    public static final String NAME = "Страница ";

    /**
     * AdminPanel window width
     */
    public static int W_FRAME = 960;

    /**
     * AdminPanel window height
     */
    public static int H_FRAME = 2 * W_FRAME / 3;

    /** frame edge (для настройки отступов) */
    public static Insets INSETS;

    /** Строка меню */
    private JMenuBar menuBar_menubar;
    /** Часть меню для создания новых записей */
    private JMenu record_menu;
    /** Часть меню для просмотра списков */
    private JMenu view_menu;
    /** Часть меню для системных команд (выход, перезагрузка) */
    private JMenu system_menu;
    /** Элемент меню - новый студент - для создания новых записей {@link #record_menu} */
    private JMenuItem addStudent_item;
    /** Элемент меню - новый групп - для создания новых записей {@link #record_menu} */
    private JMenuItem addGroup_item;
    /** Элемент меню - новый урок - для создания новых записей {@link #record_menu} */
    private JMenuItem addLesson_item;
    /** Элемент меню - просмотр списков - для создания новых записей {@link #view_menu} */
    private JMenuItem viewRecord_item;
    /** Элемент меню - для системных команд - для перезагрузки системы {@link #system_menu} */
    private JMenuItem reset_item;
    /** Элемент меню - для системных команд - для завершения программы {@link #system_menu} */
    private JMenuItem logout_item;
    private ArrayList<JPanel> components = new ArrayList<>();
    /** Текущий компонент  */
    private int currentComponent = 0;

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

    /**
     * "Графический пользовательский интерфейс"
     * Метод содержит создание интерфейса окна
     */
    private void GUI() {

        createMenus();
        createComponents();
        init();

    }


    /** Метод создания меню для GUI*/
    private void createMenus() {

        menuBar_menubar = new JMenuBar();

        JMenu homePage_menu = new JMenu("О программе");
        homePage_menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentComponent = 0;
                init();

            }
        });

        record_menu = new JMenu("Запись");
        view_menu = new JMenu("Списки");
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

        viewRecord_item = new JMenuItem("Просмотр студентов");
        view_menu.add(viewRecord_item);
        viewRecord_item.addActionListener(this);

        reset_item = new JMenuItem("Reset");
        system_menu.add(reset_item);
        reset_item.addActionListener(this);

        logout_item = new JMenuItem("Logout");
        system_menu.add(logout_item);
        logout_item.addActionListener(this);

        menuBar_menubar.add(homePage_menu);
        menuBar_menubar.add(record_menu);
        menuBar_menubar.add(view_menu);
        menuBar_menubar.add(system_menu);


        setJMenuBar(menuBar_menubar);

    }

    /** Метод создания элементов меню для GUI*/
    private void createComponents() {

        components.add(new HomePage());
        components.add(MainApp.ctx.getBean(NewGroupPanel.class));
        components.add(MainApp.ctx.getBean(NewLessonsPanel.class));
        components.add(MainApp.ctx.getBean(NewStudentPanel.class));
        components.add(MainApp.ctx.getBean(ViewStudentInfo.class));

    }

    /** Метод инициализации текущего компонента окна */
    private void init() {

        setContentPane(components.get(currentComponent));
        this.setTitle(NAME + " - " + components.get(currentComponent).toString());
        this.revalidate();
        this.repaint();

    }

    /**
     * Метод переопределён для отображения новых окон пользователю
     * в соответствии с  выбором
     * @param e нажатый элемент верхнего меню
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if( ((JMenuItem)e.getSource()).getText().equals(addGroup_item.getText())) {

            if(currentComponent == 1) {
                components.set(currentComponent, MainApp.ctx.getBean(NewGroupPanel.class));
            } else {
                currentComponent = 1;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(addLesson_item.getText())) {

            if(currentComponent == 2) {
                components.set(currentComponent, MainApp.ctx.getBean(NewLessonsPanel.class));
            } else {
                currentComponent = 2;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(addStudent_item.getText())) {
            if(currentComponent == 3) {
                components.set(currentComponent, MainApp.ctx.getBean(NewStudentPanel.class));
            } else {
                currentComponent = 3;
            }

            init();
            return;
        }
        if( ((JMenuItem)e.getSource()).getText().equals(viewRecord_item.getText())) {

            if(currentComponent == 4) {
                components.set(currentComponent, MainApp.ctx.getBean(ViewStudentInfo.class));
            } else {
                currentComponent = 4;
            }

            init();
            return;
        }

        if( ((JMenuItem)e.getSource()).getText().equals(reset_item.getText())) {

            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    AdminPage.this.dispose();
                    try {
                        Thread.sleep(50);
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