package com.lintao.epidemic;

import com.lintao.epidemic.common.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * springmvc相关配置(替换掉了springmvc.xml)
 */
@Configuration
@EnableWebMvc
@ComponentScan(includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Controller.class))
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private DateConverter dateConverter;
    /**
     * 添加视图控制器，如果没有指定，就默认访问这个界面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("epidemic");//访问的是epidemic.jsp
    }

    /**
     * 配置视图解析器（前后缀）
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //定义了一个内部资源视图解析器（InternalResourceViewResolvers）
        registry.jsp("/",".jsp");
    }

    /**
     * 配置日期转换器
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConverter);
    }

    /**
     * 如果没有匹配到action.就使用servlet默认的访问
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //简单来说，就是为给静态资源放行
        configurer.enable();
    }
}
