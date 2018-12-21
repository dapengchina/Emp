//package com.ability.emp.util;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import com.ability.emp.admin.entity.AdminEntity;
//
//public class AuthorityFilterUtil implements Filter{
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
//			throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest)arg0;
//		HttpSession session = httpRequest.getSession();
//		String path = httpRequest.getRequestURI();
//		String api = path.substring(5,10);
//		/**
//		 * 当用户请求后台管理系统时,判断是否已经登录
//		 */
//		if(api.equals("admin")){
//			AdminEntity adminEntity = (AdminEntity) session.getAttribute("admin");
//		    if(adminEntity==null){
//		    	//跳转登录页面
//		    	//arg0.getRequestDispatcher("https://localhost/Emp/adminLogin").forward(arg0, arg1);
////		    	RequestDispatcher dispatcher = arg0.getRequestDispatcher(/Emp/adminLogin);
////                dispatcher.forward(arg0, arg1);
//		    	arg2.doFilter(arg0, arg1);
//		    }else{
//		    	//用户已经登录
//		    	arg2.doFilter(arg0, arg1);
//		    }
//		}
//		
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
