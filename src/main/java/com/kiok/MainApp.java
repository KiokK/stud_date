package com.kiok;

import com.kiok.Panels.Login;
import org.jboss.jandex.Main;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Класс, с которого запускается приложение
 * @author Кихтенко О.Ю. 10702120
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages={"com.kiok.dao","com.kiok.service", "com.kiok.Panels"})
@SpringBootApplication
//@ComponentScan({"com.kiok.Panels.newPanel", "com.kiok.dao", "com.kiok.Panels", "com.kiok.service"})
public class MainApp {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {

        ctx = new SpringApplicationBuilder(MainApp.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            Login lg = ctx.getBean(Login.class);
        });
        // с английского «очередь событий» это функция, которая (содержится в Java)
        // посылает задание текущего Thread-а на очередь к выполнению в главный Thread
//        EventQueue.invokeLater(new Runnable() {
//            //создаем новый поток
//            @Override
//            public void run() {
//                //создаем окно входы в систему
//                new Login();
//            }
//        });

    }
}