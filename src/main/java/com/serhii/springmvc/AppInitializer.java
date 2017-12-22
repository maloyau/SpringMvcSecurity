package com.serhii.springmvc;

import com.serhii.springmvc.configs.DataConfig;
import com.serhii.springmvc.configs.MvcConfig;
import com.serhii.springmvc.configs.SecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DataConfig.class, SecurityConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
