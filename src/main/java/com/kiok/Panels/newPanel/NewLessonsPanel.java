package com.kiok.Panels.newPanel;
//import com.kiok.DB.DataBase;
import com.kiok.Models.Group;
import com.kiok.Models.Lesson;
import com.kiok.service.GroupService;
import com.kiok.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@Component
public class NewLessonsPanel extends JPanel implements ActionListener, ListSelectionListener{


    private static final long serialVersionUID = 2L;

    //@Autowired
    private GroupService groupService;// = MainApp.ctx.getBean(GroupService.class);

    @Autowired
    private LessonService lessonService;

    /**
     * x, y position of the List box
     */
    private final int LBX = 185, LBY = 60;

    /**
     * width, height, space of the list box
     */
    private final int LBW = 230, LBH = 160, LBS = 400;

    /**
     * width and height button
     */
    private final int BW = 120, BH = 30;

    /**
     * space of button
     */
    private final int BS = 70;


    private JList<String> selectionBox_list;
    private DefaultListModel<String> selection_model;
    private JTextField lessonName_text, teacherName_text;
    private JTextField numberTime_text;
    private JScrollPane selection_scroll;
    private JButton info_button, remove_button, save_button;

    private JRadioButton lk_rbt, pr_rbt, lr_rbt, sem_rbt, zach_rbt, dif_rbt, ekz_rbt;

    private ButtonGroup buttonGroup_typeOfClass, buttonGroup_typeOfExam;

    private JLabel selectionBox_label, group_label, lessonName_label, teacherName_label;
    private JLabel selectionGroup_label, selectedGroup_label;
    private JLabel numberTime_label;

    private ArrayList<String> groupNumbers_arrayList;

    private JComboBox<String> group_comboBox;
    private DefaultComboBoxModel<String> group_model;

    private Set<Lesson> lessons_arr;
    private Group thisGroup;

