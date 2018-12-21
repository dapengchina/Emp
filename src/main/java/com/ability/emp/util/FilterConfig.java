//package com.ability.emp.util;
//
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 过滤器配置
// *
// */
//@Configuration
//public class FilterConfig {
//    /**
//     * 配置过滤器
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean someFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new AuthorityFilterUtil());
//        registration.addUrlPatterns("/*");
//        //registration.addInitParameter("paramName", "paramValue");
//        registration.setName("authorityFilter");
//        return registration;
//    }
//
//}