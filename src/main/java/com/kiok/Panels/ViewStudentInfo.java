package com.kiok.Panels;
import com.kiok.Models.Group;
import com.kiok.Models.Lesson;
import com.kiok.Models.Student;
import com.kiok.service.GroupService;
import com.kiok.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

@org.springframework.stereotype.Component
public class ViewStudentInfo extends JPanel{

    private static final long serialVersionUID = 1L;
    @Autowired
    private StudentService studentService;

    private GroupService groupService;
    /**
     * x position of the table
     */
    private final int TX = 5;

    /**
     * y position of the table
     */
    private final int TY = 5;

    /**
     * width of the table
     */
    private final int TW = 500;

    /**
     * height of the table
     */
    private final int TH = 423;

    /**
     * space
     */
    private final int SPACE = 80;

    private final String[] column_array = {"№", "Ф.И.О.", "Номер зачетки", "тел."};
    private String[][] data_2array;
    private String[][] group_array;
    private JButton detail_button;
    private JScrollPane record_scroll;
    private JLabel chooseGroup_label;
    private JComboBox<String> group_comboBox;
    private int tableSelectedRow = -1;

    private Group thisGroup;

    @Autowired
    public ViewStudentInfo(GroupService groupService) {
        this.groupService = groupService;

        setLayout(null);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 16);
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        data_2array = getData();

        record_scroll = new JScrollPane(createTable(data_2array, column_array));
        record_scroll.setBounds(TX, TY, TW * 2 - 70, TH);
        add(record_scroll);

        //Кнопка подробной информации о студенте
        detail_button = new JButton("Подробная информация");
        detail_button.setBounds(record_scroll.getX(), record_scroll.getY() + TH, record_scroll.getWidth(), 24);
        detail_button.setFocusPainted(false);
        detail_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //если строка выбрана
                if (((JTable) record_scroll.getViewport().getComponent(0)).getSelectedRow() != -1) {

                    if (true) {

                        tableSelectedRow = ((JTable) record_scroll.getViewport().getComponent(0)).getSelectedRow();
                        //JTable selectedT = ((JTable) record_scroll.getViewport().getComponent(0));
                        List<Student> students = thisGroup.getStudentList();
                        //String employer_id = ViewStudentInfo.this.nameConvertToId("employer", (String) ((JTable) record_scroll.getViewport().getComponent(0)).getValueAt(tableSelectedRow, 1));
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
                                newStudent.setCreditBook( creditBook.getText());
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


            }
        });
        add(detail_button);


        chooseGroup_label = new JLabel("Выбор группы");
        chooseGroup_label.setForeground(new Color(76, 76, 76));
        chooseGroup_label.setBounds(TX + TW/7, TY + TH + SPACE, TW / 4, 24);
        add(chooseGroup_label);


        group_array = new String[0][0];
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
                    data_2array = getData();
                    record_scroll.getViewport().add(createTable(data_2array, column_array));
                }
            }
        });
        add(group_comboBox);

    }



    private Component createTable(String[][] tableData, String[] tableColumn) {

        JTable tab = new JTable(tableData, tableColumn) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
            DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();

            {
                renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
                //renderLeft.setHorizontalAlignment(SwingConstants.LEFT);
            }

            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
				/*if(column == 1 || column == 2)
					return renderLeft;*/
                return renderCenter;
            }
        };
        tab.setRowHeight(25);
        tab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        if(tableColumn.length == 4) {

            tab.getColumnModel().getColumn(0).setPreferredWidth(18);
            tab.getColumnModel().getColumn(0).setPreferredWidth(150);
            tab.getColumnModel().getColumn(0).setPreferredWidth(60);
            tab.getColumnModel().getColumn(0).setPreferredWidth(30);

        }

        return tab;

    }


    public JTable setColumnWidth(JTable table, int ...column) {

        for(int i = 0; i < table.getColumnCount() && i < column.length; i++)
            table.getColumnModel().getColumn(i).setPreferredWidth(column[i]);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        return table;
    }


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


private boolean phoneNumberControl(String phoneNumber) {

    if(phoneNumber.equals("")) {
        return false;
    } else if( (phoneNumber.charAt(0) == '0' && phoneNumber.length() == 11)
            || (phoneNumber.charAt(0) != '0' && phoneNumber.length() == 10)
            || (phoneNumber.charAt(0) == '+' && phoneNumber.length() == 13)) {

        return true;

    }

    return false;

}
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

    @Override
    public String toString() {
        return "Просмотр студентов";
    }


}
