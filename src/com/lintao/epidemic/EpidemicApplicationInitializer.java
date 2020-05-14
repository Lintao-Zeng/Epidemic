package com.lintao.epidemic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 总配置文件，替代了web.xml中的相关配置
 */
public class EpidemicApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 用来配置ContextLoaderListener创建对应上下文的bean
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //Spring相关配置
        return new Class[]{SpringConfig.class};
    }

    /**
     * 用于定义DispatcherServlet应用上下文的bean
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //SpringMVC相关配
        return new Class[]{SpringMVCConfig.class};
    }

    /**
     * 配置拦截器
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        //返回映射到DispatcherServlet的请求路径
        return new String[]{"/"};
    }
}
