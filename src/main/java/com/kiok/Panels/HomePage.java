package com.kiok.Panels;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage extends JPanel{

    private static final long serialVersionUID = 1L;

    private JLabel backgroundImage;

    public HomePage() {

        setLayout(null);
        backgroundImage = new JLabel();
        backgroundImage.setBounds(0, 0,
                AdminPage.W_FRAME - AdminPage.INSETS.left - AdminPage.INSETS.right,
                AdminPage.H_FRAME - AdminPage.INSETS.bottom - AdminPage.INSETS.top);

        backgroundImage.setIcon(new ImageIcon("src\\icons\\background_image.jpg"));

        add(backgroundImage);

        // Home page: only picture
        // but new features will come...

    }


    @Override
    public String toString() {
        return "Home Page";
    }




}