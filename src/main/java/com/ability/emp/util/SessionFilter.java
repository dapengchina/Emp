//package com.ability.emp.util;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
//public class SessionFilter implements Filter {
//
//    @Value("$(serverurl)")
//    private String serverurl;
//    /**
//     * 封装，不需要过滤的list列表
//     */
//    protected static List<Pattern> patterns = new ArrayList<Pattern>();
//    
//    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
//        patterns.add(Pattern.compile("admin"));
//        patterns.add(Pattern.compile("mobile"));
//        //patterns.add(Pattern.compile("login/login"));
//        //patterns.add(Pattern.compile("login.do"));
//        patterns.add(Pattern.compile("main/autoFillty_rj_situation.*"));
//        patterns.add(Pattern.compile("main/post.*"));
//        patterns.add(Pattern.compile(".*[(\\.js)||(\\.css)||(\\.png)]"));
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        
//        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//        if (url.startsWith("/") && url.length() > 1) {
//            url = url.substring(1);
//        }
//        if (isInclude(url)){
//            chain.doFilter(httpRequest, httpResponse);
//            return;
//        } else {
//            HttpSession session = httpRequest.getSession();
//            if (session.getAttribute("admin") != null){
//                // session存在
//                chain.doFilter(httpRequest, httpResponse);
//                return;
//            } else {
//                // session不存在 跳转到登录页
//                httpResponse.sendRedirect("/Emp/admin");
//            }
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    /**
//     * 是否需要过滤
//     * @param url
//     * @return
//     */
//    private boolean isInclude(String url) {
//        for (Pattern pattern : patterns) {
//            Matcher matcher = pattern.matcher(url);
//            if (matcher.matches()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}