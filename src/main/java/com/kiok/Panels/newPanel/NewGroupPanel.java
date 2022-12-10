package com.kiok.Panels.newPanel;

import com.kiok.Models.Group;
import com.kiok.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * Класс создания окна с графическим интерфейсом для создания новой группы
 * @author Кихтенко О.Ю. 10702120
 */
@Component
public class NewGroupPanel extends JPanel implements ActionListener{
    /**  Внедряем зависимость для работы с бд (таблицы группы студентов)  */
    @Autowired
    private GroupService groupService ;

    /** y позиция label */
    private final int LY = 250;

    /** x позиция label */
    private final int LX = 300;

    /** ширина text поля */
    private final int TW = 150;

    /** высота text поля */
    private final int TH = 25;

    /** ширина label */
    private final int LW = 85;

    /** высота label; */
    private final int LH = 25;

    /** горизонтальное расстояние label */
    private final int LHS = 30;

    /** ширина button */
    private final int BW = 160;

    /** высота button */
    private final int BH = 30;

    /** Подпись для номера группы */
    private JLabel number_label;
    /** Поле ввода номера группы */
    private JTextField number_text;
    /** Кнопка для сохранения данных в бд */
    private JButton save_button;
    /** Кнопка для узаления данных из бд */
    private JButton del_button;

    /** Конструктор создания панели */
    public NewGroupPanel() {

        setLayout(null);
        //Картинка для пользователя
        JLabel image_label = new JLabel(new ImageIcon(getClass().getResource("/icons/new_group.png")));
        image_label.setBounds(427, 80, 128, 128);
        add(image_label);

        //создаем подпись для поля ввода № группы
        number_label = new JLabel("№");
        number_label.setBounds(LX, LY, LW, LH);
        add(number_label);
        //создаем поле ввода № группы
        number_text = new JTextField();
        number_text.setBounds(LX + number_label.getWidth() + LHS, number_label.getY(), TW, TH);
        add(number_text);

        //создаем кнопку для сохранения данных
        save_button = new JButton("Сохранить");
        save_button.setBounds(0, 0, BW, BH);
        save_button.addActionListener(this);
        save_button.setBounds(number_text.getX() + ((TW - BW) / 2), number_text.getY() + LH + 20, BW, BH);
        save_button.setFocusPainted(false);
        add(save_button);

        //создаем кнопку для удаления данных
        del_button = new JButton("Удалить");
        del_button.setBounds(0, 0, BW, BH);
        del_button.addActionListener(new ActionListener() {
            /**
             * Метод переопределен для кнопки удаления группы из БД
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //проверяем есть ли уже группа с таким номером
                Group thisGroup = groupService.findByGroupNumber(number_text.getText());
                if (thisGroup != null) {
                    //получаем результат перепроверки: DELETE = 0, CANCEL = 1
                    int result = JOptionPane.showOptionDialog(null,
                            new Object[] {
                                new JLabel(number_label.getText()),
                                new JTextField(number_text.getText().toUpperCase()),
                             }, "Удалить", JOptionPane.OK_OPTION, -1,
                            new ImageIcon(getClass().getResource("/icons/accounting_icon_1_32.png")),
                            new Object[] {"Удалить",  "Закрыть"}, "Закрыть");

                    if (result == 1) return;
                    //удаляем группу
                    groupService.delete(thisGroup);
                    JOptionPane.showMessageDialog(null, "Группа удалена!");
                    clearPanel();
                }else
                    JOptionPane.showMessageDialog(null, "Группа с таким номером не существует");
            }
        });
        del_button.setBounds(number_text.getX() + ((TW - BW) / 2), number_text.getY() + LH + 65, BW, BH);
        del_button.setFocusPainted(false);
        add(del_button);
    }

    /**
     * Метод для сохранения данных в БД по кнопке
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //проверяем нет ли уже группы с таким номером
        if (groupService.findByGroupNumber(number_text.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Группа с таким номером уже существует");
            return;
        }
        //проверка на ввод пустоты
        if (number_text.getText().replaceAll("\\s+", "").equals("") ) {
            JOptionPane.showMessageDialog(this, "Пожалуйста введите информацию", "ОШИБКА", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Создаем диалоговое окно для получения повторногоподтвержения от пользователя
        JLabel numberLabel = new JLabel(number_label.getText());
        JTextField numberText = new JTextField(number_text.getText().toUpperCase());
        numberText.setSize(80, 24);
        numberText.setEditable(false);
        Object [] approvalForm = new Object[] {
                numberLabel,
                numberText,
        };
        //получаем результат перепроверки: SAVE = 0, CANCEL = 1
        int result = JOptionPane.showOptionDialog(this, approvalForm, "Сохранить", JOptionPane.OK_OPTION, -1, new ImageIcon(getClass().getResource("/icons/accounting_icon_1_32.png")), new Object[] {"Сохранить",  "Закрыть"}, "Закрыть");

        if(result == 0) {
            //сохраняем
            try {
                groupService.save(new Group(number_text.getText()));
                JOptionPane.showMessageDialog(this, "Сохранено");
                clearPanel();
            } catch (Exception ex) {
                //сохранить не получилось. Сообщаем об ошибке
                JOptionPane.showMessageDialog(this, "Не сохранено", "Ошибка БД", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    /**
     * Метод очищает поля, которые заполняет пользователь
     */
    private void clearPanel() {

        number_text.setText("");
        number_text.setBorder(new LineBorder(Color.white));
    }

    /**
     * Переопределенный метод для возвращения названия панели
     * @return строковое представление названия
     */
    @Override
    public String toString() {
        return "Новая группа";
    }

}
