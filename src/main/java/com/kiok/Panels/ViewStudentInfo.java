package com.kiok.Panels;
import com.kiok.Models.Group;
import com.kiok.Models.Student;
import com.kiok.service.GroupService;
import com.kiok.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Date;
import java.util.*;

/**
 * Класс панели списков студентов по выбранной группе
 * @author Кихтенко О.Ю. 10702120
 */
@org.springframework.stereotype.Component
public class ViewStudentInfo extends JPanel{

    /**  Внедряем зависимость для работы с бд (таблицы студентов)  */
    @Autowired
    private StudentService studentService;
    /**  Внедряем зависимость для работы с бд (таблицы группы студентов)  */
    private GroupService groupService;
    /** x позиция таблицы */
    private final int TX = 5;

    /** y позиция таблицы */
    private final int TY = 5;

    /** ширина таблицы */
    private final int TW = 500;

    /** высота таблицы */
    private final int TH = 423;

    /** отступы */
    private final int SPACE = 80;

    /** Элементы подписи колонок таблицы */
    private final String[] column_array = {"№", "Ф.И.О.", "Номер зачетки", "тел."};
    /** Данные для хаполнения таблицы, соответствуют колонкам {@link #column_array} */
    private String[][] tableStudentData;
    /** Кнопка детельной информации о студенте */
    private JButton detail_button;
    /** Панель таблицы списка студентов */
    private JScrollPane record_scroll_table;
    /** Подпись для элемента выбора группы студентов {@link #group_comboBox}*/
    private JLabel chooseGroup_label;
    /** Элемент выбора группы студентов*/
    private JComboBox<String> group_comboBox;
    /** Текущая выбранная строка таблицы */
    private int tableSelectedRow = -1;
    /** Выбранная группа */
    private Group thisGroup;

    /**
     * Конструктор создания панели просмотра информации о студентах
     * @param groupService внедряем зависимость для работы с БД
     */
    @Autowired
    public ViewStudentInfo(GroupService groupService) {
        this.groupService = groupService;

        setLayout(null);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 16);
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        tableStudentData = getData();

        record_scroll_table = new JScrollPane(createTable(tableStudentData, column_array));
        record_scroll_table.setBounds(TX, TY, TW * 2 - 70, TH);
        add(record_scroll_table);

