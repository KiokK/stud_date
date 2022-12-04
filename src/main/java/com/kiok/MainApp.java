package com.kiok;

import com.kiok.Panels.Login;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

/**
 * Класс, с которого запускается приложение
 * @author Кихтенко О.Ю. 10702120
 */
@SpringBootApplication
public class MainApp {
    /** Хранит контекст приложения */
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {

        ctx = new SpringApplicationBuilder(MainApp.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            Login lg = ctx.getBean(Login.class);
        });
    }
}