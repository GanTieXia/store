package com.gantiexia.webconfig.file;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 由于头像文件是存储在本地的项目路径以外的盘符下，所以需配置前端访问本地路径下文件
 *
 * @author GanTieXia
 * @date 2021/10/17 2:58
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /store/store_photos
        registry.addResourceHandler("/storeProject/image/**").addResourceLocations("file:/storeProject/image/");
    }
}
