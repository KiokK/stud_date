package com.kiok.Panels.newPanel;
import com.kiok.MainApp;
import com.kiok.Models.Group;
import com.kiok.service.AdminService;
import com.kiok.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

@Component
public class NewGroupPanel extends JPanel implements ActionListener, FocusListener{

    private static final long serialVersionUID = 1L;
    @Autowired
    private GroupService groupService ;//= MainApp.ctx.getBean(GroupService.class);

    @Autowired
    private AdminService adminService;
    /**
     * y position of label
     */
    private final int LY = 250;

    /**
     * x position of label
     */
    private final int LX = 300;

    /**
     * width of the text field
     */
    private final int TW = 150;

    /**
     * height of the text field
     */
    private final int TH = 25;

    /**
     * width of the label
     */
    private final int LW = 85;

    /**
     * height of the label;
     */
    private final int LH = 25;

    /**
     * vertical spacing of the label
     */
    private final int LVS = 40;

    /**
     * horizontal spacing of the label
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

    private JLabel number_label, surname_label, phoneNumber_label, image_label;
    private JTextField number_text, surname_text, phoneNumber_text;
    private JButton save_button;
    //private JPanel saveButtonBack_panel;


    public NewGroupPanel() {

        setLayout(null);

        image_label = new JLabel(new ImageIcon("src\\icons\\new_worker.png"));
        image_label.setBounds(427, 80, 128, 128);
        add(image_label);

        number_label = new JLabel("№");
        number_label.setBounds(LX, LY, LW, LH);
       // System.out.println(LX + "\n" + LW + "\n" + AdminPage.INSETS.left + "\n" + image_label.getX() + "\n" + (name_label.getX() + name_label.getWidth()));
        add(number_label);

        number_text = new JTextField();
        number_text.setBounds(LX + number_label.getWidth() + LHS, number_label.getY(), TW, TH);
        number_text.addFocusListener(this);
        number_text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!number_text.getText().equals(""))
                    surname_text.requestFocus();
            }
        });
        add(number_text);

//        surname_label = new JLabel("Surname");
//        surname_label.setBounds(LX, name_label.getY() + LVS, LW, LH);
//        add(surname_label);
//
//        surname_text = new JTextField();
//        surname_text.setBounds(LX + surname_label.getWidth() + LHS, surname_label.getY(), TW, TH);
//        surname_text.addFocusListener(this);
//        surname_text.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(!surname_text.getText().equals(""))
//                    phoneNumber_text.requestFocus();
//            }
//        });
//        add(surname_text);
//
//        phoneNumber_label = new JLabel("Phone Number");
//        phoneNumber_label.setBounds(LX, surname_label.getY() + LVS, LW, LH);
//        add(phoneNumber_label);
//
//        phoneNumber_text = new JTextField();
//        phoneNumber_text.setBounds(LX + phoneNumber_label.getWidth() + LHS, phoneNumber_label.getY(),TW, TH);
//        phoneNumber_text.addFocusListener(this);
//        phoneNumber_text.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                save_button.doClick();
//            }
//        });
//        add(phoneNumber_text);

        //saveButtonBack_panel = new JPanel();
        //saveButtonBack_panel.setLayout(null);
        //saveButtonBack_panel.setBounds(phoneNumber_text.getX() + ((TW - BW) / 2), phoneNumber_text.getY() + LH + 20, BW, BH);
        //saveButtonBack_panel.setBackground(Color.CYAN);

        save_button = new JButton("Сохранить");
        save_button.setBounds(0, 0, BW, BH);
        save_button.addActionListener(this);
        save_button.setBounds(number_text.getX() + ((TW - BW) / 2), number_text.getY() + LH + 20, BW, BH);
        save_button.setFocusPainted(false);

        //save_button.setContentAreaFilled(false);
        //save_button.setOpaque(false);
        //save_button.setBorder(new LineBorder(Color.GREEN, 2));

        //saveButtonBack_panel.add(save_button);

        add(save_button);

    }


    @Override
    public void focusGained(FocusEvent e) {

        ((JTextField) e.getSource()).setBorder(new LineBorder(Color.blue));

    }


    @Override
    public void focusLost(FocusEvent e) {

        Color color = Color.green;
        if( ((JTextField) e.getSource()).getText().replaceAll("\\s+", "").equals("")) {

            if (e.getSource() == number_text || e.getSource() == surname_text)
                color = Color.red;
            else
                color = Color.white;
        }
//        } else {
//            if(e.getSource() == phoneNumber_text) {
//                if( phoneNumberControl(phoneNumber_text.getText())) {
//
//                    color = Color.white;
//
//                } else {
//                    color = Color.red;
//                }
//            }
//        }

        ((JTextField) e.getSource()).setBorder(new LineBorder(color));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (groupService.findByGroupNumber(number_text.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Группа с таким номером уже существует");
            return;
        }
        if(number_text.getText().replaceAll("\\s+", "").equals("")
//                || surname_text.getText().replaceAll("\\s+", "").equals("")
//                || !phoneNumberControl(phoneNumber_text.getText())
        ) {

            JOptionPane.showMessageDialog(this, "Пожалуйста введите информацию", "ОШИБКА", JOptionPane.ERROR_MESSAGE);

        } else {

            JLabel numberLabel;//, surnameLabel, phoneNumberLabel;
            JTextField numberText;//, surnameText, phoneNumberText;

            numberLabel = new JLabel(number_label.getText());

            //surnameLabel = new JLabel(surname_label.getText());

            //phoneNumberLabel = new JLabel(phoneNumber_label.getText());

            numberText = new JTextField(number_text.getText().toUpperCase());
            numberText.setSize(80, 24);
            numberText.setEditable(false);

//            surnameText = new JTextField(surname_text.getText().toUpperCase());
//            surnameText.setSize(80, 24);
//            surnameText.setEditable(false);
//
//            phoneNumberText = new JTextField(phoneNumber_text.getText().toUpperCase());
//            phoneNumberText.setSize(80, 24);
//            phoneNumberText.setEditable(false);


            Object [] approvalForm = new Object[] {

                    numberLabel,
                    numberText,
//                    surnameLabel,
//                    surnameText,
//                    phoneNumberLabel,
//                    phoneNumberText

            };

            int result = JOptionPane.showOptionDialog(this, approvalForm, "Сохранить", JOptionPane.OK_OPTION, -1, new ImageIcon("src//icons//accounting_icon_1_32.png"), new Object[] {"Сохранить",  "Закрыть"}, "Закрыть");
            System.out.print(result);
            //SAVE = 0, CANCEL = 1
            if(result == 0) {
                try {
                    if (groupService.findByGroupNumber(number_text.getText()) != null) {
                        JOptionPane.showMessageDialog(this, "Группа с таким номером уже существует");
                        return;
                    }
                    groupService.save(new Group(number_text.getText()));

                    JOptionPane.showMessageDialog(this, "Сохранено");
                    clearPanel();
                }catch (Exception ex){

                    JOptionPane.showMessageDialog(this, "Не сохранено", "Ошибка БД", JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    }


//    private boolean phoneNumberControl(String phoneNumber) {
//
//        if(phoneNumber.equals("")) {
//            return true;
//        } else if( (phoneNumber.charAt(0) == '0' && phoneNumber.length() == 11)
//                || (phoneNumber.charAt(0) != '0' && phoneNumber.length() == 10)
//                || (phoneNumber.charAt(0) == '+' && phoneNumber.length() == 13)) {
//
//            return true;
//
//        }
//
//        return false;
//
//    }


    private void clearPanel() {

        number_text.setText("");
//        surname_text.setText("");
//        phoneNumber_text.setText("");

        number_text.setBorder(new LineBorder(Color.white));
//        surname_text.setBorder(new LineBorder(Color.white));
//        phoneNumber_text.setBorder(new LineBorder(Color.white));


    }


    @Override
    public String toString() {
        return "Новая группа";
    }

}
