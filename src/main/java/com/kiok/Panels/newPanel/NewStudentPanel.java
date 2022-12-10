package com.kiok.Panels.newPanel;

import com.kiok.Models.Group;
import com.kiok.Models.Student;
import com.kiok.service.GroupService;
import com.kiok.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
/**
 * Класс создания панели с графическим интерфейсом для создания нового студента
 * @author Кихтенко О.Ю. 10702120
 */
@Component
public class NewStudentPanel extends JPanel implements FocusListener, ActionListener{

    /**  Внедряем зависимость для работы с бд (таблицы студентов)  */
    @Autowired
    private StudentService studentService;
    /**  Внедряем зависимость для работы с бд (таблицы группы студентов)  */
    @Autowired
    private GroupService groupService;

    /**
     * y position of label
     */
    private final int LY = 250;

    /**
     * x position of label
     */
    private final int LX = 300;

    /**
     * width of the Textfield
     */
    private final int TW = 150;

    /**
     * height of the TextField
     */
    private final int TH = 25;

    /**
     * width of the label
     */
    private final int LW = 115;

    /**
     * height of the label
     */
    private final int LH = 25;

    /**
     * vertical space of the label
     */
    private final int LVS = 40;

    /**
     * horizontal space of the label
     */
    private final int LHS = 30;

    /**
     * width of the button
     */
    private final int BW = 160;

    /**
     * height of the button
     */
    private final int BH = 30;
    /** Поля подписи для личных данных студента соответственно */
    private JLabel name_label, surname_label, groupNumber_label, phoneNumber_label,
        dateOfBirth_label, creditBook_label, address_label;
    /** Поля для ввода личных данных студента соответственно */
    private JTextField name_text, surname_text, groupNumber_text, phoneNumber_text,
            dateOfBirth_text, creditBook_text, address_text;

    /** Кнопка для сохранения личных данных студента соответственно */
    private JButton save_button;