    /**
     * Конструктор создания объекта панели и графического интерфейса
     * @param groupService внедряется из ApplicationContext при срабатывании конструктора
     */
    @Autowired
    public NewLessonsPanel(GroupService groupService) {
        this.groupService = groupService;

        setLayout(null);

        groupNumbers_arrayList = groupService.findAllNumbers();
        selection_model = new DefaultListModel<>();
      //  group_model = new DefaultComboBoxModel<>();

        //for(int i = 0; i < groupNumbers_arrayList.size(); i++)
        //    group_model.addElement(groupNumbers_arrayList.get(i));

        selectionBox_list = new JList<String>();
        selectionBox_list.setFixedCellHeight(24);
        selectionBox_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionBox_list.addListSelectionListener(this);

        selection_scroll = new JScrollPane(selectionBox_list);
        selection_scroll.setBounds(LBX, LBY, LBW, LBH);
        add(selection_scroll);

        selectionBox_label = new JLabel("Список предметов");
        selectionBox_label.setBounds(LBX + (LBW - 100) / 2, LBY - BH - 30, LBW, 50);
        add(selectionBox_label);

        group_label = new JLabel("Выбрать группу");
        group_label.setBounds(LBX, LBY + LBH + 25, LBW, 24);
        add(group_label);

        //comboBox для выбора группы
        group_comboBox = new JComboBox<String>(groupNumbers_arrayList.toArray(new String[groupNumbers_arrayList.size()]));
        group_comboBox.setSelectedItem(null);
        group_comboBox.setBounds(LBX, group_label.getY() + group_label.getHeight(), LBW, 24);
        group_comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedGroup_label.setText((String) group_comboBox.getSelectedItem());
                ArrayList<String> data_model = new ArrayList<>();
                if (group_comboBox.getSelectedItem() != null) {
                    thisGroup = groupService.findByGroupNumber((String) group_comboBox.getSelectedItem());
                    lessons_arr = thisGroup.getLessons();
                    for (Lesson el : lessons_arr)
                        data_model.add(el.getLessonName()+" ("+el.getTypeOfLesson()+")");

                    selectionBox_list.setListData(data_model.toArray(new String[data_model.size()]));
                }
            }
        });
        add(group_comboBox);

        //выбранная группа
        selectionGroup_label = new JLabel("Выбранная группа");
        selectionGroup_label.setBounds(group_label.getX() + LBS, group_label.getY(), LBW, 24);
        add(selectionGroup_label);

        selectedGroup_label = new JLabel((String) group_comboBox.getSelectedItem());
        selectedGroup_label.setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 16));
        selectedGroup_label.setForeground(Color.gray);
        selectedGroup_label.setBounds(group_comboBox.getX() + LBS, group_comboBox.getY(), LBW, 24);
        add(selectedGroup_label);

        //Кнопка доп. информации о предмете
        info_button = new JButton("Инфо");
        info_button.setFocusPainted(false);
        info_button.setEnabled(false);
        info_button.setBackground(Color.gray);
        info_button.setBounds(LBX + LBW + (LBS - BW) / 2, LBY + (LBH - 4 * BH) / 2, BW, BH);
        info_button.addActionListener(this);
        add(info_button);

        //Кнопка для удаления предмета
        remove_button = new JButton("Удалить");
        remove_button.setFocusPainted(false);
        remove_button.setEnabled(false);
        remove_button.setBackground(Color.gray);
        remove_button.setBounds(LBX + LBW + (LBS - BW) / 2, info_button.getY() + BS, BW, BH);
        remove_button.addActionListener(this);
        add(remove_button);

        JLabel newLesson_label = new JLabel("___________________________Новый предмет___________________________");
        newLesson_label.setFont(selectedGroup_label.getFont());
        newLesson_label.setBounds(group_comboBox.getX(), group_comboBox.getY() + 60,
                650, BH);
        add(newLesson_label);

        //создание подписи поля для ввода названия предмета
        lessonName_label = new JLabel("Название");
        lessonName_label.setBounds(group_comboBox.getX(), newLesson_label.getY() + 60, 150, BH);
        add(lessonName_label);
        //создание поля для ввода названия предмета
        lessonName_text = new JTextField();
        lessonName_text.setBounds(lessonName_label.getX() + lessonName_label.getWidth(),
                newLesson_label.getY() + 60, 120, BH);
        add(lessonName_text);

        //создание подписи поля для ввода имени преподавателя
        teacherName_label = new JLabel("Преподаватель");
        teacherName_label.setBounds(group_comboBox.getX(), lessonName_text.getY() + 50,
                lessonName_label.getWidth(), BH);
        add(teacherName_label);
        //создание поля для ввода преподавателя
        teacherName_text = new JTextField();
        teacherName_text.setBounds(lessonName_label.getX() + lessonName_label.getWidth(),
                lessonName_text.getY() + 50, 120, BH);
        add(teacherName_text);

        //создание подписи поля для ввода
        numberTime_label = new JLabel("Кол. часов");
        numberTime_label.setBounds(group_comboBox.getX(), teacherName_text.getY() + 50,
                lessonName_label.getWidth(), BH);
        add(numberTime_label);
        //создание поля для ввода
        numberTime_text = new JTextField();
        numberTime_text.setBounds(lessonName_label.getX() + lessonName_label.getWidth(),
                teacherName_text.getY() + 50, 120, BH);
        add(numberTime_text);

        //Создание выбора типа занятия
        lk_rbt = new JRadioButton("ЛК.");
        lk_rbt.setBounds(lessonName_text.getX() + lessonName_text.getWidth() + 50,
                newLesson_label.getY() + 60, 60, BH);
        lk_rbt.setSelected(true);
        pr_rbt = new JRadioButton("ПР.");
        pr_rbt.setBounds(lk_rbt.getX() + lk_rbt.getWidth() + 10,
                newLesson_label.getY() + 60, 60, BH);
        lr_rbt = new JRadioButton("ЛР.");
        lr_rbt.setBounds(pr_rbt.getX() + pr_rbt.getWidth() + 10,
                newLesson_label.getY() + 60, 60, BH);
        sem_rbt = new JRadioButton("СЕМ.");
        sem_rbt.setBounds(lr_rbt.getX() + lr_rbt.getWidth() + 10,
                newLesson_label.getY() + 60, 60, BH);
        buttonGroup_typeOfClass = new ButtonGroup();
        buttonGroup_typeOfClass.add(lk_rbt);
        buttonGroup_typeOfClass.add(pr_rbt);
        buttonGroup_typeOfClass.add(lr_rbt);
        buttonGroup_typeOfClass.add(sem_rbt);
        add(lk_rbt); add(pr_rbt);
        add(lr_rbt); add(sem_rbt);

        //Создание выбора типа экзаменации оценки
        zach_rbt = new JRadioButton("зач.");
        zach_rbt.setBounds(lessonName_text.getX() + lessonName_text.getWidth() + 50,
                newLesson_label.getY() + 90, 60, BH);
        zach_rbt.setSelected(true);
        dif_rbt = new JRadioButton("диф.");
        dif_rbt.setBounds(lk_rbt.getX() + lk_rbt.getWidth() + 10,
                newLesson_label.getY() + 90, 60, BH);
        ekz_rbt = new JRadioButton("экз.");
        ekz_rbt.setBounds(pr_rbt.getX() + pr_rbt.getWidth() + 10,
                newLesson_label.getY() + 90, 60, BH);
        buttonGroup_typeOfExam = new ButtonGroup();
        buttonGroup_typeOfExam.add(zach_rbt);
        buttonGroup_typeOfExam.add(dif_rbt);
        buttonGroup_typeOfExam.add(ekz_rbt);
        add(zach_rbt); add(dif_rbt);
        add(ekz_rbt);

        //Кнопка для добавления нового урока
        save_button = new JButton("Добавить");
        save_button.setBounds(pr_rbt.getX(),
                newLesson_label.getY() + 150, 170, BH);
        save_button.setFocusPainted(false);
        save_button.setBackground(Color.CYAN);
        save_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //проверка на заполненность полей нового урока
                if(	lessonName_text.getText().equals("") ||
                    teacherName_text.getText().equals("") ||
                    numberTime_text.getText().equals("") ||
                    group_comboBox.getSelectedItem() == null) {

                    JOptionPane.showMessageDialog(null,
                            "Пожалуйста введите информацию корректно\nполя не могут быть пустыми\n Не забудьте выбрать группу", "ОШИБКА", JOptionPane.ERROR_MESSAGE);

                } else {
                    String text = "\n  Назв. предмета :\t" + lessonName_text.getText().toUpperCase() + "\n\n" +
                            "  Преподаватель : \t" + teacherName_text.getText().toUpperCase() + "\n\n" +
                            "  Количество часов : \t" + numberTime_text.getText().toUpperCase() + "\n\n" +
                            "  № группы : \t\t" + group_comboBox.getSelectedItem() + "\n\n" +
                            "  Тип аттестации : \t" + getSelectedButtonText(buttonGroup_typeOfExam) + "\n\n" +
                            "  Тип занятия : \t\t" + getSelectedButtonText(buttonGroup_typeOfClass) + "\n\n";
                    Object[] pane = {
                            new JLabel("Предмет будет сохранен"),
                            new JTextArea(text) {
                                public boolean isEditable() {
                                    return false;
                                };
                            }

                    };

                    //окно перепроверки перед сохранением
                    int result = JOptionPane.showOptionDialog(null, pane, "Данные", 1, 1,
                            new ImageIcon("src\\icons\\accounting_icon_1_32.png"), new Object[] {"Сохранить", "Закрыть"}, "Закрыть");

                    if(result == 0) { // 0 -> SAVE
                        Lesson saveLesson = lessonService.save(new Lesson(lessonName_text.getText(), teacherName_text.getText(),
                                getSelectedButtonText(buttonGroup_typeOfExam),
                                numberTime_text.getText(), getSelectedButtonText(buttonGroup_typeOfClass),
                                thisGroup));
                        thisGroup.addLesson(saveLesson);
                        groupService.save(thisGroup);
                        if(saveLesson != null) {

                            JOptionPane.showMessageDialog(null, "Сохранено");
                            clearPanel();
                        } else {

                            JOptionPane.showMessageDialog(null, "Не сохранено", "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }

            }
        });
        add(save_button);

    }

    /**
     * Метод возвращает название выбранной radioButton из группы
     * @param buttonGroup
     * @return название выбранного поля в формате <code>String</code>
     */
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }


    /**
     * Для обработки событий кнопки "Инфо" о предмете и "Удалить" предмет
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == info_button && selectionBox_list.getSelectedValue() != null) {
            //выводим информациюоб уроке
            String lessonText = (String) selectionBox_list.getSelectedValue();
            for (Lesson el : lessons_arr)
                if (lessonText.equals(el.getLessonName() + " (" + el.getTypeOfLesson() + ")")) {

                    JOptionPane.showMessageDialog(null,
                            "Преподаватель: " + el.getTeacherName() +
                            "\nТип аттестации: " + el.getTypeOnSession() +
                            "\nТип пары: " + el.getTypeOfLesson() +
                            "\nКол-во часов: " + el.getAmountOfHours(), el.getLessonName(), JOptionPane.OK_CANCEL_OPTION);
                    break;
                }

            info_button.setBackground(Color.gray);
            info_button.setEnabled(false);

        }
        if(e.getSource() == remove_button && selectionBox_list.getSelectedValue() != null) {
            //удаляем урок
            String lessonText = (String) selectionBox_list.getSelectedValue();
            for (Lesson el : lessons_arr)
                if (lessonText.equals(el.getLessonName() + " (" + el.getTypeOfLesson() + ")")) {
                    thisGroup = groupService.deleteLesson(el, thisGroup);
                    lessonService.delete(el);
                    JOptionPane.showMessageDialog(null,
                            "Предмет удален","УДАЛЕНИЕ" ,JOptionPane.OK_CANCEL_OPTION);
                    break;
                }
            //lessons_arr = thisGroup.getLessons();
            clearPanel();
            remove_button.setBackground(Color.gray);
            remove_button.setEnabled(false);
        }
    }



    /**
     * срабатывает при выборе элемента в selectionBox_list
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

        if(e.getSource() == selectionBox_list && selectionBox_list.getSelectedValue() != null) {
            //активируем кнопки информации и удаления
            remove_button.setBackground(Color.red);
            remove_button.setEnabled(true);
            info_button.setBackground(Color.green);
            info_button.setEnabled(true);
        }
    }



    private void clearPanel() {
        group_comboBox.setSelectedIndex(-1);
        selectionBox_list.setListData(new String[]{});
        selectedGroup_label.setText("");
    }


    @Override
    public String toString() {
        return "Новый урок";
    }

}
