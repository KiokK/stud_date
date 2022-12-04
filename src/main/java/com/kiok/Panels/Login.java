package com.kiok.Panels;
import com.kiok.MainApp;
import com.kiok.Panels.newPanel.NewStudentPanel;
import com.kiok.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Класс создания окна с графическим интерфейсом для пользователя при входе в систему
 * @author Кихтенко О.Ю. 10702120
 */
@Component
public class Login extends JFrame{
    /**  Внедряем зависимость для работы с бд (таблицы админ)  */
    @Autowired
    private AdminService adminService;

    /** login window width */
    public static final int W_FRAME = 540;

    /** login window height */
    public static final int H_FRAME = 360;

    /** Поле ошибочного сообщения  */
    private final String errorText = "Ошибочный логин или пароль";

    /** Контент страницы, содержит все элементы пользовательского интерфейса */
    private JPanel contentPane;
    /** Кнопка для проверки логина */
    private JButton button_login;
    /** Переменные для отображения поля логина и ошибочного текста в GUI */
    private JLabel label_username, label_errorText;
    /** Поле для ввода имени пользователя при авторизации */
    private JTextField textField_username;
    /** Поле для ввода пароля пользователя при авторизации */
    private JPasswordField passwordField_password;
    /** Вспомогательная переменная для настройки отступов */
    private Insets insets;


    /** Конструктор создания окна */
    public Login() {

        super("Вход");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\icons\\Login_user_24.png"));
        setResizable(false);//изменяемость размера
        setLayout(null);//менеджер расположения чтобы узнать, какой менеджер им используется в данный момент
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setLocation(getX() - 80, getY() - 80);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        insets = this.getInsets();

        GUI();//метод создания графического интерфейса

    }

    /**
     * "Графический пользовательский интерфейс"
     * Метод содержит создание интерфейса окна
     */
    private void GUI() {

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBounds(insets.left, insets.top, W_FRAME - insets.left - insets.right,
                H_FRAME - insets.bottom - insets.top);

        //создание подписи поля для ввода имени пользователя
        label_username = new JLabel("Логин");
        label_username.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_username.setBounds(120, 140, 70, 20);
        contentPane.add(label_username);

        //создание подписи поля для ввода пароля пользователя
        JLabel label_password = new JLabel("Пароль");
        label_password.setFont(label_username.getFont());
        label_password.setBounds(label_username.getX(), label_username.getY() + 40,
                label_username.getWidth(), label_username.getHeight());
        contentPane.add(label_password);

        //создание поля для имени пользователя
        textField_username = new JTextField();
        textField_username.setBounds(label_username.getX() + label_username.getWidth() + 30,
                label_username.getY(), 120, label_username.getHeight());
        textField_username.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField_password.requestFocus();
            }
        });
        contentPane.add(textField_username);

        //добавление и настройка поля пароля
        passwordField_password = new JPasswordField();
        passwordField_password.setBounds(textField_username.getX(), label_password.getY(),
                120, label_password.getHeight());
        passwordField_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_login.doClick();
            }
        });
        contentPane.add(passwordField_password);

        //добавление кнопки входа(логина)
        addLoginButton();

        //добавление картинки на панель
        JLabel label_icon = new JLabel(new ImageIcon("src\\icons\\Login_user_72.png"));
        label_icon.setBounds(textField_username.getX() + 20,
                            textField_username.getY() - 100, 72, 72);
        contentPane.add(label_icon);

        //добавление поля для сообщения об ошибке
        label_errorText = new JLabel();
        label_errorText.setForeground(Color.RED);
        label_errorText.setBounds(button_login.getX() - 45, button_login.getY() + 30,
                170, 30);
        label_errorText.setFont(new Font("Tahoma", Font.PLAIN + Font.BOLD, 11));
        contentPane.add(label_errorText);

        setContentPane(contentPane);

    }

    /**
     * Создает кнопку и добавляет ее к контенту окна пользователя
     */
    private void addLoginButton(){
        button_login = new JButton("Войти");
        button_login.setBounds(textField_username.getX() + 20,
                label_username.getY() + 80, 80, 22);
        button_login.setFocusPainted(false);
        button_login.addActionListener(new ActionListener() {
            /**
             * Переопределенный метод для авторизации пользователя по кнопке "Войти"
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //проверяем на пустоту
                if(textField_username.getText().equals("") ||
                   String.valueOf(passwordField_password.getPassword()).equals("")) {
                    label_errorText.setText(errorText);
                } else {

                    label_errorText.setText("");//текст ошибки - пустая строка
                    //проверяем пароль и логин в базе данных
                    if( adminService.verifyLogin(textField_username.getText(),
                            String.valueOf(passwordField_password.getPassword())) != null ) {

                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                Login.this.dispose();//освобождение ресурсов окна, связанных с данным Java объектом.
                                new AdminPage();//создаем и автоматически запускаем новое поле админ. панели
                            }
                        });

                    } else {
                        label_errorText.setText(errorText);//устанавливаем текст ошибки
                    }

                }

            }
        });
        contentPane.add(button_login);
    }


}