    /** Метод создания и размещения полей {@link #name_label} и {@link #name_text} */
    private void nameFields(){
        name_label = new JLabel("Имя");
        name_label.setBounds(LX, LY, LW, LH);
        add(name_label);

        name_text = new JTextField();
        name_text.setBounds(name_label.getX() + LW + LHS, name_label.getY(), TW, TH);
        name_text.addFocusListener(this);
        name_text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(save_button != null) {
                    surname_text.requestFocus();
                }
            }
        });
        add(name_text);
    }

    /** Метод создания и размещения полей {@link #surname_label} и {@link #surname_text} */
    private void surNameFields(){
        surname_label = new JLabel("Фамилия");
        surname_label.setBounds(name_label.getX(), name_label.getY() + LVS, LW, LH);
        add(surname_label);

        surname_text = new JTextField();
        surname_text.setBounds(surname_label.getX() + LW + LHS, surname_label.getY(), TW, TH);
        surname_text.addFocusListener(this);
        surname_text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(save_button != null) {
                    groupNumber_text.requestFocus();
                }
            }
        });
        add(surname_text);
    }

    /** Метод создания и размещения полей {@link #groupNumber_label} и {@link #groupNumber_text} */
    private void groupNumberFields(){
        groupNumber_label = new JLabel("Группа");
        groupNumber_label.setBounds(surname_label.getX(), surname_label.getY() + LVS, LW, LH);
        add(groupNumber_label);

        groupNumber_text = new JTextField();
        groupNumber_text.setBounds(groupNumber_label.getX() + LW + LHS, groupNumber_label.getY(), TW, TH);
        groupNumber_text.addFocusListener(this);
        groupNumber_text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(save_button != null) {
                    creditBook_text.requestFocus();
                }
            }
        });
        add(groupNumber_text);
    }

    /** Метод создания и размещения полей {@link #dateOfBirth_label} и {@link #dateOfBirth_text} */
    private void dateOfBirthFields(){
        dateOfBirth_label = new JLabel("Дата рождения");
        dateOfBirth_label.setBounds(groupNumber_label.getX(), groupNumber_label.getY() + LVS, LW, LH);
        add(dateOfBirth_label);

        dateOfBirth_text = new JTextField();
        dateOfBirth_text.setBounds(dateOfBirth_label.getX() + LW + LHS, dateOfBirth_label.getY(), TW, TH);
        dateOfBirth_text.addFocusListener(this);
        dateOfBirth_text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(save_button != null) {
                    creditBook_text.requestFocus();
                }
            }
        });
        add(dateOfBirth_text);
    }

    /** Метод создания и размещения полей {@link #creditBook_label} и {@link #creditBook_text} */
    private void creditBookFields(){
        creditBook_label = new JLabel("№ зачетной книги");
        creditBook_label.setBounds(dateOfBirth_label.getX(), dateOfBirth_label.getY() + LVS, LW, LH);
        add(creditBook_label);

        creditBook_text = new JTextField();
        creditBook_text.setBounds(creditBook_label.getX() + LW + LHS, creditBook_label.getY(), TW, TH);
        creditBook_text.addFocusListener(this);
        creditBook_text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(save_button != null) {
                    address_text.requestFocus();
                }
            }
        });
        add(creditBook_text);
    }
    /** Метод создания и размещения полей {@link #address_label} и {@link #address_text} */
    private void addressFields(){
        address_label = new JLabel("Адресс");
        address_label.setBounds(creditBook_label.getX(), creditBook_label.getY() + LVS, LW, LH);
        add(address_label);

        address_text = new JTextField();
        address_text.setBounds(address_label.getX() + LW + LHS, address_label.getY(), TW, TH);
        address_text.addFocusListener(this);
        address_text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(save_button != null) {
                    dateOfBirth_text.requestFocus();
                }
            }
        });
        add(address_text);
    }

    /** Метод создания и размещения полей {@link #phoneNumber_label} и {@link #phoneNumber_text} */
    private void phoneNumberFields(){
        phoneNumber_label = new JLabel("Номер тел.");
        phoneNumber_label.setBounds(address_label.getX(), address_label.getY() + LVS, LW, LH);
        add(phoneNumber_label);

        phoneNumber_text = new JTextField();
        phoneNumber_text.setBounds(phoneNumber_label.getX() + LW + LHS, phoneNumber_label.getY(), TW, TH);
        phoneNumber_text.addFocusListener(this);
        phoneNumber_text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(save_button != null) {
                    save_button.doClick();
                }

            }
        });
        add(phoneNumber_text);
    }
    /** Конструктор создания панели GUI */
    public NewStudentPanel() {

        setLayout(null);
        //добавляем картинку
        JLabel image_label = new JLabel();
        image_label.setIcon(new ImageIcon(getClass().getResource("/icons/new_student.png")));
        image_label.setBounds(LX + 135, 40, 128, 130);
        add(image_label);
        //Добавляем подпись
        JLabel imageText_label = new JLabel("Новый студент");
        imageText_label.setBounds(425, 180, 150, 30);
        imageText_label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        add(imageText_label);

        //вызываем методы создания полей и подписей для ввода данных о студенте
        nameFields();
        surNameFields();
        groupNumberFields();
        dateOfBirthFields();
        creditBookFields();
        addressFields();
        phoneNumberFields();

        //создаем кнопку сохранения данных
        save_button = new JButton("Сохнанить");
        save_button.setBounds(phoneNumber_text.getX() + ((TW - BW) / 2), phoneNumber_text.getY() + LH + 20, BW, BH);
        save_button.setFocusPainted(false);
        save_button.addActionListener(this);
        add(save_button);

    }

    /**
     * Переопределяем метод оформления полей ввода информации:
     * добавляем подсветку
     * @param e the event to be processed
     */
    @Override
    public void focusGained(FocusEvent e) {
        ((JTextField)e.getSource()).setBorder(new LineBorder(Color.blue));

    }
    /**
     * Переопределяем метод оформления полей ввода информации:
     * добавляем красную подсветку, если информация не введена
     * @param e the event to be processed
     */
    @Override
    public void focusLost(FocusEvent e) {
        Color color = Color.green;
        if( ((JTextField) e.getSource()).getText().replaceAll("\\s+", "").equals("")) {

            color = Color.red;

        } else {
            if(e.getSource() == phoneNumber_text) {
                if( phoneNumberControl(phoneNumber_text.getText())) {
                    color = Color.white;
                } else {
                    color = Color.red;
                }
            }
        }

        ((JTextField) e.getSource()).setBorder(new LineBorder(color));

    }

    /**
     * Переопредененный метод для кнопки, по нажатию происходит сохранение данных
     * пользователя в бд
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        //проверка на воод пустоты или не корректных данных
        if(		name_text.getText().replaceAll("\\s+", "").equals("") ||
                surname_text.getText().replaceAll("\\s+", "").equals("") ||
                groupNumber_text.getText().replaceAll("\\s+", "").equals("") ||
                !dateOfBirthControl(dateOfBirth_text.getText()) ||
                creditBook_text.getText().replaceAll("\\s+", "").equals("") ||
                address_text.getText().replaceAll("\\s+", "").equals("") ||
                !phoneNumberControl(phoneNumber_text.getText()) ) {

            JOptionPane.showMessageDialog(this,
                    "Пожалуйста введите информацию корректно\nполя не могут быть пустыми\nПример ввода даты: YYYY-MM-DD", "ОШИБКА", JOptionPane.ERROR_MESSAGE);

        } else {
            //данные введены корректно -> будем сохранять
            //Для перепроверки данных создаем информационный блок
            String text = "\n  Имя :\t" + name_text.getText().toUpperCase() + "\n\n" +
                            "  Фамилия : \t" + surname_text.getText().toUpperCase() + "\n\n" +
                            "  Номер группы : \t" + groupNumber_text.getText().toUpperCase() + "\n\n" +
                            "  Дата рождения : \t" + dateOfBirth_text.getText() + "\n\n" +
                            "  № зачетной кн. : \t" + creditBook_text.getText() + "\n\n" +
                            "  Адресс : \t" + address_text.getText() + "\n\n" +
                            "  Номер тел. :   " + phoneNumber_text.getText().toUpperCase() + "\n";
            Object[] pane = {
                    new JLabel("Пользователь будет сохранен"),
                    new JTextArea(text) {
                        public boolean isEditable() {
                            return false;
                        };
                    }

            };
            //выводим информационный блок, чтобы пользователь перепроверил и подтвердил сохранение
            int result = JOptionPane.showOptionDialog(this, pane, "Данные", 1, 1,
                    new ImageIcon(getClass().getResource("/icons/accounting_icon_1_32.png")), new Object[] {"Сохранить", "Закрыть"}, "Закрыть");
            if (groupService.findByGroupNumber(groupNumber_text.getText()) == null) {
                Object[] group_error = {
                        new JLabel("Группы с таким номером не существует"),
                        new JTextArea(groupNumber_text.getText()) {
                            public boolean isEditable() {
                                return false;
                            }
                        }
                };
                //если группы с введённым номером не существует, то её можно создать автоматически
                int createGroupVal = JOptionPane.showOptionDialog(this, group_error, "Группы с таким номером не существует", 1, 1,
                        new ImageIcon(getClass().getResource("/icons/accounting_icon_1_32.png")), new Object[]{"Создать","Закрыть"}, "Закрыть");
                if (createGroupVal == 0){
                    groupService.save(new Group(groupNumber_text.getText()));
                }
            }
            // 0 -> SAVE
            // 1 -> CANCEL

            if(result == 0) {
                //сохраняем
                Group gr = groupService.findByGroupNumber(groupNumber_text.getText());
                Student saveStudent = studentService.save(new Student(name_text.getText().toUpperCase(), surname_text.getText().toUpperCase(),
                        Date.valueOf(dateOfBirth_text.getText()), creditBook_text.getText(), phoneNumber_text.getText().toUpperCase(),
                        address_text.getText(), false, gr));
                groupService.addStudent(gr, saveStudent);
                if(saveStudent != null) {

                    JOptionPane.showMessageDialog(this, "Сохранено");
                    clearPanel();
                } else {

                    JOptionPane.showMessageDialog(this, "Не сохранено", "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }

    /**
     * Метод для проверки номера телефона на корректность
     * @param phoneNumber строка номера телефона
     * @return true - если номер корректный, иначе - false
     */
    private boolean phoneNumberControl(String phoneNumber) {
        if(phoneNumber.equals(""))
            return false;
        if( (phoneNumber.charAt(0) == '0' && phoneNumber.length() == 11)
                || (phoneNumber.charAt(0) != '0' && phoneNumber.length() == 10)
                || (phoneNumber.charAt(0) == '+' && phoneNumber.length() == 13)) {

            return true;
        }

        return false;
    }
    /**
     * Метод для проверки номера даты рождения на корректность
     * @param dateOfBirth строка даты рождения
     * @return true - если номер корректный, иначе - false
     */
    private boolean dateOfBirthControl(String dateOfBirth) {

        if(dateOfBirth.equals(""))
            return false;
        try{
            Date.valueOf(dateOfBirth);
        }catch (IllegalArgumentException e){
            return false;
        }
        return true;

    }

    /** Метод очищает заполненные пользователем поля */
    private void clearPanel() {

        name_text.setText("");
        surname_text.setText("");
        groupNumber_text.setText("");
        phoneNumber_text.setText("");
        dateOfBirth_text.setText("");
        address_text.setText("");
        creditBook_text.setText("");

        name_text.setBorder(new LineBorder(Color.white));
        surname_text.setBorder(new LineBorder(Color.white));
        groupNumber_text.setBorder(new LineBorder(Color.white));
        phoneNumber_text.setBorder(new LineBorder(Color.white));
        dateOfBirth_text.setBorder(new LineBorder(Color.white));
        address_text.setBorder(new LineBorder(Color.white));
        creditBook_text.setBorder(new LineBorder(Color.white));
    }

    /**
     * Переопределенный метод для возвращения названия панели
     * @return строковое представление названия
     */
    @Override
    public String toString() {
        return "Новый студент";
    }


}