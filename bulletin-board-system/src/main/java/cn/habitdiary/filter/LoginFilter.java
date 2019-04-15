package cn.habitdiary.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        if(httpServletRequest.getSession().getAttribute("username") == null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp?status=illegal");
            return;
        }else{
            chain.doFilter(req, resp);
            return;
        }
    }

    @Override
    public void destroy() {

    }


}