        //Кнопка подробной информации о студенте
        detail_button = new JButton("Подробная информация");
        detail_button.setBounds(record_scroll_table.getX(), record_scroll_table.getY() + TH, record_scroll_table.getWidth(), 24);
        detail_button.setFocusPainted(false);
        detail_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //если строка выбрана
                if (((JTable) record_scroll_table.getViewport().getComponent(0)).getSelectedRow() != -1) {

                    tableSelectedRow = ((JTable) record_scroll_table.getViewport().getComponent(0)).getSelectedRow();

                     List<Student> students = thisGroup.getStudentList();

                    Student thisStudent = students.get(tableSelectedRow);
                    JTextField name = new JTextField(thisStudent.getName());
                    JTextField surname = new JTextField(thisStudent.getSurname());
                    JTextField dateOfBirth = new JTextField(String.valueOf(thisStudent.getDateOfBirth()));
                    JTextField creditBook = new JTextField(thisStudent.getCreditBook());
                    JTextField phoneNumber = new JTextField(thisStudent.getPhoneNumber());
                    JTextField address = new JTextField(thisStudent.getAddress());
                    JTextField studentLeader = new JTextField(String.valueOf(thisStudent.isStudentLeader()));
                    JTextField groupNumber = new JTextField(thisStudent.getGroup().getGroupNumber());
                    Object[] message = {
                            "Имя:", name,
                            "Фамилия:", surname,
                            "Дата рождения:", dateOfBirth,
                            "№ зачетки:", creditBook,
                            "Тел.:", phoneNumber,
                            "Адресс:", address,
                            "Староста(да/нет):", studentLeader,
                            "Группа:", groupNumber
                    };

                    int option = JOptionPane.showConfirmDialog(null, message, "Редактирование студента", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        Group newGroup = groupService.findByGroupNumber(groupNumber.getText());

                        if (name.getText().replaceAll("\\s+", "").equals("") ||
                                surname.getText().replaceAll("\\s+", "").equals("") ||
                                groupNumber.getText().replaceAll("\\s+", "").equals("") ||
                                newGroup == null ||
                                (studentLeader.getText().toLowerCase().equals("да") & studentLeader.getText().toLowerCase().equals("нет")) ||
                                !dateOfBirthControl(dateOfBirth.getText()) ||
                                creditBook.getText().replaceAll("\\s+", "").equals("") ||
                                address.getText().replaceAll("\\s+", "").equals("") ||
                                !phoneNumberControl(phoneNumber.getText())) {
                            //не правильные данные
                            JOptionPane.showMessageDialog(null,
                                    "Пожалуйста введите информацию корректно\nполя не могут быть пустыми\nПример ввода даты: YYYY-MM-DD\nУбедитесь, что введенная группа существует", "ОШИБКА", JOptionPane.ERROR_MESSAGE);

                        } else {
                            //сохраняем

                            Student newStudent = new Student();
                            newStudent.setId(students.get(tableSelectedRow).getId());
                            newStudent.setName(name.getText().toUpperCase());
                            newStudent.setSurname(surname.getText().toUpperCase());
                            newStudent.setDateOfBirth(Date.valueOf(dateOfBirth.getText()));
                            newStudent.setCreditBook(creditBook.getText());
                            newStudent.setPhoneNumber(phoneNumber.getText());
                            newStudent.setAddress(address.getText());
                            newStudent.setStudentLeader((studentLeader.getText().equals("да")) ? true : false);
                            newStudent.setGroup(newGroup);

                            if (!thisGroup.getGroupNumber().equals(newGroup.getGroupNumber())) {
                                //переводим студента в новую группу
                                groupService.deleteStudent(thisGroup, thisStudent);
                                newStudent.setGroup(newGroup);
                                thisStudent.copy(newStudent);
                                newStudent = thisStudent;
                                groupService.addStudent(newGroup, newStudent);
                                //newStudent = studentService.save(newStudent);
                            } else {
                                newStudent.setGroup(thisGroup);
                                thisStudent.copy(newStudent);
                                newStudent = studentService.save(thisStudent);
                            }
                            JOptionPane.showMessageDialog(null, "Сохранено");

                        }
                    }


                }


            }
        });
        add(detail_button);

        //подпись для выпадающего списка групп
        chooseGroup_label = new JLabel("Выбор группы");
        chooseGroup_label.setForeground(new Color(76, 76, 76));
        chooseGroup_label.setBounds(TX + TW/7, TY + TH + SPACE, TW / 4, 24);
        add(chooseGroup_label);

        //выпадающий список групп
        ArrayList<String> groupNumbers_arrayList = groupService.findAllNumbers();
        group_comboBox = new JComboBox<String>(groupNumbers_arrayList.toArray(new String[groupNumbers_arrayList.size()]));//(listConvertToArray(new ArrayList<String[]>()/*DataBase.getData("employer")*/, 1, 2));
        group_comboBox.setBounds(chooseGroup_label.getX(), chooseGroup_label.getY() +
                chooseGroup_label.getHeight(), chooseGroup_label.getWidth(), 24);
        group_comboBox.setSelectedItem(null);
        group_comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (group_comboBox.getSelectedItem() != null) {
                    //загружаем информацию о группе из бд
                    thisGroup = groupService.findByGroupNumber(String.valueOf(group_comboBox.getSelectedItem()));
                    tableStudentData = getData();
                    record_scroll_table.getViewport().add(createTable(tableStudentData, column_array));
                }
            }
        });
        group_comboBox.setMaximumRowCount(5);//более 5 элементов - появится полоса прокрутки
        add(group_comboBox);
        JButton refreshBtn = new JButton(new ImageIcon("src\\icons\\btn_refresh.png"));
        refreshBtn.setToolTipText("Обновить списки групп");
        refreshBtn.setBounds(chooseGroup_label.getX() - 46, chooseGroup_label.getY() +
                chooseGroup_label.getHeight(), 45, 24);

        //для обновления списка групп
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> groupNumbers_arrayList = groupService.findAllNumbers();
                group_comboBox.setModel(new DefaultComboBoxModel<String>(
                        groupNumbers_arrayList.toArray(new String[groupNumbers_arrayList.size()])
                ));

            }
        });
        add(refreshBtn);

    }


    /**
     * Метод заполняет таблицу данными
     * @param tableData массив данных таблицы
     * @param tableColumn названия колонок
     * @return созданная таблица <code>JTable</code> в формате <code>Component</code>
     */
    private Component createTable(String[][] tableData, String[] tableColumn) {

        //создаем таблицу
        JTable tab = new JTable(tableData, tableColumn) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
            {
                renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return renderCenter;
            }
        };
        tab.setRowHeight(25);
        tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return tab;

    }


    /**
     * Метод для получения данных из БД в виде двумерного массива
     * @return таблица данных с колонками указанными в {@link #column_array}
     */
    public String[][] getData(){
        if (thisGroup == null)
            return new String[][] {};
        List<Student> students = thisGroup.getStudentList();
        if (students == null)
            return new String[][] {};
        String[][] temp = new String[students.size()][];
        //№ ФИО #зачетки тел
        for (int i = 0; i < students.size(); i++)
            temp[i] = (new String[] {
                    String.valueOf(i + 1),
                    students.get(i).getSurname() + " " + students.get(i).getName(),
                    students.get(i).getCreditBook(),
                    students.get(i).getPhoneNumber()});
        return temp;
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
    /**
     * Переопределенный метод для возвращения названия панели
     * @return строковое представление названия
     */
    @Override
    public String toString() {
        return "Просмотр студентов";
    }


}
