package com.kiok.Panels;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Класс панели информации о приложении и разработчике
 * @author Кихтенко О.Ю. 10702120
 */
public class HomePage extends JPanel{
    /** Картина с информацией */
    private JLabel backgroundImage;

    /** Конструктор создания панели */
    public HomePage() {

        setLayout(null);
        backgroundImage = new JLabel();
        backgroundImage.setBounds(0, 0,
                AdminPage.W_FRAME - AdminPage.INSETS.left - AdminPage.INSETS.right,
                AdminPage.H_FRAME - AdminPage.INSETS.bottom - AdminPage.INSETS.top);

        backgroundImage.setIcon(new ImageIcon(getClass().getResource("/icons/background_image.jpg")));

        add(backgroundImage);
    }

    /**
     * Переопределенный метод для возвращения названия панели
     * @return строковое представление названия
     */
    @Override
    public String toString() {
        return "О программе";
    }




}