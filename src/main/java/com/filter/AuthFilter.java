package com.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/user/*", "/admin/*"})  // protect these paths
public class AuthFilter implements javax.servlet.Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse resp= (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		String uri = req.getRequestURI();
		
		// Allow public pages (login, signup, css, js, images)
        if (uri.endsWith("login.jsp") || uri.endsWith("register.jsp")
                || uri.contains("register") || uri.contains("login")
                || uri.contains("css") || uri.contains("js") || uri.contains("images")) {
            chain.doFilter(request, response);
            return;
        }
        
        
        if (uri.contains("/admin/")) {
            if (session == null || session.getAttribute("adminobj") == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");
                return;
            }
        } else if (uri.contains("/user/")) {
            if (session == null || session.getAttribute("userobj") == null) {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }
        }
        
      
        chain.doFilter(request, response);

		
	}

}
