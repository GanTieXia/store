package com.gantiexia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author GanTieXia
 * @date 2021/9/11 15:06
 */
@SpringBootApplication

public class StoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
        System.out.println("Yours杂货铺启动成功...");
    }

}
